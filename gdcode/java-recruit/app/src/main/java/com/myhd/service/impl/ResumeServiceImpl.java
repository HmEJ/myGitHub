package com.myhd.service.impl;

import com.myhd.entity.Resume;
import com.myhd.exception.BusinessException;
import com.myhd.mapper.ResumeMapper;
import com.myhd.service.IResumeService;
import com.myhd.util.Code;
import com.myhd.util.Result;
import lombok.val;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 简历表 服务实现类
 * </p>
 *
 * @author JoneElmo
 * @since 2023-10-23
 */
@Service
@Primary
public class ResumeServiceImpl implements IResumeService {

    @Resource
    private ResumeMapper resumeMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * @description
     * @author JoneElmo
     * @date 2023-10-26 09:52
     * @param userId
     * @return Resume
     */
    @Override
    public Result acquireResumeInfo(Integer userId) {
        Resume resume = resumeMapper.getResumeByUserId(userId);
        return resume == null ? new Result(Code.FAIL, null,"简历不存在"):new Result(Code.OK, resume,"获取成功");
    }

    /**
     * @description
     * @author JoneElmo
     * @date 2023-10-26 09:53
     * @param resume
     * @return Boolean
     */
    @Override
    public Result saveResumeInfo(Resume resume) {
        Integer i = null;
        String key = "resume:cache:id";
        try {
            val incrementId = stringRedisTemplate.opsForValue().increment(key, 1);
            resume.setResumeId(incrementId.intValue());
            i = resumeMapper.insertResume(resume);
        } catch (Exception e) {
            stringRedisTemplate.opsForValue().increment(key, -1);
            throw new BusinessException(Code.FAIL,"保存失败,简历已存在");
        }
        return i == 1 ? new Result(Code.OK, true,"添加成功"):new Result(Code.FAIL, false,"添加失败");
    }
}
