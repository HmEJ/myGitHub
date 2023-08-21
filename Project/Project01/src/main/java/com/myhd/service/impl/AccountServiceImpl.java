package com.myhd.service.impl;

import com.myhd.dao.impl.AccountDaoImpl;
import com.myhd.pojo.Account;
import com.myhd.service.IAccountService;

public class AccountServiceImpl implements IAccountService {
    private AccountDaoImpl adi = new AccountDaoImpl();

    public Boolean updateAccount(Double money) {
        Integer i = this.adi.updateAccount(money);
        return i == 1?true:false;
    }

    public Double selectAccount(Account account) {
        Account ac = this.adi.selectAccount(account);
        return ac.getAccountPwd() == null ? null : ac.getMoney();
    }
}
