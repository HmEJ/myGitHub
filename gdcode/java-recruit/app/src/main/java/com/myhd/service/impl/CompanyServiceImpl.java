package com.myhd.service.impl;

import cn.hutool.json.JSONUtil;
import com.myhd.entity.Company;
import com.myhd.mapper.CompanyMapper;
import com.myhd.service.ICompanyService;
import com.myhd.util.Code;
import com.myhd.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * <p>
 * 企业表 服务实现类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Service
@Primary
@Slf4j
public class CompanyServiceImpl implements ICompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @description: 进入企业详情页面获取企业的信息
     * @param companyId 企业编号
     * @return: com.myhd.util.Result 返回的结果集
     * @author CYQH
     * @date: 2023/10/26 下午3:01
     */
    @Override
    public Result getCompanyInfo(Integer companyId) {
        String key = "company:" + companyId;
        Company companyInfo;
        String s = stringRedisTemplate.opsForValue().get(key);
        if (s != null){
            companyInfo = JSONUtil.toBean(s, Company.class);
            log.info("CompanyServiceImpl.getCompanyInfo:{从redis中获取}");
        }else {
            companyInfo = companyMapper.findById(companyId);
            log.info("CompanyServiceImpl.getCompanyInfo:{从数据库获取}");
            if (companyInfo != null){
                String jsonStr = JSONUtil.toJsonStr(companyInfo);
                stringRedisTemplate.opsForValue().set(key,jsonStr,Duration.ofMinutes(5L));
            }
        }
        if (companyInfo != null){
            return Result.ok(Code.GET_OK,companyInfo,"企业信息获取成功");
        }else {
            return Result.fail(Code.GET_FAIL, null,"企业信息获取失败");
        }
    }
}
