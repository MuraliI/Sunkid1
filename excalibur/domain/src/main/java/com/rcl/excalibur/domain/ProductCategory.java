package com.rcl.excalibur.domain;

import java.util.List;



public class ProductCategory {
    private String categoryid;
    private String categoryDescription;
    private List<ProductTags> productTags;

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
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
