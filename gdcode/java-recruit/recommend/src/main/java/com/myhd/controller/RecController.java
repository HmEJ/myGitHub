package com.myhd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.myhd.datahandle.FileDataDao;
import com.myhd.dto.*;
import com.myhd.entity.InitRec;
import com.myhd.entity.ItemScore;
import com.myhd.enums.RecommendTypeEnum;
import com.myhd.mapper.RecMapper;
import com.myhd.recommender.RecommenderFactory;
import com.myhd.util.Result;
import com.myhd.utils.ScoreCalculator;
import com.myhd.vo.ItemVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.lenskit.LenskitRecommender;
import org.lenskit.api.*;

import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.EntityQueryBuilder;
import org.lenskit.data.dao.SortOrder;
import org.lenskit.data.entities.CommonAttributes;
import org.lenskit.data.entities.CommonTypes;
import org.lenskit.data.entities.Entity;
import org.lenskit.data.entities.TypedName;
import org.lenskit.knn.user.Neighbor;
import org.lenskit.knn.user.NeighborFinder;
import org.lenskit.util.collections.SortedListAccumulator;
import org.lenskit.util.io.ObjectStream;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname RecommendController
 * @Description 推荐接口 (测试)
 * @Date 2024/4/2 下午5:51
 * @Created by HmEJ
 */
@RestController
@Slf4j
@SuppressWarnings("all")
@RequestMapping("/rec")
public class RecController {
    private ObjectMapper mapper = new ObjectMapper();
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RecMapper recMapper;

    /**
     * 直接推荐职位. recruitId + num
     *
     * @return
     * @throws RecommenderBuildException
     * @throws JsonProcessingException
     */
    @PostMapping("/directRec")
    public Result recommend(@RequestBody RecDTO recDTO) throws RecommenderBuildException, JsonProcessingException {
        LenskitRecommender recommender = RecommenderFactory.getRecommender(RecommendTypeEnum.ITEM_CF.getType());
        ItemBasedItemRecommender itemBasedItemRecommender = recommender.getItemBasedItemRecommender();
        // 获取到指定数据量的职位id
        List<Long> list = itemBasedItemRecommender.recommendRelatedItems(recDTO.getRecruitId(), recDTO.getNum());
        //根据职位id查询相关信息 (companyIcon, recruitId, recruitName, companyName, salary)

        return Result.ok(100, "json", "success!");
    }

    /**
     * 预置的type有 item-item;user-user;popular;slope-one;funk-svd; <br>
     * 根据用户进行物品推荐 userId + num + type
     *
     * @return
     */
    @PostMapping("/userRec")
    public Result getRecommendUser(@RequestBody RecDTO recDTO) throws JsonProcessingException {
        if (recDTO.getUserId() == null) {
            return Result.fail("获取失败!");
        }
        DataAccessObject dao = FileDataDao.get();
        LenskitRecommender recommender = RecommenderFactory.getRecommender(recDTO.getType());
        ItemRecommender itemRecommender = recommender.getItemRecommender();
        List<Long> ids = itemRecommender.recommend(recDTO.getUserId(), recDTO.getNum(), null, null);
        if (ids.isEmpty()) {
            log.info("RecController.getRecommendUser:{推荐失败,可能是数据量过少或者其他数据集问题.}");
            return Result.fail(500, false, "推荐失败,可能是数据量过少或者其他数据集问题");
        }
        log.info("RecController.getRecommendUser.协同过滤推荐ids:{}", ids.toString());
        /*根据职位id集合获取职位相关数据*/
        String key = "userRec:" + recDTO.getUserId();
        String redisVal = stringRedisTemplate.opsForValue().get(key);
        if (redisVal != null) {
            log.info("RecController.getRecommendUser:{redis存在数据,正在读取}");
            return Result.ok(100, redisVal, "success!");
        }
        //从数据库查询
        List<RecReturnDTO> list = recMapper.getRecruitInfoByIdList(ids);
        String returnJson = mapper.writeValueAsString(list);
        //日志打印list的size即可. 否则太影响日志内容
        log.info("RecController.getRecommendUser.招聘数据list.size:{}", list.size());
        /* 数据写入redis缓存 缓存5分钟 */
        stringRedisTemplate.opsForValue().set(key, returnJson, Duration.ofMinutes(5L));
        return Result.ok(100, returnJson, "success!");
    }

