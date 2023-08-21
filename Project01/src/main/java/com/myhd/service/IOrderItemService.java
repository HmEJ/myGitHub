package com.myhd.service;

import com.myhd.pojo.OrderItem;
import com.myhd.pojo.Product;

import java.util.List;

/**
 * className IOrderItemService 订单项服务层接口
 * packageName com.myhd.service
 * Description IOrderItemService
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 21:05
 */
public interface IOrderItemService {
    /**
     * Description: insertOrderItem 获取到订单编号和商品编号后再
     * 把用户填入的数量, 商品单价, 和前两者的乘积封装到订单项中进行传入,
     * 建议通过遍历最终的订单项目集合来进行订单项的填入, 避免数据库订单表的重复增删
     * 插入请使用OrderItemDaoImpl.insertOrderItem()方法,
     * @return java.lang.Integer 返回订单项是否插入成功
     * @param orderItem 传进的订单项实体对象
     * @author huian
     * @Date 2023/8/18
     * */
    Boolean insertOrderItem(OrderItem orderItem);

    /**
     * Description: selectOrderItem 根据订单编号和商品编号查询唯一订单项, 一般只用于退货时订单项的查询,
     * 不建议全部返回当前订单编号的全部订单项, 只需返回顾客需要退款的订单编号和商品编号确认的退款订单项, 需要全部返回
     * 请使用selectAllOrderItem()方法
     * 查询请使用OrderItemDaoImpl.selectOrderItem()方法
     * @return com.myhd.pojo.OrderItem 返回查询到的订单项
     * @param orderId 订单编号
     * @param productId 商品编号
     * @author huian
     * @Date 2023/8/18
     * */
    OrderItem selectOrderItem(Integer orderId, Integer productId);

    /**
     * Description: selectAllOrderItem 查询当前订单编号下的全部订单项信息
     * @return java.util.List<com.myhd.pojo.OrderItem>
     * @param orderId 订单编号
     * @author huian
     * @Date 2023/8/19
     * */
    List<OrderItem> selectAllOrderItem(Integer orderId);

    /**
     * Description: deleteOrderItem 用于退货时订单项的更新, 建议遍历最终要的更新的订单项集合进行
     * 订单项表的更新, 同时对订单表的金额进行更新, 用于订单服务层
     * 更新请使用OrderItemDaoImpl.updateOrderItem()方法,
     * @return java.lang.Integer 返回订单项是否更新成功
     * @param orderItem 要进行更新的订单项
     * @author huian
     * @Date 2023/8/18
     * */
    Boolean updateOrderItem(OrderItem orderItem);

    /**
     * Description: countByProductId 根据商品查询订单项统计商品的销售情况
     * 请使用OrderItemDaoImpl.countByProductId()方法
     * @return java.lang.Double 返回根据商品统计的金额
     * @param product 商品对象
     * @author huian
     * @Date 2023/8/18
     * */
    Double countByProductId(Product product);
}
