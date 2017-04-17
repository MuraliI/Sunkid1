package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;



public class ItemsResponse {
    @SerializedName("categoryid")
    private String categoryId;
    private String categoryName;
    private String categoryDescription;


    public ItemsResponse() {

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