    /**
     * 用户历史物品评分
     *
     * @return
     */
    @PostMapping("/userRating")
    public Result userRating(@RequestBody RecDTO recDTO) throws JsonProcessingException {
        DataAccessObject dao = FileDataDao.get();
        EntityQueryBuilder builder = new EntityQueryBuilder(CommonTypes.RATING);
        builder.addFilterField(CommonAttributes.USER_ID, (long) recDTO.getUserId());
        builder.addSortKey(CommonAttributes.RATING, SortOrder.DESCENDING);
        ObjectStream<Entity> stream = dao.streamEntities(builder.build());
        List<ItemVO> items = new ArrayList<>(10);
        stream.forEach(e -> {
            ItemVO vo = new ItemVO();
            vo.setId(e.getLong(CommonAttributes.ITEM_ID));
            vo.setRating(e.getDouble(CommonAttributes.RATING));
            Entity itemData = dao.lookupEntity(CommonTypes.ITEM, vo.getId());
            vo.setName(itemData.maybeGet(CommonAttributes.NAME));
            vo.setGenres(itemData.maybeGet(TypedName.create("genres", String.class)));
            items.add(vo);
        });
        String json = mapper.writeValueAsString(items);
        return Result.ok(100, json, "success!");
    }

    /**
     * 查找相似性用户
     *
     * @return
     */
    @PostMapping("/findNeighbors")
    public Result findNeighbors(@RequestBody RecDTO recDTO) throws JsonProcessingException {
        LenskitRecommender recommender = RecommenderFactory.getRecommender(RecommendTypeEnum.USER_CF.getType());
        DataAccessObject dao = recommender.getDataAccessObject();
        // 获取 LiveNeighborFinder 实例
        NeighborFinder neighborFinder = recommender.get(NeighborFinder.class);
        Iterable<Neighbor> neighbors = neighborFinder.getCandidateNeighbors(recDTO.getUserId(), dao.getEntityIds(CommonTypes.ITEM));
        SortedListAccumulator<Neighbor> tops = SortedListAccumulator.decreasing(recDTO.getNum(), Neighbor.SIMILARITY_COMPARATOR);
        for (Neighbor nbr : neighbors) {
            tops.add(nbr);
        }
        List<Neighbor> finish = tops.finish();
        String json = mapper.writeValueAsString(finish);
        return Result.ok(100, json, "success!");
    }

    /**
     * 获取初始化推荐
     *
     * @param initRecDTO
     * @return
     */
    @PostMapping("/requireInitRec")
    public Result requireInitRec(@RequestBody InitRecDTO initRecDTO) {
        log.info("RecController.requireInitRec:{}", initRecDTO);
        try {
            List<String> dtoList = initRecDTO.getUserInitTecList();
            String userInitTec = String.join(",", dtoList);
            InitRec initRec = InitRec.builder()
                    .userInitTec(userInitTec)
                    .userId(initRecDTO.getUserId())
                    .userInitField(initRecDTO.getUserInitField())
                    .build();
            recMapper.saveInit(initRec);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("数据插入异常");
        }
        /* 获取推荐列表 */
        List<RecReturnDTO> list = recMapper.getInitRec(initRecDTO);
        String json = null;
        try {
            json = mapper.writeValueAsString(list);
            String key = "userRec:" + initRecDTO.getUserId();
            stringRedisTemplate.opsForValue().set(key, json, Duration.ofDays(5));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Result.fail("序列化错误!");
        }
        log.info("RecController.requireInitRec.返回的推荐列表:{}", json);
        return Result.ok(100, json, "success!");
    }

    /**
     * 判断是否存在初始化推荐 <br>
     * 存在则返回redis数据 不存在则返回失败
     *
     * @param userId
     * @return
     */
    @GetMapping("/judgeInitRec/{userId}")
    public Result judgeInitRec(@PathVariable Integer userId) {
        log.info("RecController.judgeInitRec.userId:{}", userId);
        String key = "userRec:" + userId;
        String valStr = stringRedisTemplate.opsForValue().get(key);
        if (valStr == null || "".equals(valStr)) {
            return Result.fail("未找到数据");
        }
        return Result.ok(100, valStr, "success!");
    }

