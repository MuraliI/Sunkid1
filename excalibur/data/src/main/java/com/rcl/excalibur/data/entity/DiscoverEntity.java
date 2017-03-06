package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DiscoverEntity.TABLE_NAME)
public class DiscoverEntity extends Model {

    public static final String TABLE_NAME = "discover";
    public static final String COLUMN_DISCOVER_ID = "discover_id";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_PROMOTION_TEXT_ACTITITY = "promotion_text_actitity";

    @Column(name = COLUMN_DISCOVER_ID)
    private String discoverId;
    @Column(name = COLUMN_IMAGE_URL)
    private String imageUrl;
    @Column(name = COLUMN_CATEGORY)
    private String category;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_HOURS)
    private String hours;
    @Column(name = COLUMN_PROMOTION_TEXT_ACTITITY)
    private String promotionTextActitity;

    public DiscoverEntity() {
        super();
    }

    public String getDiscoverId() {
        return discoverId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getHours() {
        return hours;
    }

    public String getPromotionTextActitity() {
        return promotionTextActitity;
    }

    public String getType() {
        return type;
    }
}

