package com.myhd.mapper;

import com.myhd.entity.Company;

import com.myhd.entity.ManagementUserInfoReturn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname ManagementMapper
 * @Description
 * @Date 2024/3/30 下午7:27
 * @Created by HmEJ
 */
@Mapper
public interface ManagementMapper {
    List<ManagementUserInfoReturn> getAllUserInfo();

    List<Company> getCompanyInfo();

    int updateUserInfo(ManagementUserInfoReturn dto);

    int updateCompanyInfo(Company company);

    int deleteUserInfo(Integer userId);

    int deleteCompanyInfo(Integer companyId);

    int addUser(ManagementUserInfoReturn dto);

    int addCom(Company company);
}
