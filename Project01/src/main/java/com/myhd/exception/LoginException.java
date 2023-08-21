package com.myhd.exception;


/**
 * className LoginException
 * packageName com.myhd.exception
 * Description LoginException
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 10:45
 */
public class LoginException extends RuntimeException{

    /**
     * Description: LoginException 登录异常无参构造
     * @return null
     * @author huian
     * @Date 2023/8/18
     * */
    public LoginException(){}

    /**
     * Description: LoginException 登录异常单参构造, 传入具体异常消息
     * @return null
     * @author huian
     * @Date 2023/8/18
     * */
    public LoginException(String msg) {
        super(msg);
    }
}
