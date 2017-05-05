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
    public static final String COLUMN_CATEGORY = "category";

    @Column(name = COLUMN_CHILD_CATEGORY_ID)
    private String childCategoryId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_CATEGORY)
    private CategoryEntity category;

    public ChildCategoryEntity() {
        super();
    }

    public String getChildCategoryId() {
        return childCategoryId;
    }

    public void setChildCategoryId(String childCategoryId) {
        this.childCategoryId = childCategoryId;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
