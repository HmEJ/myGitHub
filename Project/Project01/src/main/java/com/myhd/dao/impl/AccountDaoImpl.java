package com.myhd.dao.impl;


import com.myhd.dao.IAccountDao;
import com.myhd.pojo.Account;
import com.myhd.util.BaseDao;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @description 实现类
 * @author JoneElmo
 * @date 2023-08-18 17:29
 * @version 1.0
 * @package com.myhd.dao.impl
 * @class AccountDaoImpl
 */
public class AccountDaoImpl implements IAccountDao {
    private Logger logger = Logger.getLogger(AccountDaoImpl.class);

    /**
     * @description 获取管理员账户信息
     * @author JoneElmo
     * @date 2023-08-18 17:39
     * @param account
     * @return com.myhd.pojo.Account
     */
    @Override
    public Account selectAccount(Account account) {

        String sql = "select accountId, accountPwd, money from tb_account where accountPwd=?;";
        List<?> list = BaseDao.selectInfo(sql, Account.class,account.getAccountPwd());
        return list.isEmpty()?new Account(): (Account) list.get(0);
    }
    /**
     * @description 更新账户金额
     * @author JoneElmo
     * @date 2023-08-18 19:17
     * @param money 传入更新的金额
     * @return java.lang.Integer
     */
    @Override
    public Integer updateAccount(Double money) {

        String sql = "update tb_account set money=money+? where accountId = 1;";
        int i = BaseDao.updateInfo(sql, money);
        return i;
    }
}
