package com.myhd.service.impl;

import com.myhd.dao.impl.OrderDaoImpl;
import com.myhd.dao.impl.OrderItemDaoImpl;
import com.myhd.pojo.OrderItem;
import com.myhd.pojo.Product;
import com.myhd.service.IOrderItemService;

import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceImpl implements IOrderItemService {
    /**
     * @description 
     * @author JoneElmo
     * @date 2023-08-20 14:14
     * @version 1.0
     * @package com.myhd.service.impl
     * @class OrderItemServiceImpl
     */
    private OrderItemDaoImpl oidi = new OrderItemDaoImpl();
    @Override
    public Boolean insertOrderItem(OrderItem orderItem) {
        /** 
         * @description 
         * @author JoneElmo
         * @date 2023-08-20 14:14 
         * @param orderItem
         * @return java.lang.Boolean
         */ 
        Integer i = oidi.insertOrderItem(orderItem);
        return i==1?true:false;
    }

    @Override
    public OrderItem selectOrderItem(Integer orderId, Integer productId) {
        /** 
         * @description 
         * @author JoneElmo
         * @date 2023-08-20 14:14 
         * @param orderId
         * @param productId
         * @return com.myhd.pojo.OrderItem
         */
        OrderItem item = oidi.selectOrderItem(orderId, productId);
        return item;
    }

    @Override
    public List<OrderItem> selectAllOrderItem(Integer orderId) {
        /** 
         * @description 
         * @author JoneElmo
         * @date 2023-08-20 14:15 
         * @param orderId
         * @return java.util.List<com.myhd.pojo.OrderItem>
         */
        List<OrderItem> list = oidi.selectAllOrderItem(orderId);
        return list.isEmpty()?new ArrayList<>():list;
    }

    @Override
    public Boolean updateOrderItem(OrderItem orderItem) {
        /** 
         * @description 
         * @author JoneElmo
         * @date 2023-08-20 14:17 
         * @param orderItem
         * @return java.lang.Boolean
         */
        Integer i = oidi.updateOrderItem(orderItem);
        return i==1?true:false;
    }

    @Override
    public Double countByProductId(Product product) {
        /** 
         * @description 
         * @author JoneElmo
         * @date 2023-08-20 14:23
 * @param product
         * @return java.lang.Double
         */ 
        Double aDouble = oidi.countByProductId(product);
        return aDouble;
    }
}
