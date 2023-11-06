package com.myhd.service;

import com.myhd.entity.UserInfo;
import com.myhd.util.Result;

/**
 * <p>
 * 用户信息表 存放用户详细信息 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
public interface IUserInfoService {
    /**
     * @description 插入个人信息
     * @author JoneElmo && CYQH
     * @date 2023-10-24 09:36
     * @param userInfo 个人信息
     * @return int 1成功 0失败
     */
    Result addUserInfo(UserInfo userInfo);

    /**
     * @description 根据用户编号查询信息
     * 用于数据回显
     * @author JoneElmo && CYQH
     * @date 2023-10-24 09:59
     * @param userId 用户编号
     * @return UserInfo 用户详细信息
     */
    Result getUserInfoById(Integer userId);

}
