package com.rcl.excalibur.model;


import java.util.List;

public class DiscoverItemModel {
    private String imageUrl;
    private String category;
    private String type;
    private String title;
    private String hours;
    private String subTitle;
    private String reservationRequired;
    private String description;
    private String promotionTitle;
    private String promotionDescription;
    private String legal;
    private StandardTimeModel standardTime;
    private List<AccessibilityModel> accessibilities;
    private List<PriceModel> prices;
    private List<PropertyModel> properties;

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

    public StandardTimeModel getStandardTime() {
        return standardTime;
    }

    public void setStandardTime(StandardTimeModel standardTime) {
        this.standardTime = standardTime;
    }

    public List<AccessibilityModel> getAccessibilities() {
        return accessibilities;
    }

    public void setAccessibilities(List<AccessibilityModel> accessibilities) {
        this.accessibilities = accessibilities;
    }

    public List<PriceModel> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceModel> prices) {
        this.prices = prices;
    }

    public List<PropertyModel> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyModel> properties) {
        this.properties = properties;
    }
}
