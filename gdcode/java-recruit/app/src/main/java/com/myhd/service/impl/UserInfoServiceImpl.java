package com.myhd.service.impl;

import com.myhd.entity.User;
import com.myhd.entity.UserInfo;
import com.myhd.mapper.UserInfoMapper;
import com.myhd.mapper.UserMapper;
import com.myhd.service.IUserInfoService;
import com.myhd.util.Code;
import com.myhd.util.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 存放用户详细信息 服务实现类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Service
@Primary
public class UserInfoServiceImpl implements IUserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * @description: 进行个人信息的完善插入,先进行邮箱或电话的用户表完善,再进行用户信息插入
     * @param userInfo 用户个人信息
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午3:28
     */
    @Override
    @Transactional
    public Result addUserInfo(UserInfo userInfo) {
        Integer integer;
        User user = new User();
        user.setUserId(userInfo.getUserId());
        user.setPhone(userInfo.getPhone());
        user.setEmail(userInfo.getEmail());
        try {
            userMapper.updateUser(user);
            integer = userInfoMapper.saveUserInfo(userInfo);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            return Result.fail(Code.POST_FAIL,false,"完善个人信息失败");
        }
        if(integer == 1){
            return Result.ok(Code.POST_OK,true,"完善个人信息成功");
        }
        return Result.fail(Code.POST_FAIL,false,"完善个人信息失败");
    }

    /**
     * @description: 根据用户编号获取用户个人信息进行数据展示
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午3:33
     */
    @Override
    public Result getUserInfoById(Integer userId) {

        UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
        if (userInfo != null){
            return Result.ok(Code.GET_OK,userInfo,"获取个人信息成功");
        }
        return Result.fail(Code.GET_FAIL, null,"获取个人信息失败");
    }
}
