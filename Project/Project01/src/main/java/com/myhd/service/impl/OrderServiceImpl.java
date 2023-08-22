package com.myhd.service.impl;

import com.myhd.dao.impl.OrderDaoImpl;
import com.myhd.dao.impl.OrderItemDaoImpl;
import com.myhd.pojo.Account;
import com.myhd.pojo.Order;
import com.myhd.pojo.OrderItem;
import com.myhd.service.IOrderService;
import org.apache.log4j.Logger;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
/**
 * @description
 * @author JoneElmo
 * @date 2023-08-20 14:34
 * @version 1.0
 * @package com.myhd.service.impl
 * @class OrderServiceImpl
 */
public class OrderServiceImpl implements IOrderService {
    Logger logger = Logger.getLogger(OrderServiceImpl.class);
    OrderItemServiceImpl ois = new OrderItemServiceImpl();


    private OrderDaoImpl odi = new OrderDaoImpl();
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:34
     * @param order
     * @return java.lang.Integer
     */
    @Override
    public Integer InsertOrder(Order order) {

        Integer i = odi.insertOrder(order);
        return i;
    }
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 14:34
     * @param orderId
     * @return com.myhd.pojo.Order
     */
    @Override
    public Order selectByOrderId(Integer orderId) {

        Order order = odi.selectByOrderId(orderId);
        return order;
    }
    /**
     * @description  退货业务 order订单对象 orderItems 包含该订单的所有订单项。
     * @author JoneElmo
     * @date 2023-08-20 14:34
     * @param order
     * @param orderItems
     * @return com.myhd.pojo.Order
     */
    @Override
    public Order returnOfGoods(Order order, List<OrderItem> orderItems) {

        double count = 0.0;
        Iterator<OrderItem> iterator = orderItems.iterator();
        /*遍历该订单项，将订单项更新到数据库。并获取订单项的金额*/
        while (iterator.hasNext()){
            OrderItem next = iterator.next();
            ois.updateOrderItem(next);
            count += next.getProductTotal();
        }
        ActivityServiceImpl service = new ActivityServiceImpl();
        /*获取折扣*/
        Double discount = service.selectDiscount(order.getOrderDateTime(), count);
        /*折扣后的价钱*/
        Double newPrice = count*discount;
        AccountServiceImpl accountService = new AccountServiceImpl();
        Account account = new Account();
        account.setAccountPwd("administrator");
        /*账户原金额*/
        Double p = accountService.selectAccount(account);
        Boolean aBoolean = accountService.updateAccount(newPrice-order.getOrderTrueTotal()); //money要更改的金额
        OrderDaoImpl orderDao = new OrderDaoImpl();
        order.setOrderId(order.getOrderId());
        order.setOrderDateTime(order.getOrderDateTime());
        order.setOrderTotal(count);
        order.setOrderTrueTotal(newPrice);
        Integer integer = orderDao.updateOrder(order);
        if (integer==1){
            logger.info("更新订单成功");
        }else {
            logger.info("更新订单失败");
        }
        return order;
    }
    /**
     * @description 结账
     * @author JoneElmo
     * @date 2023-08-20 21:30
     * @param order
     * @param orderItems
     * @return com.myhd.pojo.Order
     */
    @Override
    public Order billPlease(Order order, List<OrderItem> orderItems) {

        Iterator<OrderItem> iterator = orderItems.iterator();
        double count = 0.0;
        while (iterator.hasNext()){
            OrderItem next = iterator.next();
            Double total = next.getProductTotal();
            count+=total;
        }
        LocalDateTime time = order.getOrderDateTime();
        ActivityServiceImpl service = new ActivityServiceImpl();
        Double discount = service.selectDiscount(time, count);
        Double trueTotal = discount*count;
        order.setOrderTotal(count);
        order.setOrderTrueTotal(trueTotal);
        /*更新数据库*/
        OrderDaoImpl orderDao = new OrderDaoImpl();

        AccountServiceImpl accountService = new AccountServiceImpl();
        accountService.updateAccount(trueTotal);
        orderDao.updateOrder(order);
        return order;
    }
    /**
     * @description 按照日期查账
     * @author JoneElmo
     * @date 2023-08-20 15:40
     * @param startTime
     * @param endTime
     * @return java.lang.Double
     */
    @Override
    public Double countMoneyByDate(LocalDateTime startTime, LocalDateTime endTime) {

        Double aDouble = odi.countMoneyByDate(startTime, endTime);
        return aDouble;
    }
}
