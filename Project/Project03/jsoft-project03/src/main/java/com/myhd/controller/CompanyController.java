package com.myhd.controller;


import com.myhd.service.impl.CompanyServiceImpl;
import com.myhd.service.impl.RecruitServiceImpl;
import com.myhd.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 企业表 前端控制器
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    private CompanyServiceImpl companyService;

    @Resource
    private RecruitServiceImpl recruitService;

    /**
     * @description: 获取企业的详细信息
     * @param companyId 企业编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/28 下午4:30
     */
    @GetMapping("getCompanyInfo/{companyId}")
    public Result getCompanyInfo(@PathVariable Integer companyId){
        return companyService.getCompanyInfo(companyId);
    }

    /**
     * @description: 获取该企业下的所有企业信息
     * @param companyId
     * @param userId
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/29 下午2:01
     */
    @GetMapping("getCompanyRecruit/{companyId}/{userId}")
    public Result getCompanyRecruit(@PathVariable Integer companyId, @PathVariable Integer userId){
        return recruitService.acquireRecruitByCompanyId(companyId,userId);
    }
}
