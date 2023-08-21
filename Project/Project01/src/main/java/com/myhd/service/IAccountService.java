package com.myhd.service;

import com.myhd.pojo.Account;

/**
 * className IAccountService 账户服务层接口
 * packageName com.myhd.service
 * Description IAccountService
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 21:02
 */
public interface IAccountService {
    /**
     * Description: updateAccount 更新金额, 建议调用底层AccountDaoImp.updateAccount()方法
     * @return java.lang.Boolean 返回金额更新是否成功
     * @param money 更新的金额
     * @author huian
     * @Date 2023/8/19
     * */
    Boolean updateAccount(Double money);

    /**
     * Description: selectAccount 需要根据传进来的对象进行查询比较,
     * 如果account信息错误, 则返回一个空的对象, 信息正确, 拿出对象里面的金额进行返回
     * @return java.lang.Double 返回的金额
     * @param account 传入的账户对象
     * @author huian
     * @Date 2023/8/19
     * */
    Double selectAccount(Account account);
}
