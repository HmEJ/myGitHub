package com.myhd.service.impl;

import com.myhd.dao.impl.EmpUserDaoImpl;
import com.myhd.pojo.EmpUser;
import com.myhd.service.IEmpUserService;
import org.apache.log4j.Logger;

public class EmpUserServiceImpl implements IEmpUserService {
    private EmpUserDaoImpl edi = new EmpUserDaoImpl();
    Logger logger = Logger.getLogger(EmpUserServiceImpl.class);

    public Boolean LoginService(EmpUser empUser) {
        Long i = (long)this.edi.countEmpUserById(empUser.getEmpUserId());
        if (i == 1L) {
            EmpUser user = this.edi.selectEmpUSerByInfo(empUser);
            if (empUser.getEmpUserPwd().equals(user.getEmpUserPwd())) {
                this.logger.info("密码正确");
                return true;
            } else {
                this.logger.error("密码错误");
                return false;
            }
        } else {
            this.logger.info("用户不存在");
            return false;
        }
    }

    public Boolean RegisterService(EmpUser empUser) {
        Integer integer = edi.insertEmpUser(empUser);
        logger.info("获取到的用户id: "+integer);
        return true;
    }
}
