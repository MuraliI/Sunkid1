package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.Gson;

import java.util.List;

@Table(name = PromotionEntity.TABLE_NAME)
public class PromotionEntity extends Model {

    public static final String TABLE_NAME = "promotion";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY_ID = "category_id";
    public static final String COLUMN_LOCATION_CODE = "location_code";
    public static final String COLUMN_PRODUCTS = "products";

    @Column(name = COLUMN_TITLE)
    public String title;
    @Column(name = COLUMN_DESCRIPTION)
    public String description;
    @Column(name = COLUMN_CATEGORY_ID)
    public String categoryId;
    @Column(name = COLUMN_LOCATION_CODE)
    public String locationCode;
    @Column(name = COLUMN_PRODUCTS)
    public String products; //TODO temporaly stored as json. In the future it is will add to product

    public PromotionEntity() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public Integer[] getProducts() {
        return new Gson().fromJson(products, Integer[].class);
    }

    public void setProducts(List<Integer> products) {
        this.products = new Gson().toJson(products);
    }

    public void setProducts(Integer[] products) {
        this.products = new Gson().toJson(products);
    }
}
