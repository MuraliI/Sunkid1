package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = SubCategoryEntity.TABLE_NAME)
public class SubCategoryEntity extends Model {

    public static final String TABLE_NAME = "category";
    public static final String COLUMN_SUB_CATEGORY_ID = "sub_category_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    @Column(name = COLUMN_SUB_CATEGORY_ID, unique = true, index = true)
    private String categoryId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;

    public SubCategoryEntity() {
        super();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ChildCategoryEntity> getChildCategories() {
        return getMany(ChildCategoryEntity.class, ChildCategoryEntity.COLUMN_SUB_CATEGORY);
    }
}
