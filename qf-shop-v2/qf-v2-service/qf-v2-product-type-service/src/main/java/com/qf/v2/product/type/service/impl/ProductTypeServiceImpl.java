package com.qf.v2.product.type.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.service.ProductTypeService;
import com.qf.v2.common.base.BaseDao;
import com.qf.v2.common.base.BaseServiceImpl;
import com.qf.v2.entity.ProductType;
import com.qf.v2.mapper.ProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType> implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public BaseDao<ProductType> getBaseDao() {
        return productTypeMapper;
    }
}
