package com.myhd.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 用户信息表 存放用户详细信息
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -1214419438946776357L;

    /**
     * 表序号
     */
    private Integer tbId;

    /**
     * 用户编号 用于唯一识别用户
     */
    private Integer userId;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 性别
0 女
1 男
2 第三性别
     */
    private Integer gender;

    /**
     * 用户生日
     */
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date birthday;

    /**
     * 现居住地
     */
    private String resident;

    /**
     * 户口所在地
     */
    private String place;

    /**
     * 求职状态 
0 离职-随时到岗
1 在职-月内到岗
2 在职-考虑机会
3 在职-暂不考虑
     */
    private Integer jobStatus;

    /**
     * 工作经验
0 无
1 有
     */
    private Integer workExp;

    /**
     * 期望工作性质
0 全职
1 兼职
2 实习
     */
    private Integer wantedNature;

    /**
     * 期望月薪
0 面议
1 5k-
2 5k-10k
3 10k-20k
4 20k-30k
5 30k+
     */
    private Integer wantedSalary;

    /**
     * 期望工作地点
     */
    private String wantedLocation;

    /**
     * 期望职位类别
     */
    private String wantedType;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 毕业时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date graduationTime;

    /**
     * 学历
0 高中
1 专科
2 本科
3 硕士
4 博士
     */
    private Integer degree;

    /**
     * 学历类型
0 普通高等教育（统招）
1 普通专升本（统招）
2 成人高考
3 自考
4 电大
5 网络教育
     */
    private Integer degreeType;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 专业类别
0 文学
1 理学
2 工学
3 医学
     */
    private Integer majorType;

    /**
     * 成长地
     */
    private String growPlace;


}
