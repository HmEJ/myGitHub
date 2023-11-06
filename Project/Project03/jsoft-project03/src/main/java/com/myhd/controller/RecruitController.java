package com.myhd.controller;


import cn.hutool.json.JSONUtil;
import com.myhd.service.IRecruitService;
import com.myhd.util.Code;
import com.myhd.util.Result;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

/**
 * <p>
 * 招聘职位表 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/recruit")
@SuppressWarnings("all")
@Slf4j
public class RecruitController {

    @Resource
    private IRecruitService recruitService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * Description: getFields 获取Redis里面存储的相关领域
     * @return com.myhd.util.Result
     * @author jinhui-huang
     * @Date 2023/10/26
     * */
    @GetMapping("/get_fields")
    public Result getFields(){
        return recruitService.getFields();
    }

    /**
     * @description: 获取招聘职位信息页面
     * @param companyId 企业编号
     * @param recruitId 招聘编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/28 下午4:35
     */

    @GetMapping("getRecruitInfo/{companyId}/{recruitId}/{userId}")
    public Result getRecruitInfo(@PathVariable Integer companyId, @PathVariable Integer recruitId, @PathVariable Integer userId){
        return recruitService.acquireRecruitInfo(companyId,recruitId,userId);
    }

    /**
     * @description: 获取高薪职位列表
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午2:05
     */
    @GetMapping("getHighSalaryRecruit")
    public Result getHighSalaryRecruit(){
        return recruitService.acquireHighSalaryList();
    }


    /**
     * @description 首页模糊查询职位列表   非快捷查询
     * @author JoneElmo
     * @date 2023-10-29 09:58
     * @param word
     * @return Result
     */
    @GetMapping("/queryRecruitList")
    public Result QueryRecruitList(@PathParam("word") String word, @PathParam("pageNum") Integer pageNum){
        val s = stringRedisTemplate.opsForValue().get("index:" + word + ":"+pageNum);
        if (s==null){
            log.info("redis中不存在数据，正在从数据库查询...");
            val result = recruitService.searchRecruit(false, word, pageNum);
            return result;
        }else {
            log.info("redis中存在数据，正在返回结果...");
            log.info("从redis查到的职位列表信息："+s);
            val jsonObject = JSONUtil.parseObj(s);
            log.info("转换为json对象的数据"+jsonObject);

            return new Result(Code.GET_OK,jsonObject,"获取职位列表成功");
        }
    }

    @GetMapping("/quickQueryRecruitList")
    public Result quickQueryRecruitList(@PathParam("jobFiled") Integer jobFiled,@PathParam("pageNum") Integer pageNum){
        log.info("获取到的pageNum是"+pageNum);
        val result = recruitService.searchRecruit(true, jobFiled,pageNum);
        log.info("controller获取到的快捷查询数据:"+result);
        return result;
    }

}
