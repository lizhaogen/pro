package com.lzg.solr.service.entity;

import java.io.Serializable;

public class ProductResult implements Serializable {
    private Integer id;
    private String productName;
    private String productSalePoint;
    private String productInfo;
    private String productImage;
    private Integer productPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSalePoint() {
        return productSalePoint;
    }

    public void setProductSalePoint(String productSalePoint) {
        this.productSalePoint = productSalePoint;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
}
