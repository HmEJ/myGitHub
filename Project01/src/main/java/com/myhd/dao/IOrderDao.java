package com.myhd.dao;

import com.myhd.pojo.Order;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * className IOrderDao 订单Dao层接口
 * packageName com.myhd.dao.impl
 * Description IOrderDao
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:15
 */
public interface IOrderDao {
    /**
     * Description: insertOrderDao 应先传入一个只含有生成时间的order对象, 来先生成一个订单id,
     * 再返回给收银员, 用于订单项的填入,
     * 插入请使用BaseDao.updateInfo()方法,
     * @return java.lang.Integer 返回的订单编号 从200001自增
     * @param order 传进来只含有生成时间的order对象
     * @author huian
     * @Date 2023/8/18
     * */
    Integer insertOrder(Order order);

    /**
     * Description: updateOrder 更新订单表, 必须根据订单id进行更新, 更新内容为传入的order对象,
     * 订单id应为先前在insertOrder(Order order)中返回的订单id, 或者用户进行 退款时传进来的订单id,
     * 更新请使用BaseDao.updateInfo()方法,
     * @return java.lang.Integer 返回更新时对订单表影响的行数, 一般都是为1
     * @param order 用于更新的order对象
     * @author huian
     * @Date 2023/8/18
     * */
    Integer updateOrder(Order order);


    /**
     * Description: selectByOrderId 根据订单id返回订单的实体对象, 一般是在订单更新后返回到结账页面的订单信息,
     * 或者订单退货后生成的新订单信息,
     * 查询请使用BaseDao.selectInfo()方法,
     * @return com.myhd.pojo.Order 返回的订单实体对象
     * @param orderId 用于查询订单的订单编号
     * @author huian
     * @Date 2023/8/18
     * */
    Order selectByOrderId(Integer orderId);

    /**
     * Description: countMoneyByDate 根据开始时间和结束时间来统计这段时间订单表的金额, 要求startTime必须小于等于endTime
     * 查询请使用BaseDao.countInfo()方法,
     * @return java.lang.Double 返回根据时间统计的金额
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @author huian
     * @Date 2023/8/18
     * */
    Double countMoneyByDate(LocalDateTime startTime, LocalDateTime endTime);
}
