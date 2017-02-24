package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = ItemEntity.TABLE_NAME)
public class ItemEntity extends Model {

    public static final String TABLE_NAME = "item";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE_URL = "image_url";

    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_IMAGE_URL)
    private String imageUrl;


    public ItemEntity() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
