package com.myhd.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 关注企业表
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Follow implements Serializable {

    private static final long serialVersionUID = 7242065920197731534L;

    /**
     * 用户编号
和企业编号是联合主键
     */
    private Integer userId;

    /**
     * 企业编号
     */
    private Integer companyId;

    /**
     * 关注状态
0 未关注
1 已关注

     */
    private Integer followStatus;


}
