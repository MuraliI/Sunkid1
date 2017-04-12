package com.rcl.excalibur.domain;

import java.util.List;


public class ProductCategory {

    private String categoryId;
    private String categoryDescription;
    private List<ProductTags> productTags;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<ProductTags> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<ProductTags> productTags) {
        this.productTags = productTags;
    }
}
