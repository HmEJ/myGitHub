import com.myhd.dao.impl.*;
import com.myhd.pojo.*;

import java.time.LocalDateTime;
import java.util.List;

public class DaoTest {
    @org.junit.Test
    public void AccountDaoImplTest(){
        /**
         * @description 该实现类已经测试完成，功能正常。
         * @author JoneElmo
         * @date 2023-08-18 21:53
         * @return void
         */
        AccountDaoImpl ad = new AccountDaoImpl();
        Account account = new Account();
        account.setAccountId(1);
        account.setAccountPwd("qw8");
        Account account1 = ad.selectAccount(account);
        if (account1.getAccountPwd()==null){
            System.out.println("登录失败");
        }else {
            System.out.println("登陆成功");
        }
        System.out.println(ad.updateAccount(0.0));
    }
    @org.junit.Test
    public void ProductDaoImplTest(){
        /**
         * @description 该实现类已经测试完成。功能正常
         * @author JoneElmo
         * @date 2023-08-18 21:49
         * @return void
         */
        ProductDaoImpl productDao = new ProductDaoImpl();
        System.out.println(productDao.selectByproductId(200001));
        List<Product> products = productDao.selectAllProduct();
        System.out.println(products);
    }
    @org.junit.Test
    public void ActivityDaoImplTest(){
        /**
         * @description 该实现类增删改查已测，功能正常。
         * @author JoneElmo
         * @date 2023-08-18 21:46
         * @return void
         */
        ActivityDaoImpl ad = new ActivityDaoImpl();
        Activity activity = new Activity();
//        activity.setActivityName("!!!夏季促销全场满549六折优惠!!!");
//        activity.setCriticalTotal(549.0);
//        activity.setStartDate(Date.valueOf("2023-9-2"));
//        activity.setEndDate(Date.valueOf("2023-9-5"));
//        activity.setDiscount(0.6);
//        ad.insertActivity(activity);
        List<Activity> list = ad.selectActivity(1, 3);
        System.out.println("今日折扣："+list.get(0).getActivityName());
        System.out.println("起始日期："+list.get(0).getStartDate());
        System.out.println("终止日期："+list.get(0).getEndDate());
        System.out.println("请大家看清楚时间，择日选购");
        Double aDouble = ad.selectDiscount(LocalDateTime.now(), 565.0);
        System.out.println("您的本次折扣："+aDouble);
        Integer i = ad.deleteActivity(500007);
        if (i==0){
            System.out.println("删除失败。");
        }else {
            System.out.println("删除成功");
        }
        Integer integer = ad.countAllPages();
        System.out.println(integer);
    }
    @org.junit.Test
    public void EmpUserDaoImplTest(){
        /**
         * @description 该实现类已经测试完毕，功能正常。
         * @author JoneElmo
         * @date 2023-08-18 22:26
         * @return void
         */
        EmpUserDaoImpl edi = new EmpUserDaoImpl();
        EmpUser user = new EmpUser();
        user.setEmpUserId(100005);
        user.setEmpUserPwd("mh488df2df");
        EmpUser user1 = edi.selectEmpUSerByInfo(user);
        if (user1.getEmpUserPwd()==null){
            System.out.println("登陆失败");
        }else {
            System.out.println("登陆成功");
            System.out.println("您的账户信息："+user1);
        }
        Integer integer = edi.countEmpUserById(1000);
        if (integer==0){
            System.out.println("用户不存在");
        }else {
            System.out.println("用户已存在");
        }
    }
    @org.junit.Test
    public void OrderDaoImplTest(){
        /**
         * @description 该是实现类测试完成，功能正常
         * @author JoneElmo
         * @date 2023-08-18 22:42
         * @return void
         */
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Order order = new Order();
//        order.setOrderDateTime(LocalDateTime.now());
//        System.out.println(orderDao.insertOrder(order));
        order.setOrderId(2);
        order.setOrderTotal(150.0);
        order.setOrderTrueTotal(140.0);
        order.setOrderDateTime(LocalDateTime.of(2023, 9, 10, 12, 00));
//        orderDao.updateOrder(order);
//        System.out.println(orderDao.selectByOrderId(2));
        System.out.println(orderDao.countMoneyByDate(LocalDateTime.of(2023, 8, 10, 12, 00), LocalDateTime.of(2023, 9, 20, 0, 0)));
    }
    @org.junit.Test
    public void OrderItemDaoImplTest(){
        /**
         * @description 该实现类测试完毕，功能正常。
         * @author JoneElmo
         * @date 2023-08-18 23:07
         * @return void
         */
        OrderItemDaoImpl itemDao = new OrderItemDaoImpl();
        ProductDaoImpl productDao = new ProductDaoImpl();
        int orderId = 2;
        int productId =200002;
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setProductId(productId);
        orderItem.setProductName("乐事");
        orderItem.setProductCount(3);
        orderItem.setProductPrice(3.0);
        orderItem.setProductTotal(9.0);
//        itemDao.insertOrderItem(orderItem);
//        System.out.println(itemDao.selectOrderItem(1, 200001));
//        System.out.println(itemDao.updateOrderItem(orderItem));
        Product product = new Product();
        product.setProductId(200002);
        System.out.println(itemDao.countByProductId(product));
    }

}
