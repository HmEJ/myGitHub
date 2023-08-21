package com.myhd;

import com.myhd.dao.impl.*;
import com.myhd.pojo.*;
import com.myhd.service.impl.*;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @description 一级菜单
 * @author JoneElmo
 * @date 2023-08-18 17:27
 * @version 1.0
 * @package com.myhd
 * @class Main
 */
class Main {
    static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        /**
         * @description 主方法
         * @author JoneElmo
         * @date 2023-08-20 15:06
         * @param args
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("************************************************");
        System.out.println("*****************超市收银系统********************");
        System.out.println("************************************************");
        System.out.println("________________________________________________");
        System.out.println("|| 1.注册 | 2.登录  | 0.退出                   ||");
        System.out.println("|| 请输入您的操作序列号：👇                      ||");
        System.out.println("————————————————————————————————————————————————");
        String input = null;
        /*死循环 监听输入 为0则退出 非法字符则重新输入*/
        while (!"0".equals(input = in.next())){
            switch (input){
                case "1":
                    Register();
                    break;
                case "2":
                    Login();
                    break;
                default:
                    logger.error("字符非法！\n 请重新输入:");
                    continue;
            }
        }
        System.exit(0);
    }
    public static void Register(){
        /**
         * @description 注册方法
         * @author JoneElmo
         * @date 2023-08-20 16:44
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("*****************👉账户注册👈********************");
        System.out.println("________________________________________________");
        System.out.println("|| 请输入您想使用的用户名：👇                     ||");
        System.out.println("————————————————————————————————————————————————");
        String username = in.next();
        System.out.println("________________________________________________");
        System.out.println("|| 请设置您的密码：👇                        ||");
        System.out.println("————————————————————————————————————————————————");
        String password = in.next();
        EmpUser user = new EmpUser();
        user.setEmpUserName(username);
        user.setEmpUserPwd(password);
        user.setEmpUserDuty("普通员工");
        EmpUserServiceImpl service = new EmpUserServiceImpl();
        Boolean aBoolean = service.RegisterService(user);
        if (aBoolean){
            logger.info("注册成功!");
            System.out.println("请在上方info信息中牢记您的id号,\n此id号将用于日后登录操作.");
            System.out.println("输入1返回主菜单，输入0退出程序");
            String next = in.next();
            switch (next){
                case "1":
                    main(new String[1]);
                case "0":
                    System.exit(0);
            }
        }
    }
    public static void Login(){
        /**
         * @description 登陆方法
         * @author JoneElmo
         * @date 2023-08-20 16:44
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("*****************👉账户登录👈********************");
        System.out.println("________________________________________________");
        System.out.println("|| 请输入账号：👇                     ||");
        System.out.println("————————————————————————————————————————————————");
        int id = in.nextInt();
        if (id==100001){
            System.out.println("________________________________________________");
            System.out.println("|| 已进入管理员系统，请输入管理密码：👇      ||");
            System.out.println("————————————————————————————————————————————————");
            String password = in.next();
            EmpUser user1 = new EmpUser();
            user1.setEmpUserId(id);
            user1.setEmpUserPwd(password);
            EmpUserDaoImpl empUserDao = new EmpUserDaoImpl();
            EmpUser user2 = empUserDao.selectEmpUSerByInfo(user1);
            if (user2.getEmpUserName()==null){
                logger.error("密码错误，登陆失败！");
                Login();
            }else {
                logger.info("登录成功!");
                Main2.menu3();
            }
        }
        System.out.println("________________________________________________");
        System.out.println("|| 请输入密码：👇                     ||");
        System.out.println("————————————————————————————————————————————————");
        String password = in.next();

        EmpUser user = new EmpUser();
        user.setEmpUserId(id);
        user.setEmpUserPwd(password);
        EmpUserServiceImpl userService = new EmpUserServiceImpl();
        Boolean aBoolean = userService.LoginService(user);
        if (aBoolean){
            Main2.menu();
        }else {
            Main.Login();
        }
    }
}
/**
 * @description 二级菜单
 * @author JoneElmo
 * @date 2023-08-20 19:44
 * @version 1.0
 * @package com.myhd
 * @class Main2
 */
class Main2{
    static HashMap<ArrayList<Integer>, OrderItem> shoppingCar = new HashMap<>();

    static Logger logger = Logger.getLogger(Main2.class);
    public static void menu(){
        Scanner in = new Scanner(System.in);
        System.out.println("*****************👉欢迎您进入收银系统👈*****************");
        System.out.println("________________________________________________");
        System.out.println("|| 0.返回主菜单   | 1.商品列表   | 2.活动列表    ||");
        System.out.println("|| 3.商品结账     | 4.商品退货                  ||");
        System.out.println("|| 请输入您的操作序列号：👇                      ||");
        System.out.println("————————————————————————————————————————————————");
        String input = null;
        /*死循环 监听输入 为0则退出 非法字符则重新输入*/
        while (true){
            switch (input=in.next()){
                case "0":
                    Main.main(new String[1]);
                    break;
                case "1":
                    Main3.showCommodity();
                    break;
                case "2":
                    Main3.showActivity();
                    break;
                case "3":
                    checkBill();
                    break;
                case "4":
                    returnGoods();
                    break;
                default:
                    logger.error("字符非法！\n 请重新输入:");
                    continue;
            }
        }
    }

    static void returnGoods() {
        /**
         * @description 退货
         * 结账时已经生成订单更新到数据库
         * @author JoneElmo
         * @date 2023-08-20 21:20
         * @return void
         */
        Scanner in = new Scanner(System.in);
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderItemServiceImpl orderItemService = new OrderItemServiceImpl();
        System.out.println("请输入要退货的订单编号: ");
        int orderId = in.nextInt();
        /*查到该订单信息(订单总额，订单时间)*/
        Order order = orderService.selectByOrderId(orderId);
        /*查找到该订单id关联的所有订单项(订单id，产品id，产品名，产品数量，产品单价，产品总价)*/
        List<OrderItem> orderItems = orderItemService.selectAllOrderItem(orderId);
        System.out.println("当前订单编号对应的所有订单项信息：");
        Iterator<OrderItem> iterator = orderItems.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        HashMap<ArrayList<Integer>, OrderItem> map = new HashMap<>();

        Iterator<OrderItem> iterator1 = orderItems.iterator();
        while (iterator1.hasNext()){
            ArrayList<Integer> list = new ArrayList<>();
            OrderItem next = iterator1.next();
            list.add(next.getOrderId());
            list.add(next.getProductId());
            map.put(list,next);
        }
        while (true){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(orderId);
            System.out.println("请输入要退货的产品编号(输入0退出)：");
            int productId = in.nextInt();
            if (productId==0){
                break;
            }
            list.add(productId);
            ProductServiceImpl productService = new ProductServiceImpl();
            Product product = productService.selectByproductId(productId);
            OrderItem orderItem = new OrderItem(orderId, productId, product.getProductName(), 0, product.getProductPrice(), 0.0);
            map.put(list,orderItem);
        }
        logger.info("当前订单项列表:");
        Iterator<Map.Entry<ArrayList<Integer>, OrderItem>> iterator2 = map.entrySet().iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
        ArrayList<OrderItem> list = new ArrayList<>();
        Iterator<Map.Entry<ArrayList<Integer>, OrderItem>> iterator3 = map.entrySet().iterator();
        while (iterator3.hasNext()){
            OrderItem value = iterator3.next().getValue();
            list.add(value);
        }
        orderService.returnOfGoods(order, list);
        logger.info("退货成功！");
        logger.info("正在返回上级菜单...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Main2.menu();
    }

    static void checkBill() {
        /**
         * @description 结账
         * @author JoneElmo
         * @date 2023-08-20 19:00
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("*****************👉结账页面👈*****************");
        System.out.println("________________________________________________");
        System.out.println("|| 0.返回上一级   | 1.添加商品   | 2.移除商品    ||");
        System.out.println("|| 3.生成订单                                   ||");
        System.out.println("|| 请输入您的操作序列号：👇                      ||");
        System.out.println("————————————————————————————————————————————————");
        String input = in.next();
        switch (input){
            case "0":
                Main2.menu();
                break;
            case "1":
                Integer orderId = null;
                Order order = new Order();
                if (shoppingCar.isEmpty()){
                    order.setOrderDateTime(LocalDateTime.now());
                    OrderDaoImpl orderDao = new OrderDaoImpl();
                    orderId = orderDao.insertOrder(order);
                    order.setOrderId(orderId);
                }
                addCommodity(order);
                break;
            case "2":
                logger.info("请先添加商品再移除！");
                checkBill();
                break;
            case "3":
                logger.info("请先添加商品再生成订单!");
                checkBill();
                break;
        }
    }

    static void makeBill(Order order) {
        /**
         * @description 生成订单
         * @author JoneElmo
         * @date 2023-08-21 08:44
         * @return void
         */
        Scanner in = new Scanner(System.in);
        List<OrderItem> list= new ArrayList<>();
        OrderServiceImpl orderService = new OrderServiceImpl();
        OrderItemServiceImpl orderItemService = new OrderItemServiceImpl();
        Iterator<Map.Entry<ArrayList<Integer>, OrderItem>> iterator = shoppingCar.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<ArrayList<Integer>, OrderItem> next = iterator.next();
            OrderItem value = next.getValue();
            orderItemService.insertOrderItem(value);
            list.add(value);
        }
        Order order1 = orderService.billPlease(order, list);
        logger.info("订单生成完毕.信息如下：");
        logger.info(order1);
        System.out.println("输入0返回上一级：");
        String a = in.next();
        if ("0".equals(a)){
            Main2.menu();
        }
    }

    static void removeCommodity(Order order) {
        /** 
         * @description 移除商品
         * @author JoneElmo
         * @date 2023-08-21 08:23 
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("你购物车中的商品是：");
        /*显示购物车*/
        Set<Map.Entry<ArrayList<Integer>, OrderItem>> entries = shoppingCar.entrySet();
        Iterator<Map.Entry<ArrayList<Integer>, OrderItem>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<ArrayList<Integer>, OrderItem> next = iterator.next();
            System.out.println(next);
        }

        System.out.println("输入您想移除的商品id：");
        int commodityId = in.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(order.getOrderId());
        list.add(commodityId);

        shoppingCar.remove(list);
        System.out.println("当前购物车中的商品是：");
        /*显示购物车*/
        Set<Map.Entry<ArrayList<Integer>, OrderItem>> entrySet = shoppingCar.entrySet();
        Iterator<Map.Entry<ArrayList<Integer>, OrderItem>> it = entries.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("________________________________________________");
        System.out.println("|| 0.返回上一级   | 1.添加商品   | 2.移除商品    ||");
        System.out.println("|| 3.生成订单                                 ||");
        System.out.println("————————————————————————————————————————————————");
        String input = in.next();
        switch (input){
            case "0":
                Main2.menu();
                break;
            case "1":
                addCommodity(order);
                break;
            case "2":
                removeCommodity(order);
                break;
            case "3":
                makeBill(order);
                break;
        }
    }

    static void addCommodity(Order order) {
        /** 
         * @description 添加商品
         * @author JoneElmo
         * @date 2023-08-21 08:23 
         * @return void
         */
        while (true){
            ArrayList<Integer> list = new ArrayList<>();
            ProductServiceImpl productService = new ProductServiceImpl();
            Scanner in = new Scanner(System.in);
            System.out.println("________________________________________________");
            System.out.println("|| 添加商品页面：👇                            ||");
            System.out.println("————————————————————————————————————————————————");
            System.out.println("请输入您要添加的商品编号：");
            int id = in.nextInt();
            System.out.println("请输入要添加该商品的数量：");
            int count = in.nextInt();
            /*获取商品信息：编号，名称，单价*/
            Product product = productService.selectByproductId(id);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setProductName(product.getProductName());
            orderItem.setProductId(product.getProductId());
            orderItem.setProductPrice(product.getProductPrice());
            orderItem.setProductCount(count);
            orderItem.setProductTotal(product.getProductPrice()*count);

            /*往购物车添加内容 购物车 k=ArrayList<Integer> v=orderItem*/
            list.add(order.getOrderId());
            list.add(product.getProductId());
            if (orderItem.getProductCount()!=0){
                shoppingCar.put(list, orderItem);
            }else {
                shoppingCar.remove(list);
            }
            System.out.println("你的添加的商品是：");
            /*显示购物车*/
            Set<Map.Entry<ArrayList<Integer>, OrderItem>> entries = shoppingCar.entrySet();
            Iterator<Map.Entry<ArrayList<Integer>, OrderItem>> iterator = entries.iterator();
            while (iterator.hasNext()){
                Map.Entry<ArrayList<Integer>, OrderItem> next = iterator.next();
                System.out.println(next);
            }
            System.out.println("________________________________________________");
            System.out.println("|| 0.返回上一级  | 1.修改商品数量   | 2.移除商品 ||");
            System.out.println("|| 3.生成订单                                 ||");
            System.out.println("————————————————————————————————————————————————");
            String input = in.next();
            switch (input){
                case "0":
                    Main2.menu();
                    break;
                case "1":
                    continue;
                case "2":
                    removeCommodity(order);
                    break;
                case "3":
                    makeBill(order);
                    break;
            }
        }

    }

    public static void menu3() {
        /**
         * @description 二级菜单---管理员
         * @author JoneElmo
         * @date 2023-08-20 19:37
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("*****************👉欢迎您,管理员👈*****************");
        System.out.println("________________________________________________");
        System.out.println("|| 0.返回主菜单   | 1.添加活动   | 2.修改活动    ||");
        System.out.println("|| 3.删除活动     | 4.对账                      ||");
        System.out.println("|| 请输入您的操作序列号：👇                      ||");
        System.out.println("————————————————————————————————————————————————");
        String i = in.next();
        switch (i){
            case "0":
                Main.main(new String[1]);
                break;
            case "1":
                Main3.addActivity();
                break;
            case "2":
                Main3.modifyActivity();
                break;
            case "3":
                Main3.deleteActivity();
                break;
            case "4":
                Main3.checkout();
                break;
            default:
                logger.error("字符非法！\n请重新输入:");
        }
    }
}
/**
 * @description 三级菜单
 * @author JoneElmo
 * @date 2023-08-20 19:43
 * @version 1.0
 * @package com.myhd
 * @class Main3
 */
class Main3{
    static Logger logger = Logger.getLogger(Main3.class);

    static void showActivity() {
        /**
         * @description 活动列表
         * @author JoneElmo
         * @date 2023-08-20 19:00
         * @return void
         */
        ActivityServiceImpl service = new ActivityServiceImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("想查看第几页?\n 请输入:");
        int page = in.nextInt();
        System.out.println("正在获取活动列表...");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("*****************👇当前活动列表👇*****************");
        List<Activity> list = service.selectActivity(page, 5);
        Iterator<Activity> iterator = list.iterator();
        System.out.println("活动编号\t活动名称\t\t\t\t\t\t\t活动开始日期\t\t活动结束日期");
        while (iterator.hasNext()){
            Activity next = iterator.next();
            System.out.println(next.getActivityId()+"\t"+next.getActivityName()+"\t"+next.getStartDate()+"\t"+next.getEndDate());
        }
        System.out.println("*****************当前是第"+page+"页***************");
        System.out.println("*****************👆当前活动列表👆*****************");
        System.out.println("0.返回上一级 1.继续查看");
        int input = in.nextInt();
        switch (input){
            case 1:
                showActivity();
                break;
            case 0:
                Main2.menu();
                break;
        }
    }

    static void showCommodity() {
        /**
         * @description 展示商品
         * @author JoneElmo
         * @date 2023-08-20 19:00
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("*****************👉当前仓库商品列表👈*****************");
        System.out.println("商品编号\t商品名\t\t\t商品单价");
        ProductDaoImpl productDao = new ProductDaoImpl();
        List<Product> products = productDao.selectAllProduct();
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product next = iterator.next();
            System.out.println(next.getProductId()+"\t"+next.getProductName()+"\t\t\t"+next.getProductPrice());
        }
        System.out.println("***********************************************");
        System.out.println("输入0返回上一层,请输入：");
        String input = in.next();
        if ("0".equals(input)){
            Main2.menu();
        }
    }

    static void addActivity() {
        /**
         * @description 添加活动
         * @author JoneElmo
         * @date 2023-08-20 20:05
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        System.out.println("|| 活动添加页面：👇                          ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("请设置活动名：");
        String activityName = in.next();
        System.out.println("请设置满减金额：");
        double critical = in.nextDouble();
        System.out.println("请设置折扣：(范围0~1)");
        double discount = in.nextDouble();
        System.out.println("设置活动开始日期：");
        Date start = Date.valueOf(in.next());
        System.out.println("设置活动结束日期：");
        Date end = Date.valueOf(in.next());

        Activity activity = new Activity();
        activity.setActivityName(activityName);
        activity.setCriticalTotal(critical);
        activity.setDiscount(discount);
        activity.setStartDate(start);
        activity.setEndDate(end);

        ActivityServiceImpl service = new ActivityServiceImpl();
        Boolean aBoolean = service.insertActivity(activity);
        if (aBoolean){
            logger.info("活动添加成功");
        }
        System.out.println("***********************************************");
        System.out.println("输入0返回上一层,请输入：");
        String input = in.next();
        if ("0".equals(input)){
            Main2.menu3();
        }
    }

    static void modifyActivity() {
        /**
         * @description 修改活动
         * @author JoneElmo
         * @date 2023-08-20 20:05
         * @return void
         */
        ActivityServiceImpl service = new ActivityServiceImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        System.out.println("|| 活动修改页面：👇                          ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("请输入要修改的活动id");
        int id = in.nextInt();
        /**/
        ActivityDaoImpl activityDao = new ActivityDaoImpl();
        Activity activity = activityDao.selectActivityById(id);
        /**/
        System.out.println("该活动名："+activity.getActivityName());
        System.out.println("请修改活动名：");
        String activityName = in.next();
        System.out.println("该活动满减金额："+activity.getCriticalTotal());
        System.out.println("请修改满减金额：");
        double critical = in.nextDouble();
        System.out.println("该活动折扣："+activity.getDiscount());
        System.out.println("请修改折扣：(范围0~1)");
        double discount = in.nextDouble();
        System.out.println("该活动开始日期："+activity.getStartDate());
        System.out.println("修改活动开始日期：");
        Date startDate = Date.valueOf(in.next());
        System.out.println("该活动结束日期："+activity.getEndDate());
        System.out.println("修改活动结束日期：");
        Date endDate = Date.valueOf(in.next());

        Activity activity1 = new Activity();
        activity1.setActivityId(id);
        activity1.setActivityName(activityName);
        activity1.setCriticalTotal(critical);
        activity1.setDiscount(discount);
        activity1.setStartDate(startDate);
        activity1.setEndDate(endDate);

        Boolean aBoolean = service.updateActivity(activity1);
        if (aBoolean){
            logger.info("修改成功");
        }else {
            logger.error("修改失败");
        }
        System.out.println("***********************************************");
        System.out.println("输入0返回上一层,请输入：");
        String input = in.next();
        if ("0".equals(input)){
            Main2.menu3();
        }
    }

    public static void deleteActivity() {
        ActivityServiceImpl service = new ActivityServiceImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        System.out.println("|| 活动删除页面：👇                          ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("请输入要删除的活动id：");
        int id = in.nextInt();
        ActivityDaoImpl activityDao = new ActivityDaoImpl();
        Activity activity = activityDao.selectActivityById(id);
        System.out.println("你要删除的活动信息如下：");
        System.out.println(activity);
        System.out.println("确认删除吗?(Y/N)");
        String confim = in.next();
        if ("Y".equalsIgnoreCase(confim)){
            service.deleteActivity(id);
            logger.info("删除成功.");
        }
        System.out.println("***********************************************");
        System.out.println("输入0返回上一层,请输入：");
        String input = in.next();
        if ("0".equals(input)){
            Main2.menu3();
        }
    }

    public static void checkout() {
        /**
         * @description 对账
         * @author JoneElmo
         * @date 2023-08-20 22:51
         * @return void
         */
        Scanner in = new Scanner(System.in);
            /**/
        System.out.println("________________________________________________");
        System.out.println("|| 0.返回上一级  | 1.账户查询 | 2.按时间统计     ||");
        System.out.println("|| 3.按商品统计                                ||");
        System.out.println("|| 请输入您的操作序列号：👇                      ||");
        System.out.println("————————————————————————————————————————————————");
        String input = in.next();
        switch (input) {
            case "0":
                Main2.menu3();
                break;
            case "1":
                Main4.selectAccount();
                break;
            case "2":
                Main4.selectByTime();
                break;
            case "3":
                Main4.selectByCommodity();
                break;
            default:
                logger.error("字符非法！\n请重新输入:");
        }
    }
}
/**
 * @description 四级菜单
 * @author JoneElmo
 * @date 2023-08-20 23:19
 * @version 1.0
 * @package com.myhd
 * @class Main4
 */
class Main4{
    static Logger logger = Logger.getLogger(Main4.class);
    public static void selectAccount() {
        /**
         * @description 查询账户余额
         * @author JoneElmo
         * @date 2023-08-20 23:19
         * @return void
         */
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        System.out.println("|| 查询账户余额页面：👇                           ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("============敏感操作，需要二次验证============");
        System.out.println("请输入账户密码：");
        String password = in.next();
        if (!"administrator".equals(password)){
            logger.error("密码错误！将自动返回上一层");
            Main3.checkout();
        }else {
            AccountServiceImpl accountService = new AccountServiceImpl();
            Account account = new Account();
            account.setAccountPwd(password);
            Double money = accountService.selectAccount(account);
            System.out.println("________________________________________________");
            System.out.println("|| 目前账户总金额是：￥"+money+"                ||");
            System.out.println("————————————————————————————————————————————————");
            System.out.println("输入0返回上一层,请输入：");
            String input = in.next();
            if ("0".equals(input)){
                Main3.checkout();
            }
        }
    }

    public static void selectByTime() {
        /**
         * @description 按时间查账
         * @author JoneElmo
         * @date 2023-08-20 23:35
         * @return void
         */
        // TODO: 2023/8/21 021 时间转换有问题
        OrderServiceImpl orderService = new OrderServiceImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        System.out.println("|| 按时间查账页面：                            ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("请输入开始日期(格式2023-08-20):");
        String start = in.nextLine();
        System.out.println("请输入结束日期(格式2023-08-21):");
        String end = in.nextLine();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(start+" 00:00:00", df);
        LocalDateTime endTime = LocalDateTime.parse(end+" 23:59:59", df);

        Double money = orderService.countMoneyByDate(startTime, endTime);
        System.out.println("________________________________________________");
        System.out.println("|| 这段时间的账户总账：￥"+money+"              ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("输入0返回上一层,请输入：");
        String input = in.next();
        if ("0".equals(input)){
            Main3.checkout();
        }
    }

    public static void selectByCommodity() {
        /**
         * @description 根据商品查账
         * @author JoneElmo
         * @date 2023-08-20 23:41
         * @return void
         */
        OrderItemServiceImpl orderItemService = new OrderItemServiceImpl();
        Scanner in = new Scanner(System.in);
        System.out.println("________________________________________________");
        System.out.println("|| 按商品查账页面：                            ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("请输入商品id号：");
        int id = in.nextInt();
        Product product = new Product();
        product.setProductId(id);
        Double money = orderItemService.countByProductId(product);
        System.out.println("________________________________________________");
        System.out.println("|| 这个商品的收益是：￥"+money+"                 ||");
        System.out.println("————————————————————————————————————————————————");
        System.out.println("输入0返回上一层,请输入：");
        String input = in.next();
        if ("0".equals(input)){
            Main3.checkout();
        }
    }
}