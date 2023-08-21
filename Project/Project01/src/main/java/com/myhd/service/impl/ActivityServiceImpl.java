package com.myhd.service.impl;

import com.myhd.dao.impl.ActivityDaoImpl;
import com.myhd.pojo.Activity;
import com.myhd.service.IActivityService;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityServiceImpl implements IActivityService {

    private ActivityDaoImpl adi = new ActivityDaoImpl();

    public Boolean insertActivity(Activity activity) {
        Integer i = this.adi.insertActivity(activity);
        return i == 1?true:false;
    }

    public List<Activity> selectActivity(Integer start, Integer rows) {
        List<Activity> list = this.adi.selectActivity(start, rows);
        return list;
    }

    public Boolean updateActivity(Activity activity) {
        Integer i = this.adi.updateActivity(activity);
        return i == 1?true:false;
    }

    public Boolean deleteActivity(Integer activityId) {
        Integer i = this.adi.deleteActivity(activityId);
        return i == 1?true:false;
    }

    public Double selectDiscount(LocalDateTime nowDate, Double money) {
        Double discount = this.adi.selectDiscount(nowDate, money);
        return discount==null?1:discount;
    }
}
