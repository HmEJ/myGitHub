package com.myhd.service;

import com.myhd.entity.Resume;
import com.myhd.util.Result;

/**
 * <p>
 * 简历表 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
public interface IResumeService {
    /* 一分钟填写简历 */

    /**
     * @description 获取简历信息，用于回显数据
     * @author JoneElmo
     * @date 2023-10-26 09:14
     * @param
     * @return Resume
     */
    Result acquireResumeInfo(Integer userId);

    /**
     * @description 插入简历信息
     * @author JoneElmo && CYQH
     * @date 2023-10-26 09:17
     * @param
     * @return
     */
    Result saveResumeInfo(Resume resume);

}
