package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * className OrderItem
 * packageName com.myhd.pojo
 * Description OrderItem 订单项实体类
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer productCount;
    private Double productPrice;
    private Double productTotal;
}
