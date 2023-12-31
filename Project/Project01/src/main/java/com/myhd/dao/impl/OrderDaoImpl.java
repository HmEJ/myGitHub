package com.myhd.dao.impl;

import com.myhd.dao.IOrderDao;
import com.myhd.pojo.Order;
import com.myhd.util.BaseDao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-18 19:47
     * @version 1.0
     * @package com.myhd.dao.impl
     * @class OrderDaoImpl
     */
    @Override
    public Integer insertOrder(Order order) {
        /**
         * @description 插入订单数据
         * @author JoneElmo
         * @date 2023-08-18 19:47
         * @param order
         * @return java.lang.Integer
         */
        String sql = "insert into tb_order(orderDateTime) values (?);";
        BaseDao.updateInfo(sql, order.getOrderDateTime());
        List<?> list = BaseDao.selectInfo("select * from tb_order where orderId = (select max(orderId) as orderId from tb_order)", Order.class);
        Object obj = list.get(0);
        Order oo = (Order) obj;
        return oo.getOrderId();
    }

    @Override
    public Integer updateOrder(Order order) {
        /**
         * @description
         * @author JoneElmo
         * @date 2023-08-18 19:50
         * @param order
         * @return java.lang.Integer
         */
        String sql = "update tb_order set orderTotal=?,orderTrueTotal=?,orderDateTime=? where orderId=?;";
        int i = BaseDao.updateInfo(sql, order.getOrderTotal(), order.getOrderTrueTotal(), order.getOrderDateTime(), order.getOrderId());
        return i;
    }

    @Override
    public Order selectByOrderId(Integer orderId) {
        /**
         * @description
         * @author JoneElmo
         * @date 2023-08-18 19:54
         * @param orderId
         * @return com.myhd.pojo.Order
         */
        String sql = "select orderId, orderTotal, orderTrueTotal, orderDateTime from tb_order where orderId=?;";
        List<?> list = BaseDao.selectInfo(sql, Order.class, orderId);
        return (Order) list.get(0);
    }

    @Override
    public Double countMoneyByDate(LocalDateTime startTime, LocalDateTime endTime) {
        /** 
         * @description 
         * @author JoneElmo
         * @date 2023-08-18 20:10
         * @param startTime
         * @param endTime
         * @return java.lang.Double
         */ 
        String sql = "select sum(orderTrueTotal) as count from tb_order where orderDateTime between ? and ?;";
        Double d = (Double) BaseDao.countInfo(sql, startTime, endTime);
        return d;
    }
}
