package com.myhd.service.impl;

import cn.hutool.json.JSONUtil;
import com.myhd.dto.FormDTO;
import com.myhd.entity.User;
import com.myhd.exception.BusinessException;
import com.myhd.mapper.UserMapper;
import com.myhd.service.IUserService;
import com.myhd.util.Code;
import com.myhd.util.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 存放用户基本信息 服务实现类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Service
@Primary
public class UserServiceImpl implements IUserService {
    @Resource
    UserMapper userMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * @param user
     * @return Boolean
     * @description 用户注册
     * @author JoneElmo
     * @date 2023-10-26 09:49
     */
    @Override
    public Result registerUser(User user) {
        Integer i = 0;
        String key = "user:cache:id";
        String userNamePrefix = "user_";
        try {
            Long incrementId = stringRedisTemplate.opsForValue().increment(key, 1);
            user.setUserId(incrementId.intValue());
            user.setUserName(userNamePrefix + incrementId);
            i = userMapper.saveUser(user);
        } catch (Exception e) {
            stringRedisTemplate.opsForValue().increment(key, -1);
            throw new BusinessException(Code.FAIL, "注册失败，用户已存在");
        }
        return i == 1 ? new Result(Code.OK, user, "注册成功") : new Result(Code.FAIL, false, "注册失败");
    }

    /**
     * @param formDTO
     * @return Boolean
     * @description 用户登录功能。首先判断用户是否存在。如果用户不存在，不允许登陆
     * 否则，判断密码是否正确。
     * @author JoneElmo
     * @date 2023-10-26 09:49
     */
    @Override
    public Result loginUser(FormDTO formDTO) {
        Integer userExist = userMapper.isUserExist(formDTO);
        if (userExist == 0) {  /*用户不存在无法登陆*/
            return new Result(Code.FAIL, false, "用户不存在");
        } else {
            User args = userMapper.findByArgs(formDTO);
            if (args.getPassword().equals(formDTO.getPassword())) {
                /* 密码正确就将信息缓存到redis中 */
                String key = "login:token:" + args.getUserId();
                String jsonStr = JSONUtil.toJsonStr(args);
                stringRedisTemplate.opsForValue().set(key, jsonStr, 2L, TimeUnit.DAYS);
                return new Result(Code.OK, true, "登陆成功");
            }
        }
        return new Result(Code.FAIL, false, "密码错误");
    }

    /**
     * @param user
     * @return Boolean
     * @description 修改密码
     * @author JoneElmo
     * @date 2023-10-26 09:49
     */
    @Override
    public Result changePassword(User user) {
        Integer i = userMapper.updateUserPassword(user);
        return i == 1 ? new Result(Code.OK, true, "修改成功") : new Result(Code.FAIL, false, "修改失败");
    }

    /**
     * @param formDTO
     * @return Result
     * @description 根据参数获取用户详细信息
     * @author JoneElmo
     * @date 2023-10-26 19:32
     */
    @Override
    public Result getUserInfo(FormDTO formDTO) {
        User user = userMapper.findByArgs(formDTO);
        if (user != null) {
            return new Result(Code.POST_OK, user, "用户信息获取成功");
        }
        return new Result(Code.POST_FAIL, null, "用户信息获取失败");
    }

    /**
     * @param userId 用户编号
     * @description: 用于进入个人信息页面时获取用户注册时的手机号或邮箱
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/28 下午9:44
     */
    @Override
    public Result getUserById(Integer userId) {
        User user = userMapper.getUserById(userId);
        if (user != null) {
            String pass = maskPassword(user.getPassword());
            user.setPassword(pass);
            return Result.ok(Code.GET_OK, user, "信息获取成功");
        }
        return Result.ok(Code.GET_FAIL, null, "信息获取失败");
    }

    private String maskPassword(String password) {
        int length = password.length();
        if (length <= 2) {
            return password.charAt(0) + "**";
        } else {
            return password.charAt(0) + "*".repeat(length - 2) + password.charAt(length - 1);
        }
    }
}
