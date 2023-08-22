package com.myhd.dao.impl;

import com.myhd.dao.IProductDao;
import com.myhd.pojo.Product;
import com.myhd.util.BaseDao;

import java.util.List;
/**
 * @description
 * @author JoneElmo
 * @date 2023-08-18 20:44
 * @version 1.0
 * @package com.myhd.dao.impl
 * @class ProductDaoImpl
 */
public class ProductDaoImpl implements IProductDao {
    /**
     * @description 根据产品id查询产品信息
     * @author JoneElmo
     * @date 2023-08-18 20:44
     * @param productId
     * @return com.myhd.pojo.Product
     */
    @Override
    public Product selectByproductId(Integer productId) {

        String sql = "select productId, productName, productPrice from tb_product where productId=?";
        List<?> list = BaseDao.selectInfo(sql, Product.class,productId);
        return list.isEmpty()?null: (Product) list.get(0);
    }
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 17:26
     * @return com.myhd.pojo.Product
     */
    @Override
    public List<Product> selectAllProduct() {

        String sql = "select * from tb_product";
        List<Product> list = (List<Product>) BaseDao.selectInfo(sql, Product.class);
        return list;
    }
}
