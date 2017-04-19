package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = CategoryEntity.TABLE_NAME)
public class CategoryEntity extends Model {

    public static final String TABLE_NAME = "category_product";

    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_NAME = "name";


    @Column(name = COLUMN_CATEGORY_ID)
    public String categoryId;
    @Column(name = COLUMN_DESCRIPTION)
    public String description;
    @Column(name = COLUMN_NAME)
    private String name;


    public CategoryEntity() {
        super();
    }

    public List<ChildCategoryProductEntity> getChildCategoryProducts() {
        return getMany(ChildCategoryProductEntity.class, ChildCategoryProductEntity.COLUMN_CATEGORY);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
