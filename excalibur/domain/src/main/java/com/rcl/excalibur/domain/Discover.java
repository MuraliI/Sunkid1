package com.rcl.excalibur.domain;

public class Discover {
    private String discoverId;
    private String imageUrl;
    private String category;
    private String title;
    private String hours;
    private String promotionTextActitity;
    private String type;

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

    public String getPromotionTextActitity() {
        return promotionTextActitity;
    }

    public void setPromotionTextActitity(String promotionTextActitity) {
        this.promotionTextActitity = promotionTextActitity;
    }
}
