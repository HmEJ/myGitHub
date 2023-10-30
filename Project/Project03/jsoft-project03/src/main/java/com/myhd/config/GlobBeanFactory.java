package com.myhd.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: GlobBeanFactory 全局bean工厂类
 * <br></br>
 * className: GlobBeanFactory
 * <br></br>
 * packageName: com.myhd.config
 *
 * @author jinhui-huang
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/10/25 08:47
 */
@Configuration
public class GlobBeanFactory {
    /**pageHelper插件*/
    @Bean
    public Interceptor[] plugins() {
        return new Interceptor[]{new PageInterceptor()};
    }

    /**配置pageHelper*/
    @Bean
    public PageHelper pageHelper(){
        return new PageHelper();
    }

}
