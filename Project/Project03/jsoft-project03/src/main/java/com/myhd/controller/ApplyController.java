package com.myhd.controller;


import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import com.myhd.entity.Apply;
import com.myhd.entity.Recruit;
import com.myhd.service.impl.ApplyServiceImpl;
import com.myhd.util.Code;
import com.myhd.util.Result;
import lombok.val;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.time.Duration;
import java.util.List;

/**
 * <p>
 * 职位申请表 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/apply")
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
    @GetMapping("getAllUserApply/{pageNum}/{userId}")
    public Result getCompanyInfo(@PathVariable Integer userId, @PathParam("like") String like, @PathVariable Integer pageNum){
        System.out.println(like);
        String key = userId+":"+like+":"+pageNum;
        List<Recruit> allUserApply;
        PageInfo<Recruit> pageInfo;
        String s = stringRedisTemplate.opsForValue().get(key);
        if (s == null){
            return applyService.getAllUserApply(userId, like, pageNum);
        }else {
            System.out.println("从redis获取");
            val jsonObject = JSONUtil.parseObj(s);
            return Result.ok(Code.GET_OK,jsonObject,"查询成功");
        }
    }
}
