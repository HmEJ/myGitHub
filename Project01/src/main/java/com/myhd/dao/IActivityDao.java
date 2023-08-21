package com.myhd.dao;

import com.myhd.pojo.Activity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * className IActivityDao 活动Dao层接口
 * packageName com.myhd.dao
 * Description IActivityDao
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:14
 */
public interface IActivityDao {

    /**
     * @description 根据活动id查找活动信息
     * @author JoneElmo
     * @date 2023-08-20 20:20
     * @param id 活动id
     * @return
     */
    Activity selectActivityById(Integer id);
    /**
     * Description: insertActivity 由总经理添加活动, 活动id一般不需要特殊指定, 活动id从500001开始自增,
     * 插入请使用BaseDao.updateInfo()方法
     * @return java.lang.Integer 返回插入活动时对活动表的影响行数, 一般都为1
     * @param activity 传入要插入的具体活动对象
     * @author huian
     * @Date 2023/8/18
     * */
    Integer insertActivity(Activity activity);

    /**
     * Description: selectActivity 分页查询展示活动信息,
     * 查询请使用BaseDao.selectInfo()方法
     * @return java.util.List<com.myhd.pojo.Activity> 返回查询到的活动集合
     * @param start 开始查询的页数, 一般要小于总页数
     * @param rows 每页要展示的行数
     * @author huian
     * @Date 2023/8/18
     * */
    List<Activity> selectActivity(Integer start, Integer rows);

    /**
     * Description: countAllPages 统计全部的活动数量
     * @return java.lang.Integer 返回总数据量
     * @author huian
     * @Date 2023/8/20
     * */
    Integer countAllPages();

    /**
     * Description: deleteActivity 根据传进去的活动对象进行活动的更新,
     * 更新请使用BaseDao.updateInfo()方法,
     * @return java.lang.Integer 返回更新活动时对活动表的影响行数, 一般都为1
     * @param activity 活动对象
     * @author huian
     * @Date 2023/8/18
     * */
    Integer updateActivity(Activity activity);

    /**
     * Description: deleteActivity 根据活动id删除活动,
     * 删除请使用BaseDao.updateInfo()方法
     * @return java.lang.Integer 返回插入活动时对活动表的影响行数, 一般都为1
     * @param activityId 活动id
     * @author huian
     * @Date 2023/8/18
     * */
    Integer deleteActivity(Integer activityId);

    /**
     * Description: selectDiscount 根据当前日期和消费金额进行折扣的选择并返回,
     * 查询请使用BaseDao.countInfo()方法,
     * @return java.lang.Double 返回选择好的折扣, 如果没有符合的折扣必须返回1
     * @param nowDate 当前日期
     * @param money 当前消费金额
     * @author huian
     * @Date 2023/8/18
     * */
    Double selectDiscount(LocalDateTime nowDate, Double money);
}
