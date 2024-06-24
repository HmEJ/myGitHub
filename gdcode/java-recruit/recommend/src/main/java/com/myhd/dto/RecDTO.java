package com.myhd.dto;

import lombok.Data;

/**
 * @Classname RecDTO
 * @Description
 * @Date 2024/4/5 下午10:49
 * @Created by HmEJ
 */
@Data
public class RecDTO {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 推荐个数
     */
    private Integer num = 10;
    /**
     * 推荐器类型
     */
    private String type = "item-item";
    /**
     * 职位id
     */
    private Integer recruitId;
}
