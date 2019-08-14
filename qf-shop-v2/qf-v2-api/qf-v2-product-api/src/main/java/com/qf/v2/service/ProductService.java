package com.qf.v2.service;


import com.qf.v2.common.base.BaseService;
import com.qf.v2.common.dto.ProductDTO;
import com.qf.v2.entity.Product;


public interface ProductService extends BaseService<Product> {
    Product getProduct(Integer id);
    Integer addProduct(ProductDTO product);
}
