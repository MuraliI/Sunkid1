package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProductCategoryResponse {
    @SerializedName("categoryid")
    private String categoryId;
    private String categoryDescription;
    private List<ProductTagsResponse> productTags;

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

    public List<ProductTagsResponse> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<ProductTagsResponse> productTags) {
        this.productTags = productTags;
    }
}
