package com.myhd.controller;


import com.myhd.entity.Apply;
import com.myhd.service.impl.ApplyServiceImpl;
import com.myhd.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * <p>
 * 职位申请表 前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/apply")
@Api(tags = "职位申请接口")
public class ApplyController {
    @Resource
    private ApplyServiceImpl applyService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * @description: 进行职位申请
     * @param apply 申请实体类
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午2:55
     */
    @ApiOperation("申请职位")
    @PostMapping
    public Result applyRecruit(@RequestBody Apply apply){
        return applyService.insertApplyInfo(apply);
    }

    /**
     * @description: 个人申请职位的查询以及模糊查询和分页
     * @param userId 用户编号
     * @param like 模糊查询信息
     * @param pageNum   页码
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午3:30
     */
   @ApiOperation("根据用户id查询用户申请的职位信息")
    @GetMapping("getAllUserApply/{pageNum}/{userId}")
    public Result getCompanyInfo(@PathVariable Integer userId, @PathParam("like") String like, @PathVariable Integer pageNum){
            return applyService.getAllUserApply(userId, like, pageNum);
    }
}
