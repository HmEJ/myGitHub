package com.myhd.controller;


import com.myhd.service.impl.FollowServiceImpl;
import com.myhd.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 关注企业表 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/follow")
@Api(tags = "关注相关")
public class FollowController {
    @Resource
    private FollowServiceImpl followService;
    /**
     * @description: 进入企业详情页面判断该企业是否关注
     * @param companyId 企业编号
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午2:06
     */
    @ApiOperation("根据用户id和企业id检查关注状态")
    @GetMapping("checkFollowStatus/{companyId}/{userId}")
    public Result getCompanyInfo(@PathVariable Integer companyId, @PathVariable Integer userId){
        return followService.checkFollow(userId,companyId);
    }
    /**
     * @description: 关注企业
     * @param companyId 企业编号
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午2:45
     */
    @ApiOperation("关注企业")
    @GetMapping("followCompany/{companyId}/{userId}")
    public Result followCompany(@PathVariable Integer companyId, @PathVariable Integer userId){
        return followService.follow(userId,companyId);
    }
    /**
     * @description: 取消关注企业
     * @param companyId 企业编号
     * @param userId 用户编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午2:46
     */
    @ApiOperation("取消关注")
    @GetMapping("unfollowCompany/{companyId}/{userId}")
    public Result unfollowCompany(@PathVariable Integer companyId, @PathVariable Integer userId){
        return followService.unfollow(userId,companyId);
    }
}
