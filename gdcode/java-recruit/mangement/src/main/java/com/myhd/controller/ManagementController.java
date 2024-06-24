package com.myhd.controller;

import com.myhd.entity.Company;
import com.myhd.entity.ManagementUserInfoReturn;
import com.myhd.service.IManagementService;
import com.myhd.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Classname ManagementController
 * @Description
 * @Date 2024/3/30 下午7:21
 * @Created by HmEJ
 */
@RequestMapping("/manage")
@RestController
public class ManagementController {

    @Resource
    private IManagementService managementService;

    /**
     * 获取所有的用户信息
     *
     * @return
     */
    @GetMapping("/getAllUserInfo")
    public Result getAllUserInfo() {
        return managementService.getAllUserInfo();
    }

    @GetMapping("/getCompanyInfo")
    public Result getCompanyInfo() {
        return managementService.getCompanyInfo();
    }


    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody ManagementUserInfoReturn dto) {
        return managementService.updateUserInfo(dto);
    }

    @PostMapping("/updateCompanyInfo")
    public Result updateCompanyInfo(@RequestBody Company company) {
        return managementService.updateCompanyInfo(company);
    }

    @PostMapping("/deleteUserInfo/{userId}")
    public Result deleteUserInfo(@PathVariable Integer userId) {
        return managementService.deleteUserInfo(userId);
    }

    @PostMapping("/deleteCompanyInfo/{companyId}")
    public Result deleteCompanyInfo(@PathVariable Integer companyId) {
        return managementService.deleteCompanyInfo(companyId);
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody ManagementUserInfoReturn dto){
        return managementService.addUser(dto);
    }

    @PostMapping("/addCom")
    public Result addCom(@RequestBody Company company){
        return managementService.addCom(company);
    }
}
