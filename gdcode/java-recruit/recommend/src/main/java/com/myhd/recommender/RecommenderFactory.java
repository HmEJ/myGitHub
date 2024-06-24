package com.myhd.recommender;


import com.myhd.config.RecommenderConfigBuilder;

import com.myhd.datahandle.FileDataDao;
import lombok.extern.slf4j.Slf4j;
import org.lenskit.LenskitConfiguration;
import org.lenskit.LenskitRecommender;
import org.lenskit.data.dao.DataAccessObject;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import static com.myhd.enums.RecommendTypeEnum.POPULAR;


/**
 * @author TARZAN
 */
@Slf4j
@Component
public class RecommenderFactory implements BeanPostProcessor {

    private static final Map<String, LenskitRecommender> RECOMMENDER_POOL = new ConcurrentHashMap<>(10);

    static {
        // RECOMMENDER_POOL.put(FUNK_SVD.getType(), getItemRecommender(FUNK_SVD.getType()));
//        RECOMMENDER_POOL.put(ITEM_CF.getType(), getItemRecommender(ITEM_CF.getType()));
        // RECOMMENDER_POOL.put(USER_CF.getType(), getItemRecommender(USER_CF.getType()));
        // RECOMMENDER_POOL.put(SLOPE_ONE.getType(), getItemRecommender(SLOPE_ONE.getType()));
        RECOMMENDER_POOL.put(POPULAR.getType(), getRecommender(POPULAR.getType()));
    }

    public static LenskitRecommender getRecommender(LenskitConfiguration config, DataAccessObject dao) {
        //最后，获取并使用该推荐器。
        try (LenskitRecommender rec = LenskitRecommender.build(config, dao)) {
            rec.close();
            log.info("obtained recommender from engine");
            //我们想要推荐项
            return rec;
        }
    }

    public static LenskitRecommender getRecommender(String type, DataAccessObject dao) {
        LenskitConfiguration config = RecommenderConfigBuilder.createConfig(type);
        log.info("RecommenderFactory.getRecommender(type,dao).config:{}",config);
        return getRecommender(config, dao);
    }

    public static LenskitRecommender getRecommender(String type) {
        DataAccessObject dao = FileDataDao.get();
        log.info("RecommenderFactory.getRecommender(type).dao:{}",dao);
        if (RECOMMENDER_POOL.get(type) != null) {
            return RECOMMENDER_POOL.get(type);
        } else {
            LenskitRecommender recommender = getRecommender(type, dao);
            RECOMMENDER_POOL.put(type, recommender);
            return recommender;
        }
    }


    /**
     * 30分钟更新一下推荐引擎
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    private void reload() {
        DataAccessObject dao = FileDataDao.load();
        RECOMMENDER_POOL.forEach((k, v) -> RECOMMENDER_POOL.put(k, getRecommender(k, dao)));
    }

}
