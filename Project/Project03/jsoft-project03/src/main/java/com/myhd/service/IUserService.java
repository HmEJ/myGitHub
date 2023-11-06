package com.myhd.service;

import com.myhd.dto.FormDTO;
import com.myhd.entity.User;
import com.myhd.util.Result;

/**
 * <p>
 * 用户表 存放用户基本信息 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
public interface IUserService {
    /**
     * @description 注册功能
     * 获取表单数据并添加至数据库。添加至前需要查询内容判断是否存在该用户。
     * @author JoneElmo
     * @date 2023-10-26 08:49
     * @param
     * @return Boolean 注册是否成功
     */
    Result registerUser(User user);

    /**
     * @description 登陆功能。需要获用户的信息进行对比
     * @author JoneElmo
     * @date 2023-10-26 08:51
     * @param formDTO 表单信息
     * @return Boolean 登陆是否成功
     */
    Result loginUser(FormDTO formDTO);

    /**
     * @description 忘记密码功能
     * @author JoneElmo
     * @date 2023-10-26 08:53
     * @param
     * @return Boolean 修改密码是否成功
     */
    Result changePassword(User user);

    /**
     * @description: 根据用户编号获取user的信息
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午7:40
     */
    Result getUserById(Integer userId);

    /**
     * @description 根据传入参数获取用户信息
     * @author JoneElmo
     * @date 2023-10-26 19:30
     * @param
     * @return
     */
    Result getUserInfo(FormDTO formDTO);
}
