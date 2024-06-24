package com.myhd.utils;

import com.myhd.mapper.RecMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname ScoreCalculator
 * @Description 1. 0-10s内, 对应分数增长为 0 到 0.25 <br>
 * 2. 10-60s内, 对应分数增长为 0.25 到 0.75 <br>
 * 3.  60-300s内, 对应分数增长为 0.75 到 1.5 <br>
 * 4. 超过300s, 对应分数增长为 1.5 到 2.5  <br>
 * 分数最高是2.5
 * @Date 2024/4/8 上午12:18
 * @Created by HmEJ
 */
public class ScoreCalculator {
    // 定义停留时长对应的分数增长关系
    private static final int[] TIME_THRESHOLDS = {10000, 60000, 300000, 360000}; // 停留时长的阈值，单位为毫秒 10s 1分钟 5分钟
    private static final double[] SCORE_THRESHOLDS = {0.0, 0.25, 0.75, 1.5, 2.5}; // 对应的评分阈值

    // 计算停留时长对应的分数增长
    public static double calculateStayScore(long durationInMillis) {
        double score;
        if (durationInMillis <= TIME_THRESHOLDS[0]) {
            // 在0-10秒内，对应分数增长为0分到0.25分
            score = SCORE_THRESHOLDS[0] + (durationInMillis / (double) TIME_THRESHOLDS[0]) * (SCORE_THRESHOLDS[1] - SCORE_THRESHOLDS[0]);
        } else if (durationInMillis <= TIME_THRESHOLDS[1]) {
            // 在10秒-60秒内，对应分数增长为0.25分到0.75分
            score = SCORE_THRESHOLDS[1] + ((durationInMillis - TIME_THRESHOLDS[0]) / (double) (TIME_THRESHOLDS[1] - TIME_THRESHOLDS[0])) * (SCORE_THRESHOLDS[2] - SCORE_THRESHOLDS[1]);
        } else if (durationInMillis <= TIME_THRESHOLDS[2]) {
            // 在60秒-300秒内，对应分数增长为0.75分到1.5分
            score = SCORE_THRESHOLDS[2] + ((durationInMillis - TIME_THRESHOLDS[1]) / (double) (TIME_THRESHOLDS[2] - TIME_THRESHOLDS[1])) * (SCORE_THRESHOLDS[3] - SCORE_THRESHOLDS[2]);
        } else if (durationInMillis <= TIME_THRESHOLDS[3]) {
            // 在300秒-360秒内，对应分数增长为1.5分到2.5分
            score = SCORE_THRESHOLDS[3] + ((durationInMillis - TIME_THRESHOLDS[2]) / (double) (TIME_THRESHOLDS[3] - TIME_THRESHOLDS[2])) * (SCORE_THRESHOLDS[4] - SCORE_THRESHOLDS[3]);
        } else {
            // 超过360秒，记为2.5分
            score = SCORE_THRESHOLDS[4];
        }
        return Math.round(score * 100.0) / 100.0;
    }

    public static HashMap<Integer, Double> calculateNormalScore(List<Integer> list, Integer recruitId, HashMap<Integer, Double> userRatingMap) {
        //如果用户申请了该职位, 对该职位的评分加1
        if (!list.isEmpty()) {
            for (Integer id : list) {
                if (id.equals(recruitId)) {
                    //拿到评分
                    Double rating = userRatingMap.get(recruitId);
                    if (rating < 5) {
                        rating += 1;
                        userRatingMap.put(id, rating);
                    }
                } else {
                    double grade = 1;
                    userRatingMap.put(id, grade);
                }
            }
        }
        return userRatingMap;
    }
}