    /**
     * 分析用户行为 生成推荐数据 <br>
     * 1. 职位详情页面用户停留时长 <br>
     * 2. 用户申请职位 <br>
     * 3. 用户关注企业 <br>
     *
     * @return
     */
    @PostMapping("/analysis")
    public void analysisRec(@RequestBody RecAnalysis ra) {
        Integer recruitId = ra.getRecruitId();
        log.info("RecController.analysisRec.stayTime:{}", ra.toString());
        //1.用户的停留时长 并计分
        Long stayTime = ra.getStayTime();
        double score = ScoreCalculator.calculateStayScore(stayTime);
        log.info("stayTime得分是:{}", score);
        /*
         * map存入redis
         * redis中使用hash格式存储
         * 格式: key = "rec:rating:analysis" + ra.getUserId
         * value = userRatingMap <br>
         *     redis中格式belike:   key | userRatingMap
         */
        HashMap<Integer, Double> userRatingMap = new HashMap<>();
        userRatingMap.put(recruitId, score);

        //2.获取用户申请的职位 并计分
        List<Integer> applyRecruitList = recMapper.getList(ra.getUserId());
        log.info("RecController.analysisRec.applyRecruitList.用户申请的职位列表:{}", applyRecruitList);
        userRatingMap = ScoreCalculator.calculateNormalScore(applyRecruitList, recruitId, userRatingMap);

        //3.获取用户关注的企业
        List<Integer> companyIdList = recMapper.getFollowList(ra.getUserId());
        log.info("RecController.analysisRec.followCompanyList.用户关注的企业列表:{}", companyIdList.toString());

        if (!companyIdList.isEmpty()) {
            //根据这些企业查询到企业的招聘列表
            List<Integer> companyRecruitList = recMapper.getRecruitByCompanyIdList(companyIdList);
            log.info("RecController.analysisRec.followCompanyList.用户关注的企业的招聘职位列表:{}", companyRecruitList.toString());
            //计分
            userRatingMap = ScoreCalculator.calculateNormalScore(companyRecruitList, recruitId, userRatingMap);
        }
        //4.存入redis
        String key = "rec:analysis:rating:" + ra.getUserId();
        userRatingMap.forEach(
                (k, v) -> {
                    long timeStamp = System.currentTimeMillis();
                    AnalysisRedisVal val = new AnalysisRedisVal();
                    val.setRating(Math.floor(v * 100) / 100);
                    val.setTimeStamp(timeStamp);
                    stringRedisTemplate.opsForHash().put(key, k.toString(), val.toString());
                }
        );
        log.info("RecController.analysisRec.分析数据已存入redis:{}", key);
    }

    public double cosineSimilarity(int[] vectorA, int[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    @GetMapping("/test")
    public String test() {
        int[] a = {1, 0, 0, 1};
        int[] b = {1, 1, 0, 1};
        double similarity = cosineSimilarity(a, b);
        System.out.println("The cosine similarity between a and b is " + similarity);
        return ""+similarity;
    }

    @GetMapping("item/recommend")
    @ApiOperation(value = "根据物品进行物品推荐")
    public Result getRecommendItem(Long itemId, Integer num) {
        LenskitRecommender recommender = RecommenderFactory.getRecommender(RecommendTypeEnum.ITEM_CF.getType());
        ItemBasedItemScorer itemScorer = recommender.getItemBasedItemScorer();
        DataAccessObject dao = recommender.getDataAccessObject();
        // 获取所有的物品ID
        Set<Long> allItemIds = dao.getEntityIds(CommonTypes.ITEM);
        // 为所有的物品评分
        ResultMap scores = itemScorer.scoreRelatedItemsWithDetails(Collections.singleton(itemId), allItemIds);
        // 将结果转换为一个列表，并按照相似度降序排序
        List<ItemScore> itemScores = scores.entrySet().stream()
                .map(entry -> {
                    // 获取物品实体
                    Entity itemEntity = dao.lookupEntity(CommonTypes.ITEM, entry.getKey());
                    // 获取物品名称
                    String itemName = itemEntity.maybeGet(CommonAttributes.NAME);
                    // 获取物品标签
                    String recruitTag = itemEntity.maybeGet(TypedName.create("genres", String.class));
                    // 创建ItemScore对象
                    return new ItemScore(entry.getKey(), entry.getValue().getScore(), itemName, recruitTag);
                })
                .sorted(Comparator.comparing(ItemScore::getScore).reversed())
                .collect(Collectors.toList());
        // 选择前N个物品作为结果返回
        List<ItemScore> topItemScores = itemScores.subList(0, Math.min(num, itemScores.size()));
        return Result.ok(100, topItemScores, "success!");
    }
}
