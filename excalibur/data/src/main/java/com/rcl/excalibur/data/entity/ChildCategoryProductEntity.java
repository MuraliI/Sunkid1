package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ChildCategoryProductEntity.TABLE_NAME)
public class ChildCategoryProductEntity extends Model {

    public static final String TABLE_NAME = "child_category_product";
    public static final String COLUMN_CHILD_CATEGORY_ID = "child_category_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY = "category";

    @Column(name = COLUMN_CHILD_CATEGORY_ID)
    private String categoryId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_CATEGORY)
    private CategoryEntity category;

    public ChildCategoryProductEntity() {
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
