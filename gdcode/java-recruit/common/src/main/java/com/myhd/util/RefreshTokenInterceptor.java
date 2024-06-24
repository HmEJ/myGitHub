package com.myhd.util;

import com.myhd.entity.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: LoginInterceptor 用与判断所有请求中token的有效性
 * <br></br>
 * className: LoginInterceptor
 * <br></br>
 * packageName: com.myhd.utils
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/10/6 18:04
 */
@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Description: preHandle 前置拦截器
     * @return boolean
     * @author jinhui-huang
     * @Date 2023/10/6
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*TODO 1. 获取header中的token*/
        Object token = request.getHeader("authorization");

        /*TODO 2. 基于token获取redis中的用户*/

        /*TODO 3. 判断用户是否存在*/

        /*TODO 5. 存在, 保存用户信息到ThreadLocal*/
        UserHolder.saveUser(new User());
        /*TODO 6. 刷新token有效期*/

        return true;
    }

    /**
     * Description: afterCompletion 请求之后
     * @return void
     * @author jinhui-huang
     * @Date 2023/10/6
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
