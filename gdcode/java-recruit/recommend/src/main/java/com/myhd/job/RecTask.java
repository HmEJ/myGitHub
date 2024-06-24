package com.myhd.job;

import com.myhd.dto.AnalysisRedisVal;
import com.myhd.mapper.RecMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Classname RecTask
 * @Description
 * @Date 2024/4/8 下午6:08
 * @Created by HmEJ
 */
@Component
@Slf4j
public class RecTask {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RecMapper recMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void exportData() {
        // 1. 从redis中获取数据
        Set<String> keySet = stringRedisTemplate.keys("rec:analysis:rating:*");
        List<Integer> idList = keySet.stream().map(
                key -> {
                    key = key.split(":")[3];
                    return key;
                }
        ).collect(Collectors.toList()).stream().map(
                id -> {
                    return Integer.valueOf(id);
                }
        ).collect(Collectors.toList());
        log.info("RecTask.从redis获取的idList:{}", idList);
        // 2. 导出数据到mysql
        int flag = 0;
        for (Integer id : idList) {
            String key = "rec:analysis:rating:" + id;
            Set<Object> keys = stringRedisTemplate.opsForHash().keys(key);
            for (Object k : keys) {
                AnalysisRedisVal v = (AnalysisRedisVal) stringRedisTemplate.opsForHash().get(key, k);
                Double rating = v.getRating();
                Long timeStamp = v.getTimeStamp();
                try {
                    flag = recMapper.insertRating(id, (Integer) k, rating, timeStamp);
                    if (flag == 1) {
                        log.info("RecTask.导出数据到mysql成功:{},{},{},{}", id, k, rating, timeStamp);
                        //删除redis数据
                        stringRedisTemplate.opsForHash().delete(key, k);
                    } else {
                        log.info("RecTask.导出数据到mysql失败:{},{},{},{}", id, k, rating, timeStamp);
                    }
                } catch (Exception e) {
                    log.error("RecTask.导出数据到mysql异常:{}", e);
                }
            }
        }
    }
}
