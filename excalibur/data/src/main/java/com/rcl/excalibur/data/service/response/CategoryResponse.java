package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CategoryResponse {

    private String categoryDescription;
    @SerializedName("categoryid")
    private long categoryId;
    private List<String> productTags;

    public CategoryResponse() {
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<String> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<String> productTags) {
        this.productTags = productTags;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}
