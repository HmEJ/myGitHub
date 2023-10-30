import com.myhd.dto.FormDTO;
import com.myhd.entity.User;
import com.myhd.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Classname UserMapperTest
 * @Description TODO
 * @Date 2023/10/24 下午1:46
 * @Created by joneelmo
 */
@Slf4j
@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void saveUserTest(){
        val user = new User();
        user.setUserId(2);
        user.setUserName("jonlmo");
        user.setPassword("1256");
        user.setPhone("115");
        user.setEmail("sdf@dfsd.com");
        System.out.println(userMapper.saveUser(user));
    }

    @Test
    public void isUserExistTest(){
        val loginFormDTO = new FormDTO();
        loginFormDTO.setPhone("115");
        val userExist = userMapper.isUserExist(loginFormDTO);
        log.info(String.valueOf(userExist));
    }

    @Test
    public void findByArgsTest(){
        val loginFormDTO = new FormDTO();
        loginFormDTO.setPhone("115");
//        loginFormDTO.setUserEmail("sdf@dfsd.com");
        val user = userMapper.findByArgs(loginFormDTO);
        System.out.println(user.getPassword());
    }

    @Test
    public void updateUserPasswordTest(){
        val user = new User();
        user.setPassword("abc");
        user.setEmail("sdf@dfsd.com");
        userMapper.updateUserPassword(user);
    }

}
