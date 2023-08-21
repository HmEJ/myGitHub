package com.myhd.exception;

/**
 * className RegisterException
 * packageName com.myhd.exception
 * Description RegisterException
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 10:50
 */
public class RegisterException extends RuntimeException{
    /**
     * Description: RegisterException 注册异常无参构造
     * @return null
     * @author huian
     * @Date 2023/8/18
     * */
    public RegisterException(){
    }

    /**
     * Description: RegisterException 注册异常单参构造, 传入具体异常消息
     * @return null
     * @author huian
     * @Date 2023/8/18
     * */
    public RegisterException(String msg){
        super(msg);
    }
}
