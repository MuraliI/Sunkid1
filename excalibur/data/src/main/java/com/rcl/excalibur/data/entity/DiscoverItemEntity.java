package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = DiscoverItemEntity.TABLE_NAME)
@Deprecated
public class DiscoverItemEntity extends Model {

    public static final String TABLE_NAME = "discover_item";
    public static final String COLUMN_DISCOVER_ITEM_ID = "discover_item_id";
    public static final String COLUMN_IMAGE_URL = "image_url";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_HOURS = "hours";

    public static final String COLUMN_SUB_TITLE = "sub_title";
    public static final String COLUMN_RESERVATION_REQUIRED = "reservation_required";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LEGAL = "legal";
    public static final String COLUMN_PROMOTION_TITLE = "promotion_title";
    public static final String COLUMN_PROMOTION_DESCRIPTION = "promotion_description";
    public static final String COLUMN_STANDARD_TIME = "standard_time";

    @Column(name = COLUMN_DISCOVER_ITEM_ID, unique = true)
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
    @Column(name = COLUMN_SUB_TITLE)
    private String subTitle;
    @Column(name = COLUMN_RESERVATION_REQUIRED)
    private String reservationRequired;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_PROMOTION_TITLE)
    private String promotionTitle;
    @Column(name = COLUMN_PROMOTION_DESCRIPTION)
    private String promotionDescription;
    @Column(name = COLUMN_LEGAL)
    private String legal;

    public DiscoverItemEntity() {
        super();
    }

    public String getDiscoverItemId() {
        return discoverItemId;
    }

    public void setDiscoverItemId(String discoverItemId) {
        this.discoverItemId = discoverItemId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getReservationRequired() {
        return reservationRequired;
    }

    public void setReservationRequired(String reservationRequired) {
        this.reservationRequired = reservationRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }

    public String getPromotionDescription() {
        return promotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        this.promotionDescription = promotionDescription;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

}
