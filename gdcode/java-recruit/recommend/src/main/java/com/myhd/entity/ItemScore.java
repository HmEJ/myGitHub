package com.myhd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Classname ItemScore
 * @Description
 * @Date 2024/4/20 下午2:43
 * @Created by HmEJ
 */
@Data
@AllArgsConstructor
public class ItemScore {
    private Long itemId;
    private Double score;
    private String itemName;
    private String recruitTag;
}
