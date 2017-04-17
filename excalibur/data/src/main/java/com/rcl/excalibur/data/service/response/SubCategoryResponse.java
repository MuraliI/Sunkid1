package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SubCategoryResponse {

    private String categoryName;
    private String categoryDescription;
    @SerializedName("categoryid")
    private String categoryId;
    private List<ChildCategoryResponse> childCategory;

    public SubCategoryResponse() {
    }

    public List<ChildCategoryResponse> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<ChildCategoryResponse> childCategory) {
        this.childCategory = childCategory;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
