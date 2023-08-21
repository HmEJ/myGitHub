package com.myhd.service;

import com.myhd.pojo.EmpUser;

/**
 * className IEmpUserService
 * packageName com.myhd.service
 * Description IEmpUserService
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 21:05
 */
public interface IEmpUserService {

    /**
     * Description: LoginService 登录功能建议先判断用户是否存在, 再获取用户全部信息
     * 进行比较, 用户不存在使用log4j输出用户不存在的信息, 同时在Main方法中登录的时候判断是普通员工还是
     * 总经理身份, 建议调用EmpUserDaoImpl.countEmpUserById() 和 EmpUserDaoImpl.selectEmpUser()方法
     * @return java.lang.Boolean 返回登录成功与否
     * @param empUser 用户实体对象
     * @author huian
     * @Date 2023/8/18
     * */
    Boolean LoginService(EmpUser empUser);

    /**
     * Description: RegisterService 注册功能建议先判断用户是否存在, 一般来讲如果不特定指定用户id没有判断的必要,
     * 因为注册时的id是自增的, 未特定指定注册id是不会发生用户重复的情况, 而且是允许同名用户的存在,
     * 建议调用EmpUserDaoImpl.selectEmpUser(), 如果指定id进行用户id是否重复判断调用EmpUserDaoImpl.countEmpUserById()方法
     * @return java.lang.Boolean 返回注册成功与否
     * @param empUser 用户实体对象
     * @author huian
     * @Date 2023/8/18
     * */
    Boolean RegisterService(EmpUser empUser);
}
