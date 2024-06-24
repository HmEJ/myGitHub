package com.myhd.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myhd.entity.Company;

import com.myhd.entity.ManagementUserInfoReturn;
import com.myhd.mapper.ManagementMapper;
import com.myhd.service.IManagementService;
import com.myhd.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;

/**
 * @Classname ManagementServiceImpl
 * @Description
 * @Date 2024/3/30 下午7:26
 * @Created by HmEJ
 */
@Service
@Primary
@Slf4j
public class ManagementServiceImpl implements IManagementService {

    @Resource
    private ManagementMapper managementMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getAllUserInfo() {
        List<ManagementUserInfoReturn> list = managementMapper.getAllUserInfo();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return Result.ok(100, json, "success!");
    }

    @Override
    public Result getCompanyInfo() {
        String key = "manage:cache:company";
        String companyStr = stringRedisTemplate.opsForValue().get(key);
        if (companyStr != null){
            log.info("ManagementServiceImpl.getCompanyInfo:{redis检索到数据,正在返回}");
            return Result.ok(100,companyStr,"success!");
        }
        log.info("ManagementServiceImpl.getCompanyInfo:{redis未检索到数据,正在查询数据库}");
        List<Company> list = managementMapper.getCompanyInfo();
        ObjectMapper mapper = new ObjectMapper();
        String json = null;

        try {
            json = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        /* 数据写入redis 有效期5分钟 */
        stringRedisTemplate.opsForValue().set(key,json, Duration.ofMinutes(5L));
        log.info("ManagementServiceImpl.getCompanyInfo:{正在返回数据库数据}");
        return Result.ok(100, json, "success!");
    }

    @Override
    public Result updateUserInfo(ManagementUserInfoReturn dto) {
        int flag = managementMapper.updateUserInfo(dto);
        return flag == 1 ? Result.ok("更新成功") : Result.fail("更新失败");
    }

    @Override
    public Result updateCompanyInfo(Company company) {
        int flag = managementMapper.updateCompanyInfo(company);
        return flag == 1 ? Result.ok("更新成功") : Result.fail("更新失败");
    }

    @Override
    public Result deleteUserInfo(Integer userId) {
        int flag = managementMapper.deleteUserInfo(userId);
        return flag == 1 ? Result.ok("删除成功") : Result.fail("删除失败");
    }

    @Override
    public Result deleteCompanyInfo(Integer companyId) {
        int flag = managementMapper.deleteCompanyInfo(companyId);
        return flag == 1 ? Result.ok("删除成功") : Result.fail("删除失败");
    }

    @Override
    public Result addUser(ManagementUserInfoReturn dto) {
        int flag = managementMapper.addUser(dto);

        return flag == 1 ? Result.ok("删除成功") : Result.fail("删除失败");
    }

    @Override
    public Result addCom(Company company) {
        int flag = managementMapper.addCom(company);
        return flag == 1 ? Result.ok("删除成功") : Result.fail("删除失败");
    }
}
