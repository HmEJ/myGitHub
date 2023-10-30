import com.myhd.dto.FormDTO;
import com.myhd.entity.User;
import com.myhd.service.IUserService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Classname UserServiceImplTest
 * @Description
 * @Date 2023/10/26 上午9:57
 * @Created by joneelmo
 */
@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class UserServiceImplTest {
    @Resource
    IUserService userService;

    @Test
    void registerUser(){
        val user = new User();
        user.setEmail("tttest");
        user.setUserName("tttest");
        user.setPassword("ttttest");
        System.out.println(userService.registerUser(user));
    }

    @Test
    void loginUser(){
        val formDTO = new FormDTO();
        formDTO.setUserEmail("mhhhhh@gmail.com");
        formDTO.setPassword("123123");
        System.out.println(userService.loginUser(formDTO));
    }

    @Test
    void changePassword(){
        val user = new User();
        user.setPassword("88888");
        user.setPhone("11sfsdfdsf5");
        System.out.println(userService.changePassword(user));
    }

}
