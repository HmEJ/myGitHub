package com.myhd.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业表
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Company implements Serializable {

    private static final long serialVersionUID = -626166199017249786L;

    /**
     * 表序号
     */
    private Integer tbId;

    /**
     * 企业编号
     */
    private Integer companyId;

    /**
     * 企业图标
     */
    private String companyIcon;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业类别
     */
    private String companyType;

    /**
     * 企业规模
     */
    private String companyScale;

    /**
     * 企业主页
     */
    private String companyHome;

    /**
     * 运营领域
     */
    private String companyField;

    /**
     * 企业地址
     */
    private String companyAddress;

    /**
     * 企业介绍
     */
    private String companyText;


}
