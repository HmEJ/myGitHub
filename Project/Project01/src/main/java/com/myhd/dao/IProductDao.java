package com.myhd.dao;

import com.myhd.pojo.Product;

import java.util.List;

/**
 * className IProductDao 商品Dao层接口
 * packageName com.myhd.dao
 * Description IProductDao
 *
 * @author huian
 * @version 1.0
 * @email 2634692718@qq.com
 * @Date: 2023/8/18 11:15
 */
public interface IProductDao {
    /**
     * Description: selectByproductId 根据商品id查询到对应商品信息, 同时
     * 把查询到的商品信息填入订单项实体对象中去
     * @return com.myhd.pojo.Product 返回查询到的商品对象
     * @param productId 商品id
     * @author huian
     * @Date 2023/8/18
     * */
    Product selectByproductId(Integer productId);

    /**
     * @description 查询所有商品
     * @author JoneElmo
     * @date 2023-08-20 17:26
     * @param null
     * @return Product
     */
    List<Product> selectAllProduct();
}
