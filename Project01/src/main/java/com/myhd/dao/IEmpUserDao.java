package com.myhd.dao;

import com.myhd.pojo.EmpUser;

/**
 * className IEmpUserDao 用户实体Dao层接口
 * packageName com.myhd.dao
 * Description IEmpUserDao
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:14
 */
public interface IEmpUserDao {
    /**
     * Description: countUserById 根据id(唯一性)判断当前用户表里是否有这个用户,
     * 用于登录判断当前用户是否存在, 不存在登录失败提示用户不存在, 反之进入用户密码判断; 注册用于判断用户是否存在, 不存在即可注册, 反之不行,
     * 查询方法请调用BaseDao.countInfo();
     * @return java.lang.Integer 返回查询到的实体数量
     * @param empUserId 用于查询用户数量的用户id
     * @author huian
     * @Date 2023/8/18
     * */
    Integer countEmpUserById(Integer empUserId);

    /**
     * Description: selectByInfo 根据用户输入信息来查询用户表里的信息是否存在, 存在返回一个实体对象, 不存在返回一个空对象,
     * 查询请使用BaseDao.selectInfo()方法,
     * 用于登录判断用户的账号和密码是否正确
     * @return com.myhd.pojo.EmpUser 返回查询到的实体对象
     * @param empUser 用于查询的用户实体对象
     * @author huian
     * @Date 2023/8/18
     * */
    EmpUser selectEmpUSerByInfo(EmpUser empUser);

    /**
     * Description: insertEmpUser 必须先经过用户是不存在判断, 方可进行注册用户, 否则将抛出用户id主键重复异常,
     * 将传进来的用户实体进行注册, 一般不需要指定id, 在用户实体表中, 用户id自增属性从100001开始,
     * 更新请使用BaseDao.updateInfo()方法,
     * @return java.lang.Integer 返回注册时对用户表影响的行数, 一般都是为1
     * @param empUser 用于注册的用户实体对象
     * @author huian
     * @Date 2023/8/18
     * */
    Integer insertEmpUser(EmpUser empUser);


}
