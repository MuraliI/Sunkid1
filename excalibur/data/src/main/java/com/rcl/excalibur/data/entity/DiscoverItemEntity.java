package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DiscoverItemEntity.TABLE_NAME)
public class DiscoverItemEntity extends Model {

    public static final String TABLE_NAME = "discover_item";
    public static final String COLUMN_DISCOVER_ITEM_ID = "discover_item_id";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_PROMOTION_TEXT = "promotion_text";

    @Column(name = COLUMN_DISCOVER_ITEM_ID)
    private String discoverItemId;
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
    @Column(name = COLUMN_PROMOTION_TEXT)
    private String promotionText;

    public DiscoverItemEntity() {
        super();
    }

    public String getDiscoverItemId() {
        return discoverItemId;
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

    public String getPromotionText() {
        return promotionText;
    }

    public String getType() {
        return type;
    }
}

