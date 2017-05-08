package com.rcl.excalibur.domain;

import java.util.List;


@Deprecated
public class SubCategory {
    private String categoryName;
    private String categoryDescription;
    private String categoryId;
    private List<ChildCategory> childCategory;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ChildCategory> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<ChildCategory> childCategory) {
        this.childCategory = childCategory;
    }
}
