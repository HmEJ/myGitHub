package com.myhd.service;

import com.myhd.util.Result;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关注企业表 服务类
 * </p>
 *
 * @author Jinhui-Huang
 * @since 2023-10-23
 */

public interface IFollowService {

    /**
     * @description: 判断该企业是否被关注
     * @return: java.lang.Boolean true为已关注,false为未关注
     * @author CYQH
     * @date: 2023/10/25 下午5:59
     *
     */
    Result checkFollow(Integer userId, Integer companyId);

    /**
     * @description: 点击关注进行企业关注,根据是否是第一次关注选择插入还是更新,将关注状态置为1
     * @return: java.lang.Boolean true为关注成功,false为关注失败
     * @author CYQH
     * @date: 2023/10/25 下午5:53
     */
    Result follow(Integer userId,Integer companyId);


    /**
     * @description: 点击取消关注更新关注状态为0
     * @return: java.lang.Boolean true为取消关注成功,false为取消关注失败.
     * @author CYQH
     * @date: 2023/10/25 下午5:54
     */
    Result unfollow(Integer userId,Integer companyId);

}
