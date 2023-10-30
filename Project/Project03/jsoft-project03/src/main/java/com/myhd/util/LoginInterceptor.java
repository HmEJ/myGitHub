package com.myhd.util;

import com.myhd.dto.FormDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: LoginInterceptor 判断对应的请求是否有用户对象
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
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * Description: preHandle 前置拦截器
     * @return boolean
     * @author jinhui-huang
     * @Date 2023/10/6
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*1. 判断是否需要去拦截(ThreadLocal中是否有用户)*/
        if (UserHolder.getUser(FormDTO.class) == null) {
            /*没有, 需要拦截, 设置状态码*/
            response.setStatus(401);
            /*拦截返回false*/
            return false;
        }
        /*有用户, 则放行*/
        return true;
    }
}
