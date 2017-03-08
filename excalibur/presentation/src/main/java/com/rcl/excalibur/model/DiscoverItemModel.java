package com.rcl.excalibur.model;


import java.util.List;
import java.util.Map;

public class DiscoverItemModel {
    public static final String TYPE_DINING = "Dining";
    private String discoverId;
    private String imageUrl;
    private String category;
    private String title;
    private String hours;
    private String promotionText;
    private String type;
    //FIXME this are temporary attributes. Will be fixed when we start querying the database
    private String subtitle;
    private boolean reservationRequired;
    private String promotionTitle;
    private String promotionDescription;
    private List<String> discoverItemTimes;
    private Map<String, String> properties;
    private String[] priceRange;
    private String description;
    private String accessibility;
    private String legal;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscoverId() {
        return discoverId;
    }

    public void setDiscoverId(String discoverId) {
        this.discoverId = discoverId;
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

    public String getPromotionText() {
        return promotionText;
    }

    public void setPromotionText(String promotionText) {
        this.promotionText = promotionText;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public List<String> getDiscoverItemTimes() {
        return discoverItemTimes;
    }

    public void setDiscoverItemTimes(List<String> discoverItemTimes) {
        this.discoverItemTimes = discoverItemTimes;
    }

    public String[] getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String[] priceRange) {
        this.priceRange = priceRange;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public boolean isReservationRequired() {
        return reservationRequired;
    }

    public void setReservationRequired(boolean reservationRequired) {
        this.reservationRequired = reservationRequired;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
