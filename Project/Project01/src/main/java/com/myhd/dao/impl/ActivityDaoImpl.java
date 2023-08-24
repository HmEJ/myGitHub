package com.myhd.dao.impl;

import com.myhd.dao.IActivityDao;
import com.myhd.pojo.Activity;
import com.myhd.util.BaseDao;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/**
 * @description
 * @author JoneElmo
 * @date 2023-08-18 19:17
 * @version 1.0
 * @package com.myhd.dao.impl
 * @class ActivityDaoImpl
 */
public class ActivityDaoImpl implements IActivityDao {
    @Override
    public Activity selectActivityById(Integer id) {
        /**
         * @description 根据id查找活动信息
         * @author JoneElmo
         * @date 2023-08-20 20:21
         * @param id
         * @return com.myhd.pojo.Activity
         */
        String sql = "select * from tb_activity where activityId=?;";
        List<?> list = BaseDao.selectInfo(sql, Activity.class, id);
        return (Activity) list.get(0);
    }

    @Override
    public Integer insertActivity(Activity activity) {
        /**
         * @description  插入活动数据
         * @author JoneElmo
         * @date 2023-08-18 19:17
         * @param activity 对象
         * @return java.lang.Integer
         */
        String sql = "insert into tb_activity(activityName, criticalTotal, startDate, endDate, discount) VALUES (?,?,?,?,?);";
        int i = BaseDao.updateInfo(sql, activity.getActivityName(), activity.getCriticalTotal(), activity.getStartDate(), activity.getEndDate(), activity.getDiscount());
        return i;
    }

    @Override
    public List<Activity> selectActivity(Integer start, Integer rows) {
        /**
         * @description 分页查询
         * @author JoneElmo
         * @date 2023-08-18 19:17
         * @param start 开始的下标索引 从0开始
         * @param rows 显示的行数
         * @return java.util.List<com.myhd.pojo.Activity>
         */
        String sql = "select activityId, activityName, criticalTotal, startDate, endDate, discount from tb_activity order by activityId limit ?,?;";
        List<Activity> list = (List<Activity>) BaseDao.selectInfo(sql, Activity.class, (start-1)*rows, rows);
        return list;
    }

    @Override
    public Integer countAllPages() {
        /**
         * @description
         * @author JoneElmo
         * @date 2023-08-20 13:59
         * @return java.lang.Integer
         */
        String sql = "select count(activityId) as count from tb_activity;";
        Integer i = ((Long) BaseDao.countInfo(sql)).intValue();
        return i;
    }

    @Override
    public Integer updateActivity(Activity activity) {
        /**
         * @description 更新活动信息
         * @author JoneElmo
         * @date 2023-08-18 19:17
         * @param activity 活动对象
         * @return java.lang.Integer
         */
        String sql = "update tb_activity set activityName=?, criticalTotal=?,startDate=?,endDate=?,discount=? where activityId = ?;";
        int i = BaseDao.updateInfo(sql, activity.getActivityName(), activity.getCriticalTotal(), activity.getStartDate(), activity.getEndDate(), activity.getDiscount(), activity.getActivityId());
        return i;
    }

    @Override
    public Integer deleteActivity(Integer activityId) {
        /**
         * @description 根据传入活动id删除对应活动
         * @author JoneElmo
         * @date 2023-08-18 19:17
         * @param activityId
         * @return java.lang.Integer
         */
        String sql ="delete from tb_activity where activityId=?;";
        int i = BaseDao.updateInfo(sql, activityId);
        return i;
    }

    @Override
    public Double selectDiscount(LocalDateTime nowDate, Double money) {
        /**
         * @description 根据当前日期和结账金额查找优惠额度
         * @author JoneElmo
         * @date 2023-08-18 19:32
         * @param nowDate 系统当前日期
         * @param money 结账金额
         * @return java.lang.Double
         */
        String sql = "select min(discount) as count from tb_activity where ? between startDate and endDate and ?>=criticalTotal;";
        Double d = (Double) BaseDao.countInfo(sql, nowDate, money);
        return d;
    }
}
