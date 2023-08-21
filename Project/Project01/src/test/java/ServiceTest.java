import com.myhd.dao.impl.OrderItemDaoImpl;
import com.myhd.pojo.*;
import com.myhd.service.IAccountService;
import com.myhd.service.impl.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

public class ServiceTest {
    private Logger logger = Logger.getLogger(ServiceTest.class);

    public ServiceTest() {
    }

    @Test
    public void AccountServiceImplTest() {
        IAccountService accountService = new AccountServiceImpl();
        Account account = new Account();
        account.setAccountPwd("qwer888888");
        Boolean aBoolean = accountService.updateAccount(0.0D);
        System.out.println(aBoolean);
        Double aDouble = accountService.selectAccount(account);
        System.out.println(aDouble);
    }

    @Test
    public void ActivityServiceImplTest() {
        ActivityServiceImpl service = new ActivityServiceImpl();
        List<Activity> list = service.selectActivity(1, 3);
        System.out.println(list);
        Activity activity = new Activity();
        activity.setActivityName("test");
        activity.setCriticalTotal(20.0D);
        activity.setStartDate(Date.valueOf(LocalDate.now()));
        activity.setEndDate(Date.valueOf(LocalDate.now()));
        activity.setDiscount(0.2D);
    }

    @Test
    public void EmpUserServiceImplTest() {
        /**
         * @description 该实现类测试完毕，功能正常
         * @author JoneElmo
         * @date 2023-08-20 14:09
         * @return void
         */
        EmpUserServiceImpl service = new EmpUserServiceImpl();
        EmpUser user = new EmpUser();
        user.setEmpUserId(100002);
        user.setEmpUserPwd("12345677");
        service.LoginService(user);
        Boolean aBoolean = service.RegisterService(user);
        System.out.println(aBoolean);
    }
    @Test
    public void OrderItemServiceImplTest(){
        /**
         * @description 该实现类测试完成
         * @author JoneElmo
         * @date 2023-08-20 14:32
         * @return void
         */
        OrderItemServiceImpl service = new OrderItemServiceImpl();
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(12);
        orderItem.setProductId(200002);
        orderItem.setProductName("百事可乐");
        orderItem.setProductCount(10);
        orderItem.setProductPrice(3.0);
        orderItem.setProductTotal(orderItem.getProductPrice()* orderItem.getProductCount());
//        System.out.println(service.insertOrderItem(orderItem));
//        System.out.println(service.selectOrderItem(11, 200002));
        Product product = new Product();
        product.setProductId(200002);
//        System.out.println(service.countByProductId(product));
//        System.out.println(service.selectAllOrderItem(11));

    }
    @Test
    public void OrderServiceImplTest(){
        OrderServiceImpl osi = new OrderServiceImpl();
        Double aDouble = osi.countMoneyByDate(LocalDateTime.of(2023, 8, 18, 0, 0), LocalDateTime.of(2023, 8, 30, 0, 0));
        System.out.println(aDouble);
    }
    @Test
    public void ProductServiceImplTest(){
        /**
         * @description 该实现类测试完成
         * @author JoneElmo
         * @date 2023-08-20 15:05
         * @return void
         */
        ProductServiceImpl psi = new ProductServiceImpl();
        Product product = psi.selectByproductId(200002);
        logger.info(product);
    }
}
