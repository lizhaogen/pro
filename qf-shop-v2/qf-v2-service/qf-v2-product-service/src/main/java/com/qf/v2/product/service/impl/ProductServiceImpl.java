package com.qf.v2.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.v2.common.base.BaseDao;
import com.qf.v2.common.base.BaseServiceImpl;
import com.qf.v2.common.dto.ProductDTO;
import com.qf.v2.entity.Product;
import com.qf.v2.entity.ProductInfo;
import com.qf.v2.mapper.ProductInfoMapper;
import com.qf.v2.mapper.ProductMapper;
import com.qf.v2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public BaseDao<Product> getBaseDao() {
        return productMapper;
    }


    @Override
    public Product getProduct(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<Product> selectProducts() {
        return productMapper.selectProducts();
    }

    @Override
    public PageInfo<Product> getPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = selectProducts();
        PageInfo<Product> pageInfo = new PageInfo<Product>(list, 10);

        return pageInfo;
    }


    @Override
    public Integer addProduct(ProductDTO product) {
        //添加商品
        //获取product部分 存入到相应表中
        Product tpro = product.getProduct();
        tpro.setTypeName("生活用品");
        productMapper.insertSelective(tpro);

        System.out.println(tpro.getId());
        //添加商品描述
        ProductInfo productInfo = new ProductInfo();
        productInfo.setPid(tpro.getId());
        productInfo.setProductInfo(product.getProductInfo());

        productInfoMapper.insertSelective(productInfo);
        return tpro.getId();
    }


}
