package com.myhd.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 简历表
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Resume implements Serializable {

    private static final long serialVersionUID = 49416326599804497L;

    /**
     * 表序号
     */
    private Integer tbId;

    /**
     * 简历编号
     */
    private Integer resumeId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String resumeName;

    /**
     * 性别
     */
    private Integer resumeGender;

    /**
     * 出生地
     */
    private String resumePlace;

    /**
     * 手机号
     */
    private String resumePhone;

    /**
     * 毕业学校
     */
    private String resumeSchool;

    /**
     * 学历
     */
    private String resumeDegree;

    /**
     * 所学专业
     */
    private String resumeMajor;

    /**
     * 工作地点 （下拉列表）
     */
    private Integer resumeLoc;

    /**
     * 期望薪资
     */
    private Integer resumeSalary;

    /**
     * 期望工作性质
     */
    private Integer resumeJobType;

    /**
     * 求职状态
     */
    private Integer resumeStatus;

    /**
     * 邮箱
     */
    private String resumeEmail;


}
