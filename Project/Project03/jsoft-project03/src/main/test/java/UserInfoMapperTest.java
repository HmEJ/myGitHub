import com.myhd.entity.UserInfo;
import com.myhd.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname UserInfoMapperTest
 * @Description TODO
 * @Date 2023/10/24 下午3:38
 * @Created by joneelmo
 */
@Slf4j
@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class UserInfoMapperTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void saveUserInfoTest(){
        val userInfo = new UserInfo();
        userInfo.setWorkExp(1);
        userInfo.setUserId(200005);
        userInfo.setName("啊航");
        System.out.println(userInfoMapper.saveUserInfo(userInfo));
    }

    @Test
    public void getUserInfoByIdTest(){
        System.out.println(userInfoMapper.getUserInfoById(200005).getName());
    }
}
