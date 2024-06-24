package com.myhd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.myhd")
public class AppApplication {
    /*测试*/
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
