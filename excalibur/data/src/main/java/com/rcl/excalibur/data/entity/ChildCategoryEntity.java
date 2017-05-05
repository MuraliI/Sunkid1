package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ChildCategoryEntity.TABLE_NAME)
public class ChildCategoryEntity extends Model {

    public static final String TABLE_NAME = "child_category";
    public static final String COLUMN_CHILD_CATEGORY_ID = "child_category_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_SUB_CATEGORY = "sub_category";

    @Column(name = COLUMN_CHILD_CATEGORY_ID, unique = true, index = true)
    private String categoryId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_SUB_CATEGORY)
    private SubCategoryEntity subCategory;

    public ChildCategoryEntity() {
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

    public SubCategoryEntity getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryEntity subCategory) {
        this.subCategory = subCategory;
    }
}
