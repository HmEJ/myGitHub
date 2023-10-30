package com.myhd.mapper;

import com.myhd.entity.Resume;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 简历表 Mapper 接口
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Mapper
public interface ResumeMapper {

    /**
     * @description 插入简历
     * @author JoneElmo && CYQH
     * @date 2023-10-24 10:03
     * @param resume 简历
     * @return int 1 成功 0 失败
     */
    Integer insertResume(Resume resume);

    /**
     * @description 根据用户编号查询对应的简历
     * 用于数据回显
     * @author JoneElmo && CYQH
     * @date 2023-10-24 10:05
     * @param userId
     * @return Resume简历信息
     */
    Resume getResumeByUserId(Integer userId);

}
