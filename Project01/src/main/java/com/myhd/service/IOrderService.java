package com.myhd.service;

import com.myhd.pojo.Order;
import com.myhd.pojo.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

/**
 * className IOrderService
 * packageName com.myhd.service
 * Description IOrderService
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 21:05
 */
public interface IOrderService {
    /**
     * Description: insertOrderDao 应先传入一个只含有生成时间的order对象, 来先生成一个订单id,
     * 再返回给收银员, 用于订单项的填入, 此方法可应用与订单项服务层的结账功能调用
     * 插入请使用OrderDaoImpl.InsertOrder()方法,
     * @return java.lang.Integer 返回的订单编号 从200001自增
     * @param order 传进来只含有生成时间的order对象
     * @author huian
     * @Date 2023/8/18
     * */
    Integer InsertOrder(Order order);

    /**
     * Description: selectByOrderId 根据订单id返回订单的实体对象, 一般是在订单更新后返回到结账页面的订单信息,
     * 或者订单退货后生成的新订单信息,
     * 查询请使用OrderDaoImpl.selectByOrderId()方法,
     * @return com.myhd.pojo.Order 返回的订单实体对象
     * @param orderId 用于查询订单的订单编号
     * @author huian
     * @Date 2023/8/18
     * */
    Order selectByOrderId(Integer orderId);

    /**
     * Description: returnOfGoods 退货功能, 应该把已经确认的退货订单项信息集合(必须含有订单编号)传入退货方法里, 再使用订单项
     * 服务层的更新订单项方法, 然后调用订单更新功能, 最后调用订单查询功能把退款更新好的信息传回服务层
     * @return java.lang.Boolean 返回的订单对象
     * @param orderItems 确认好的订单项集合
     * @author huian
     * @Date 2023/8/19
     * */
    Order returnOfGoods(Order order , List<OrderItem> orderItems);

    /**
     * Description: billPlease 结账功能, 把顾客确认的订单项集合(每一条订单项必须含有订单编号, 用来更新订单)传入结账功能, 再使用订单项
     * 服务层的更新订单项方法, 然后使用活动服务层查出相应的折扣进行总价格(总价格可以根据传入进来的订单项集合算出来)的运算,
     * 根据订单项里的订单编号调用OrderDaoImpl订单更新方法, 最后调用订单OrderDaoImpl查询功能把结账更新好的信息传回服务层
     * @return com.myhd.pojo.Order 返回的订单对象
     * @param orderItems 确认好的订单项集合
     * @author huian
     * @Date 2023/8/19
     * */
    Order billPlease(Order order, List<OrderItem> orderItems);

    /**
     * Description: countMoneyByDate 根据开始时间和结束时间来统计这段时间订单表的金额, 要求startTime必须小于等于endTime,
     * 而且必须在服务层里进行判断
     * 查询请使用OrderDaoImpl.countMoneyByDate()方法,
     * @return java.lang.Double 返回根据时间统计的金额
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @author huian
     * @Date 2023/8/18
     * */
    Double countMoneyByDate(LocalDateTime startTime, LocalDateTime endTime);

}
