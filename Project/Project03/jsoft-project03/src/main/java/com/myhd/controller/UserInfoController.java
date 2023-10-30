package com.myhd.controller;

import com.myhd.entity.UserInfo;
import com.myhd.service.impl.UserInfoServiceImpl;
import com.myhd.util.Result;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import com.myhd.dto.FormDTO;
import com.myhd.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息表 存放用户详细信息 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/user-info")
@Slf4j
public class UserInfoController {
    @Resource
    private IUserService userService;

    @Resource
    private UserInfoServiceImpl userInfoService;
    /**
     * @description 获取用户信息
     * @author JoneElmo
     * @date 2023-10-26 19:18
     * @param
     * @return
     */
    @PostMapping("/getUserInfo")
    public Result getUserInfo(@RequestBody FormDTO formDTO){
        return userService.getUserInfo(formDTO);
    }

    /**
     * @description: 初次进入个人信息页面时获取个人信息页面的手机号或密码使用
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午7:56
     */
    @GetMapping("getPhoneOrEmail/{userId}")
    public Result getPhoneOrEmail(@PathVariable Integer userId){
        return userService.getUserById(userId);
    }

    /**
     * @description: 获取用户个人信息用于数据显示
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/28 下午4:30
     */
    @GetMapping("getUserInfo/{userId}")
    public Result getUserInfo(@PathVariable Integer userId){
        return userInfoService.getUserInfoById(userId);
    }

    /**
     * @description: 添加用户个人信息到个人用户信息表上
     * @param userInfo 用户个人信息实体类
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/28 下午4:31
     */
    @PostMapping
    public Result addUserInfo(@RequestBody UserInfo userInfo, HttpServletResponse response){
        Result result = userInfoService.addUserInfo(userInfo);
        /*手机号脱敏处理*/
        String phone = null;
        if (userInfo.getPhone()!=null){
            phone = userInfo.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        val realEmail = userInfo.getEmail();
        /*邮箱脱敏处理*/
        String maskedEmail = null;
        if (realEmail != null){
            val split = realEmail.split("@");
            maskedEmail = maskEmail(split[0]) + "@" + split[1];
        }
        val cookiePhone = new Cookie("cookiePhone",phone);
        val cookieUserEmail = new Cookie("cookieUserEmail", maskedEmail);
        int maxAge = 2 * 24 * 60 * 60; // 2天 = 2 * 24小时 * 60分钟 * 60秒
        setCookie(response, cookiePhone, maxAge);
        setCookie(response, cookieUserEmail, maxAge);
        return result;
    }

    private void setCookie(HttpServletResponse response, Cookie cookie1, int maxAge) {
        cookie1.setMaxAge(maxAge);
        cookie1.setPath("/");
        cookie1.setHttpOnly(false);
        response.addCookie(cookie1);
    }

    private String maskEmail(String emailPart) {
        int length = emailPart.length();
        if (length <= 2) {
            return emailPart.charAt(0) + "**";
        } else {
            return emailPart.charAt(0) + "*".repeat(length - 2) + emailPart.charAt(length - 1);
        }
    }

}
