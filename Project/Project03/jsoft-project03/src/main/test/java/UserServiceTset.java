import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.myhd.dto.FormDTO;
import com.myhd.dto.ToEmail;
import com.myhd.entity.User;
import com.myhd.exception.BusinessException;
import com.myhd.mapper.UserMapper;
import com.myhd.util.Code;
import com.myhd.util.EmailService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashMap;

import static com.myhd.util.AliSms.sendPhoneCode;

/**
 * @Classname UserServiceTset
 * @Description TODO
 * @Date 2023/10/25 下午7:15
 * @Created by joneelmo
 */

@SpringBootTest(classes = com.myhd.JsoftProject03Application.class)
public class UserServiceTset {

    FormDTO formDTO;

    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    private EmailService emailService;
    @Resource
    UserMapper userMapper;

    @BeforeEach
    void init() {
//        loginFormDTO = new LoginFormDTO();
//        loginFormDTO.setUserEmail("mhhhhh@gmail.com");
//        loginFormDTO.setPassword("123123");

        formDTO = new FormDTO();
//        formDTO.setPhone("17552654906");
        formDTO.setUserEmail("mhangggggg@qq.com");
    }

//    @Test
//    void registerUser(){
//        val user = new User();
//        /* user:cache:id */
//        String key = "user:cache:id";
//        val userId = stringRedisTemplate.opsForValue().increment(key);
//        assert userId!=null;
//        user.setUserId(userId.intValue());
//        String userName = "user_"+userId;
//        user.setUserName(userName);
//        if (loginFormDTO.getUserEmail()!=null){
//            user.setEmail(loginFormDTO.getUserEmail());
//        }
//        if(loginFormDTO.getPhone()!=null){
//            user.setPhone(loginFormDTO.getPhone());
//        }
//        user.setPassword(loginFormDTO.getPassword());
//
//        try {
//            val saved = userMapper.saveUser(user);
//            System.out.println(saved == 0 ? "注册失败" : "注册成功");
//        } catch (DuplicateKeyException e) {       //捕获持久化异常
//            stringRedisTemplate.opsForValue().increment(key,-1);  /* 出现异常回退redis数据 */
//            throw new BusinessException(Code.FAIL, "手机号或邮箱重复");
//        }
//    }

    @Test
    void selectUser() {
        /* 1.判断用户输入的信息 */
        val user = userMapper.findByArgs(formDTO);
//        if (user==null){
//            System.out.println("用户不存在");
//        }
        String key = "forget:user:" + user.getUserId();
        val map = new HashMap<String, String>();
//        BeanUtil.bean
//        stringRedisTemplate.opsForHash().put(key,"userName",user.getUserName());
//        stringRedisTemplate.opsForHash().put(key,"phone",user.getPhone()==null?"":user.getPhone());
//        stringRedisTemplate.opsForHash().put(key,"email",user.getEmail()==null?"":user.getEmail());
//        stringRedisTemplate.expire(key, Duration.ofMinutes(30L));
        val jsonStr = JSONUtil.toJsonStr(user);
        stringRedisTemplate.opsForValue().set(key, jsonStr);
    }


    @Test
    void sendCode() {
        String userName = formDTO.getUserName();
        String userId = "100008";
        String userKey = "forget:user:" + userId;
        String s = stringRedisTemplate.opsForValue().get(userKey);
//        System.out.println(s);
        String phone = JSONUtil.toBean(s, User.class).getPhone();
//        System.out.println(phone);
        String email = JSONUtil.toBean(stringRedisTemplate.opsForValue().get(userKey), User.class).getEmail() ;
        System.out.println(email);
//        System.out.println(0);
        if (StrUtil.isNotBlank(phone)) {
//            System.out.println(1);
            String key = "forget:code:" + phone;
            String verifyCode = RandomUtil.randomNumbers(6);
            stringRedisTemplate.opsForValue().set(key, verifyCode, Duration.ofMinutes(30L));
            try {
                sendPhoneCode(phone, verifyCode, true);
                System.out.println("短信验证码：" + verifyCode);
            } catch (Exception e) {
                System.out.println(2);
                throw new RuntimeException(e);
            }
        } else if (StrUtil.isNotBlank(email)) {
            System.out.println("发邮件ing...");
            String key = "forget:code:" + email;
            String verifyCode = RandomUtil.randomNumbers(6);
            stringRedisTemplate.opsForValue().set(key,verifyCode);
            val toEmail = new ToEmail();
            toEmail.setToUser(email);
            toEmail.setContent(verifyCode);
//            System.out.println(toEmail);
            val b = emailService.sendEmail(toEmail);
            if (b){
                System.out.println("邮件验证码：" + verifyCode);
            }
        }
//        System.out.println(3);
    }
}
