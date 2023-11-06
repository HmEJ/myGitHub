package com.myhd.config;

import com.myhd.util.LoginInterceptor;
import com.myhd.util.RefreshTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Description: MvcConfig
 * <br></br>
 * className: MvcConfig
 * <br></br>
 * packageName: com.myhd.config
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/10/6 18:22
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private RefreshTokenInterceptor refreshTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns( /*排除的路径*/
                        "/user/register",
                        "/user/forget",
                        "/user/code",
                        "/user/login"
                ).order(1);
        /*先执行*/
        registry.addInterceptor(refreshTokenInterceptor).excludePathPatterns()
                .addPathPatterns("/**").order(0);
    }
}
