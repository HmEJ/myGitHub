package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * className EmpUser
 * packageName com.myhd.pojo
 * Description EmpUser 员工用户实体类(包括普通员工和总经理)
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpUser {
    private Integer empUserId;
    private String empUserName;
    private String empUserDuty;
    private String empUserPwd;
}
