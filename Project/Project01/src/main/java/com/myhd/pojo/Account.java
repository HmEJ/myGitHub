package com.myhd.pojo;

import lombok.*;

/**
 * className Account
 * packageName com.myhd.pojo
 * Description Account 收银账户实体类
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Integer accountId;
    private String accountPwd;
    private Double money;

}
