package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;


@Table(name = ProductEntity.TABLE_NAME)
public class ProductEntity extends Model {

    public static final String TABLE_NAME = "product";

    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PRODUCT_CLASS = "product_class";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_RANK = "rank";
    public static final String COLUMN_UPCHARGE_ICON = "upcharge_icon";
    public static final String COLUMN_RESERVATION_REQUIRED = "reservation_required";
    public static final String COLUMN_SCHEDULABLE = "schedulable";
    public static final String COLUMN_ACTIVITY_LEVEL = "activity_level";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_COST_TYPE = "cost_type";
    public static final String COLUMN_STARTING_FROM_PRICE = "starting_from_price";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_SHORT_DESCRIPTION = "short_description";
    public static final String COLUMN_LONG_DESCRIPTION = "long_description";
    public static final String COLUMN_PREFERENCE = "preference";
    public static final String COLUMN_PRODUCT_MEDIA = "product_media";

    @Column(name = COLUMN_PRODUCT_ID, unique = true, index = true)
    public long productId;
    @Column(name = COLUMN_CODE)
    public String code;
    @Column(name = COLUMN_TYPE)
    public TypeEntity type;
    @Column(name = COLUMN_PRODUCT_CLASS)
    public String productClass;
    @Column(name = COLUMN_CATEGORY)
//    TODO In the service arrive a list of Categories. But for the moment we retrive only one this
    public CategoryEntity category;
    @Column(name = COLUMN_RANK)
    public int rank;
    @Column(name = COLUMN_UPCHARGE_ICON)
    public int upchargeIcon;
    @Column(name = COLUMN_RESERVATION_REQUIRED)
    public boolean reservationRequired;
    @Column(name = COLUMN_SCHEDULABLE)
    public boolean schedulable;
    @Column(name = COLUMN_ACTIVITY_LEVEL)
    public ActivityLevelEntity activityLevel;
    @Column(name = COLUMN_LOCATION)
    public LocationEntity location;
    @Column(name = COLUMN_DURATION)
    public DurationEntity duration;
    @Column(name = COLUMN_COST_TYPE)
    public CostTypeEntity costType;
    @Column(name = COLUMN_STARTING_FROM_PRICE)
    public StartingFromPriceEntity startingFromPrice;
    @Column(name = COLUMN_TITLE)
    public String title;
    @Column(name = COLUMN_SHORT_DESCRIPTION)
    public String shortDescription;
    @Column(name = COLUMN_LONG_DESCRIPTION)
    public String longDescription;
    @Column(name = COLUMN_PREFERENCE)
    public PreferenceEntity preference;
    @Column(name = COLUMN_PRODUCT_MEDIA)
    public MediaEntity productMedia;

    public ProductEntity() {
        super();
    }

    public List<AdvisementEntity> getAdvisements() {
        return getMany(AdvisementEntity.class, AdvisementEntity.COLUMN_PRODUCT);
    }

    public List<RestrictionEntity> getRestrictions() {
        return getMany(RestrictionEntity.class, RestrictionEntity.COLUMN_PRODUCT);
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setType(TypeEntity type) {
        this.type = type;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getUpchargeIcon() {
        return upchargeIcon;
    }

    public void setUpchargeIcon(int upchargeIcon) {
        this.upchargeIcon = upchargeIcon;
    }

    public boolean isReservationRequired() {
        return reservationRequired;
    }

    public void setReservationRequired(boolean reservationRequired) {
        this.reservationRequired = reservationRequired;
    }

    public boolean isSchedulable() {
        return schedulable;
    }

    public void setSchedulable(boolean schedulable) {
        this.schedulable = schedulable;
    }

    public ActivityLevelEntity getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevelEntity activityLevel) {
        this.activityLevel = activityLevel;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public DurationEntity getDuration() {
        return duration;
    }

    public void setDuration(DurationEntity duration) {
        this.duration = duration;
    }

    public CostTypeEntity getCostType() {
        return costType;
    }

    public void setCostTypeEntity(CostTypeEntity costType) {
        this.costType = costType;
    }

    public StartingFromPriceEntity getStartingFromPrice() {
        return startingFromPrice;
    }

    public void setStartingFromPrice(StartingFromPriceEntity startingFromPrice) {
        this.startingFromPrice = startingFromPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public PreferenceEntity getPreference() {
        return preference;
    }

    public void setPreference(PreferenceEntity preference) {
        this.preference = preference;
    }

    public MediaEntity getProductMedia() {
        return productMedia;
    }

    public void setProductMedia(MediaEntity productMedia) {
        this.productMedia = productMedia;
    }
}
