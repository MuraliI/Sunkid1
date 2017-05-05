package com.rcl.excalibur.domain;

import java.util.List;

public class Category {

    public static final String SHOREX_CATEGORY = "shorex";
    public static final String ACTIVITIES_CATEGORY = "activities";
    public static final String ENTERTAINMENT_CATEGORY = "entertainment";
    public static final String DINING_CATEGORY = "dining";
    public static final String SPA_CATEGORY = "spa";
    public static final String SHOPPING_CATEGORY = "shop";
    public static final String GUEST_SERVICES_CATEGORY = "guest";

    private String categoryId;
    private String description;
    private List<String> tags;

    public Category() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
