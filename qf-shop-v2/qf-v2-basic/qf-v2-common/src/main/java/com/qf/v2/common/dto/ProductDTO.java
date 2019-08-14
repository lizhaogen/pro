package com.qf.v2.common.dto;

import com.qf.v2.entity.Product;

import java.io.Serializable;


public class ProductDTO implements Serializable {
    private Product product;
    private String productInfo;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "product=" + product +
                ", productInfo='" + productInfo + '\'' +
                '}';
    }
}
