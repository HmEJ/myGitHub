package com.myhd.service;

import com.myhd.pojo.Product;

/**
 * className IProductService
 * packageName com.myhd.service
 * Description IProductService
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 21:06
 */
public interface IProductService {
    /**
     * Description: selectByproductId 根据商品id查询到对应商品信息, 同时
     * 把查询到的商品信息填入订单项实体对象中去
     * 建议查询使用ProductDaoImpl.selectByproductId()方法
     * @return com.myhd.pojo.Product 返回查询到的商品对象
     * @param productId 商品id
     * @author huian
     * @Date 2023/8/18
     * */
    Product selectByproductId(Integer productId);
}
