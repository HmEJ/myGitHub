package com.myhd.service.impl;

import com.myhd.entity.Follow;
import com.myhd.mapper.FollowMapper;
import com.myhd.service.IFollowService;
import com.myhd.util.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 关注企业表 服务实现类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */
@Service
@Primary
public class FollowServiceImpl implements IFollowService {

    @Resource
    private FollowMapper followMapper;

    /**
     * @description: 根据用户编号和企业编号判断该企业有没有被该用户关注
     * @param userId 用户编号
     * @param companyId 企业编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午3:08
     */
    @Override
    public Result checkFollow(Integer userId, Integer companyId) {
        Follow follow = followMapper.findFollowStatus(userId, companyId);
        if (follow != null && follow.getFollowStatus() == 1){
            return Result.ok("该企业已关注");
        }else {
            return Result.fail("未关注该企业");
        }
    }

    /**
     * @description: 点击关注进行企业关注,第一次关注时进行关注表插入,后续执行关注时进行状态修改
     * @param userId 用户编号
     * @param companyId 企业编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午3:23
     */
    @Override
    public Result follow(Integer userId,Integer companyId) {
        Follow followStatus = followMapper.findFollowStatus(userId, companyId);
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setCompanyId(companyId);
        follow.setFollowStatus(1);
        boolean flag = false;
        if (followStatus == null){
            if (followMapper.saveFollowInfo(follow) == 1){
                flag = true;
            }
        }else {
            if (followMapper.setFollowStatus(follow) == 1){
                flag = true;
            }
        }
        if (flag){
            return Result.ok("关注成功");
        }
        return Result.fail("关注失败");
    }

    /**
     * @description: 点击取消关注进行取消关注
     * @param userId 用户编号
     * @param companyId 企业编号
     * @return: com.myhd.util.Result
     * @author CYQH
     * @date: 2023/10/26 下午3:25
     */
    @Override
    public Result unfollow(Integer userId,Integer companyId) {

        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setCompanyId(companyId);
        follow.setFollowStatus(0);
        if (followMapper.setFollowStatus(follow) == 1){
            return Result.ok("取消关注成功");
        }
        return Result.fail("取消关注失败");
    }
}
