package com.qf.v2.mapper;

import com.qf.v2.common.base.BaseDao;
import com.qf.v2.entity.Product;

import java.util.List;

public interface ProductMapper extends BaseDao<Product> {

    List<Product> selectProducts();
}