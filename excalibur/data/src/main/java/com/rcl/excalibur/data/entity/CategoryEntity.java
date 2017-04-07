package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;

import java.util.List;

@Table(name = CategoryEntity.TABLE_NAME)
public class CategoryEntity extends Model {

    public static final String TABLE_NAME = "category";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_TAGS = "tags";

    @Column(name = COLUMN_CATEGORY_ID)
    public String categoryId;
    @Column(name = COLUMN_DESCRIPTION)
    public String description;
    @Column(name = COLUMN_TAGS)
    public String tags;

    public CategoryEntity() {
        super();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String[] getTags() {
        return new Gson().fromJson(tags, String[].class);
    }

    public void setTags(List<String> tags) {
        this.tags = new Gson().toJson(tags);
    }

    public void setTags(String[] tags) {
        this.tags = new Gson().toJson(tags);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
