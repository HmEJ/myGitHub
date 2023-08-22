package com.myhd.dao.impl;

import com.myhd.dao.IOrderItemDao;
import com.myhd.pojo.OrderItem;
import com.myhd.pojo.Product;
import com.myhd.util.BaseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @description sdfsdfsd
 * @author JoneElmo
 * @date 2023-08-18 20:09
 * @version 1.0
 * @package com.myhd.dao.impl
 * @class OrderItemDaoImpl
 */
public class OrderItemDaoImpl implements IOrderItemDao {
    /**
     * @description 插入订单项
     * @author JoneElmo
     * @date 2023-08-18 20:35
     * @param orderItem
     * @return java.lang.Integer
     */
    @Override
    public Integer insertOrderItem(OrderItem orderItem) {

        String sql = "insert into tb_orderitem(orderId,productId,productName, productCount, productPrice, productTotal) VALUES (?,?,?,?,?,?)";
        int i = BaseDao.updateInfo(sql,  orderItem.getOrderId(),orderItem.getProductId(),orderItem.getProductName(), orderItem.getProductCount(), orderItem.getProductPrice(), orderItem.getProductTotal());
        return i;
    }

    /**
     * @description 查找订单项
     * @author JoneElmo
     * @date 2023-08-18 20:35
     * @param orderId
     * @param productId
     * @return com.myhd.pojo.OrderItem
     */
    @Override
    public OrderItem selectOrderItem(Integer orderId, Integer productId) {

        String sql = "select orderId, productId, productName, productCount, productPrice, productTotal from tb_orderitem where orderId=? and productId=?;";
        List<?> list = BaseDao.selectInfo(sql, OrderItem.class, orderId, productId);
        return list.isEmpty()?null: (OrderItem) list.get(0);
    }

    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-19 09:44
     * @param orderId
     * @return java.util.List<com.myhd.pojo.OrderItem>
     */
    @Override
    public List<OrderItem> selectAllOrderItem(Integer orderId) {

        String sql = "select orderId, productId, productName, productCount, productPrice, productTotal from tb_orderitem where orderId=?;";
        List<?> list = BaseDao.selectInfo(sql, OrderItem.class, orderId);
        return list.isEmpty()?new ArrayList<>(): (List<OrderItem>) list;
    }

    /**
     * @description 更新订单项
     * @author JoneElmo
     * @date 2023-08-18 20:35
     * @param orderItem
     * @return java.lang.Integer
     */
    @Override
    public Integer updateOrderItem(OrderItem orderItem) {

        String sql = "update tb_orderitem set productName=?,productCount=?,productPrice=?,productTotal=? where orderId=? and productId=?;";
        int i = BaseDao.updateInfo(sql, orderItem.getProductName(), orderItem.getProductCount(), orderItem.getProductPrice(), orderItem.getProductTotal(), orderItem.getOrderId(), orderItem.getProductId());
        return i;
    }

    /**
     * @description 根据产品对象返回该产品在订单中的总订单额
     * @author JoneElmo
     * @date 2023-08-18 20:35
     * @param product
     * @return java.lang.Double
     */
    @Override
    public Double countByProductId(Product product) {

        String sql = "select sum(productTotal) as count from tb_orderitem where productId=?;";
        Double d = (Double) BaseDao.countInfo(sql, product.getProductId());
        return d;
    }
}
