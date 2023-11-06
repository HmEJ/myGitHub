package com.myhd.service;

import com.myhd.entity.Company;
import com.myhd.util.Result;

/**
 * <p>
 * 企业表 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
public interface ICompanyService {

    /**
     * @description 根据企业id查询企业信息
     * 用于首页点击企业时，获取企业信息功能
     * @author JoneElmo && CYQH
     * @date 2023-10-24 09:23
     * @param companyId 企业id 唯一标识符
     * @return Company 企业信息
     */
    Result getCompanyInfo(Integer companyId);

}
