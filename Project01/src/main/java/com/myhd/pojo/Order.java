package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * className Order
 * packageName com.myhd.pojo
 * Description Order 订单实体类
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private Double orderTotal;
    private Double orderTrueTotal;
    private LocalDateTime orderDateTime;
}
