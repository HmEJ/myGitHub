package com.myhd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname AnalysisRedisVal
 * @Description
 * @Date 2024/4/8 下午5:34
 * @Created by HmEJ
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisRedisVal {
    private Double rating;
    private Long timeStamp;
}


