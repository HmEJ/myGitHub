package com.myhd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * className Activity
 * packageName com.myhd.pojo
 * Description Activity 优惠活动实体类
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    private Integer activityId;
    private String activityName;
    private Double criticalTotal;
    private Date startDate;
    private Date endDate;
    private Double discount;
}
