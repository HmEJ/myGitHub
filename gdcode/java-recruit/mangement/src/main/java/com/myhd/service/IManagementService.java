package com.myhd.service;

import com.myhd.entity.Company;
import com.myhd.entity.ManagementUserInfoReturn;
import com.myhd.util.Result;

/**
 * @Classname IManagementService
 * @Description
 * @Date 2024/3/30 下午7:25
 * @Created by HmEJ
 */
public interface IManagementService {
    Result getAllUserInfo();

    Result getCompanyInfo();

    Result updateUserInfo(ManagementUserInfoReturn dto);

    Result updateCompanyInfo(Company company);

    Result deleteUserInfo(Integer userId);

    Result deleteCompanyInfo(Integer companyId);

    Result addUser(ManagementUserInfoReturn dto);

    Result addCom(Company company);
}
