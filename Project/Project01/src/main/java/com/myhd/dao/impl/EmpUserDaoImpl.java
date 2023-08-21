package com.myhd.dao.impl;

import com.myhd.dao.IEmpUserDao;
import com.myhd.pojo.EmpUser;
import com.myhd.util.BaseDao;

import java.util.List;

public class EmpUserDaoImpl implements IEmpUserDao {
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-18 19:30
     * @version 1.0
     * @package com.myhd.dao.impl
     * @class EmpUserDaoImpl
     */
    @Override
    public Integer countEmpUserById(Integer empUserId) {
        /**
         * @description 根据员工id查询员工是否存在。如果存在返回1，不存在返回0.
         * @author JoneElmo
         * @date 2023-08-18 19:39
         * @param empUserId
         * @return java.lang.Integer
         */
        String sql = "select cast(count(*) as signed) as count from tb_empuser where empUserId=?;";
        Long i  = (Long) BaseDao.countInfo(sql, empUserId);
        return Math.toIntExact(i);
    }

    @Override
    public EmpUser selectEmpUSerByInfo(EmpUser empUser) {
        /**
         * @description 根据输入的账号密码查询用户。
         * @author JoneElmo
         * @date 2023-08-18 19:40
         * @param empUser
         * @return com.myhd.pojo.EmpUser
         */
        String sql = "select empUserId, empUserName, empUserDuty, empUserPwd from tb_empuser where empUserId=? and empUserPwd=?;";
        List<?> list = BaseDao.selectInfo(sql, EmpUser.class, empUser.getEmpUserId(), empUser.getEmpUserPwd());
        return list.isEmpty()?new EmpUser(): (EmpUser) list.get(0);
    }

    @Override
    public Integer insertEmpUser(EmpUser empUser) {
        /**
         * @description 插入用户信息。返回影响的行数。插入成功返回1，否则返回0；
         * @author JoneElmo
         * @date 2023-08-18 19:44
         * @param empUser
         * @return java.lang.Integer 返回的是插入用户的id
         */
        String sql = "insert into tb_empuser(empUserName, empUserDuty, empUserPwd) VALUES (?,?,?);";
        BaseDao.updateInfo(sql, empUser.getEmpUserName(), empUser.getEmpUserDuty(), empUser.getEmpUserPwd());
        String sql1 = "select max(empUserId) as count from tb_empuser;";
        return (Integer) BaseDao.countInfo(sql1);
    }
}
