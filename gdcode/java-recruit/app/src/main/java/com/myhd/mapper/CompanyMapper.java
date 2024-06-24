package com.myhd.mapper;

import com.myhd.entity.Company;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 企业表 Mapper 接口
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Mapper
public interface CompanyMapper {

    /**
     * @description 根据企业id查询企业信息
     * 用于首页点击企业时，获取企业信息功能
     * @author JoneElmo && CYQH
     * @date 2023-10-24 09:23
     * @param id 企业id 唯一标识符
     * @return Company 企业信息
     */
    Company findById(Integer id);
}
