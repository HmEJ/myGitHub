package com.myhd.service.impl;

import com.myhd.dao.impl.ProductDaoImpl;
import com.myhd.pojo.Product;
import com.myhd.service.IProductService;
/**
 * @description
 * @author JoneElmo
 * @date 2023-08-20 15:03
 * @version 1.0
 * @package com.myhd.service.impl
 * @class ProductServiceImpl
 */
public class ProductServiceImpl implements IProductService {

    private ProductDaoImpl pdi = new ProductDaoImpl();
    /**
     * @description
     * @author JoneElmo
     * @date 2023-08-20 15:04
     * @param productId
     * @return com.myhd.pojo.Product
     */
    @Override
    public Product selectByproductId(Integer productId) {

        Product product = pdi.selectByproductId(productId);
        return product;
    }
}
