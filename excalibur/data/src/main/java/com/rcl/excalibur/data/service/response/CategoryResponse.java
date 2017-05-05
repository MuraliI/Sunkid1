package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CategoryResponse {

    private String categoryDescription;
    @SerializedName("categoryid")
    private String categoryId;
    private String categoryName;
    private List<ChildCategoryResponse> childCategory;

    public CategoryResponse() {
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

    public List<ChildCategoryResponse> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<ChildCategoryResponse> childCategory) {
        this.childCategory = childCategory;
    }
}
