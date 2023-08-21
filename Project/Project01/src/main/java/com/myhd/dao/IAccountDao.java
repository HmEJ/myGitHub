package com.myhd.dao;

import com.myhd.pojo.Account;

/**
 * className IAccountDao 收银账户Dao层接口
 * packageName com.myhd.dao.impl
 * Description IAccountDao
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:13
 */
public interface IAccountDao {

    /**
     * Description: selectAccount 进入账户页面时需要使用到的功能,返回的集合对象一般只有一个对象,
     * 当然查询时如果没有查到对象返回的集合是空, 在从返回的集合获取对象时需要判断
     * 当前集合是否为空, 如果为空, 则表示进入账户的密码是错的, 提示进入账户页面失败,
     * 查询请使用BaseDao.selectInfo()方法,
     * @return com.myhd.pojo.Account
     * @param account 传进来用于查询的账户实体对象
     * @author huian
     * @Date 2023/8/18
     * */
    Account selectAccount(Account account);

    /**
     * Description: updateAccount 根据订单项自动传进来的金额进行账户更新,
     * 不允许直接人为操纵账户更新, 账户金额更新应只存在与订单业务中,
     * 更新请使用BaseDao.updateInfo()方法
     * @return java.lang.Integer
     * @param money 用于更新的金额钱数
     * @author huian
     * @Date 2023/8/18
     * */
    Integer updateAccount(Double money);
}
