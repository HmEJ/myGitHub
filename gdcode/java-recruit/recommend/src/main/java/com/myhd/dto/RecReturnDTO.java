package com.myhd.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Classname RecReturnDTO
 * @Description
 * @Date 2024/4/5 下午11:12
 * @Created by HmEJ
 */
@Data
public class RecReturnDTO {
    /**
     * 企业编号
     */
    private Integer companyId;

    /**
     * 招聘编号
     */
    private Integer recruitId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 招聘的职位名称
     */
    private String recruitName;

    /**
     * 文字性薪资描述
     */
    private String recruitSalary;

    /**
     * 公司图标
     */
    private String companyIcon;
}
