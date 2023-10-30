package com.myhd.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 招聘职位表
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Recruit implements Serializable {

    private static final long serialVersionUID = 24332134093119558L;

    /**
     * 企业编号
     */
    private Integer companyId;

    /**
     * 招聘编号
     */
    private Integer recruitId;

    /**
     公司名称
    * */
    private String companyName;

    /**
     * 招聘的职位名称
     */
    private String recruitName;

    /**
     * 招聘的职位标签  多个标签用#分割
     */
    private String recruitTag;

    /**
     * 职位地址
     */
    private String recruitAddress;

    /**
     * 最高薪
     */
    private Double recruitSalaryMax;

    /**
     * 最低薪
     */
    private Double recruitSalaryMin;

    /**
     * 招聘人数
     */
    private Integer recruitNumber;

    /**
     * 招聘学历

     */
    private String recruitDegree;

    /**
     * 招聘年龄
     */
    private String recruitAge;

    /**
     * 招聘的要求工作经验
     */
    private String recruitExp;

    /**
     * 招聘方式
     */
    private String recruitType;

    /**
     * 招聘发布/更新时间
     */
    private Date recruitTime;

    /**
     * 岗位职责
     */
    private String recruitTextDuty;

    /**
     * 岗位要求
     */
    private String recruitTextNeed;

    /**
     * 职位领域
     */
    private Integer jobField;

    /**
     * compnay字段
     * 用于在首页高薪职位列表中显示公司logo
     */
    private Company company;

    /**
     * 用于判断记录是否被申请
     * 通过外连接查询出来的结果
     * 如果包含userid字段，说明被申请
     * 如果userid字段为null,说明未被申请
     */
    private Integer userId;
    /*用于判断是否关注*/
    private Integer followStatus;
    /**
     * 用于模糊查询展示列表页面显示企业规模信息
     */
    private String companyScale;
}
