package com.myhd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.myhd.mapper")
public class JsoftProject03Application {
    /*测试*/
    public static void main(String[] args) {
        SpringApplication.run(JsoftProject03Application.class, args);
    }

}
