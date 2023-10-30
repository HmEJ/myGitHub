package com.myhd.mapper;

import com.myhd.dto.FormDTO;
import com.myhd.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 存放用户基本信息 Mapper 接口
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Mapper
public interface UserMapper {
    /**
     * @description 用户注册接口
     * 用于插入用户信息
     * @author JoneElmo && CYQH
     * @date 2023-10-24 08:58
     * @param user 注册时填写的信息
     * @return int 1 注册成功 0 注册失败
     */
    Integer saveUser(User user);

    /**
     * @description 判断用户是否存在
     * 动态sql通过手机号或者邮箱来查询
     * @author JoneElmo && CYQH
     * @date 2023-10-24 08:56
     * @param
     * @return int 1 用户存在 0 用户不存在
     */
    Integer isUserExist(FormDTO formDTO);

    /**
     * @description 根据输入的参数查找用户信息
     * 该方法用于验证密码是否正确
     * @author JoneElmo && CYQH
     * @date 2023-10-24 09:07
     * @param
     * @return User 结果用于对比
     */
    User findByArgs(FormDTO formDTO);

    /**
     * @description 用于实现找回密码功能
     * @author JoneElmo && CYQH
     * @date 2023-10-24 09:10
     * @param user
     * @return int 1表示成功  0表示失败
     */
    Integer updateUserPassword(User user);

    /**
     * @description: 在用户完善个人信息前,将新增的邮箱或电话更新到用户表中
     * @param user
     * @return: java.lang.Integer
     * @author CYQH
     * @date: 2023/10/26 下午4:15
     */
    Integer updateUser(User user);

    /**
     * @description: 用于进入个人信息页面时获取用户注册时的手机号或邮箱
     * @param userId 用户编号
     * @return: com.myhd.entity.User
     * @author CYQH
     * @date: 2023/10/26 下午7:36
     */
    User getUserById(Integer userId);

}
