package com.rcl.excalibur.data.service.response;

import java.util.List;



public class ProductCategoryResponse {
    private String categoryid;
    private String categoryDescription;
    private List<ProductTagsResponse> productTags;

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

    public List<ProductTagsResponse> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<ProductTagsResponse> productTags) {
        this.productTags = productTags;
    }
}
