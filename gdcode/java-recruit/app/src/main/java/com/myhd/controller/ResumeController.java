package com.myhd.controller;


import com.myhd.entity.Resume;
import com.myhd.service.IResumeService;
import com.myhd.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * <p>
 * 简历表 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/resume")
@Slf4j
@Api(tags = "简历相关")
public class ResumeController {
    @Resource
    private IResumeService resumeService;
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/generateResume")
    @ApiOperation("生成简历")
    public Result generateResume(@RequestBody Resume resume){
        val result = resumeService.saveResumeInfo(resume);
        return result;
    }

    @GetMapping("/acquireResume")
    @ApiOperation("获取简历信息")
    public Result acquireResume(@PathParam("userId") Integer userId){
        log.info("获取到的用户id是"+userId);
        val result = resumeService.acquireResumeInfo(userId);
        return result;
    }
}
