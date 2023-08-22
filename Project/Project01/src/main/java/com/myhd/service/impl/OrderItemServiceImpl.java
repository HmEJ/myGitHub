package com.myhd.service.impl;

import com.myhd.dao.impl.OrderDaoImpl;
import com.myhd.dao.impl.OrderItemDaoImpl;
import com.myhd.pojo.OrderItem;
import com.myhd.pojo.Product;
import com.myhd.service.IOrderItemService;

import java.util.ArrayList;
import java.util.List;
/**
 * @description
 * @author JoneElmo
 * @date 2023-08-20 14:14
 * @version 1.0
 * @package com.myhd.service.impl
 * @class OrderItemServiceImpl
 */
public class OrderItemServiceImpl implements IOrderItemService {

    private OrderItemDaoImpl oidi = new OrderItemDaoImpl();
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:14
     * @param orderItem
     * @return java.lang.Boolean
     */
    @Override
    public Boolean insertOrderItem(OrderItem orderItem) {

        Integer i = oidi.insertOrderItem(orderItem);
        return i==1?true:false;
    }

    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:14
     * @param orderId
     * @param productId
     * @return com.myhd.pojo.OrderItem
     */
    @Override
    public OrderItem selectOrderItem(Integer orderId, Integer productId) {

        OrderItem item = oidi.selectOrderItem(orderId, productId);
        return item;
    }

    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:15
     * @param orderId
     * @return java.util.List<com.myhd.pojo.OrderItem>
     */
    @Override
    public List<OrderItem> selectAllOrderItem(Integer orderId) {

        List<OrderItem> list = oidi.selectAllOrderItem(orderId);
        return list.isEmpty()?new ArrayList<>():list;
    }

    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:17
     * @param orderItem
     * @return java.lang.Boolean
     */
    @Override
    public Boolean updateOrderItem(OrderItem orderItem) {

        Integer i = oidi.updateOrderItem(orderItem);
        return i==1?true:false;
    }

    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:23
     * @param product
     * @return java.lang.Double
     */
    @Override
    public Double countByProductId(Product product) {

        Double aDouble = oidi.countByProductId(product);
        return aDouble;
    }
}
