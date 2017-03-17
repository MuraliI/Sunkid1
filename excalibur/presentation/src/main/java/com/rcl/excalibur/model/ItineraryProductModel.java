package com.rcl.excalibur.model;

public class ItineraryProductModel {
    public static final String LOCATION_POINTER_FWD = "Forward";
    public static final String LOCATION_POINTER_AFT = "After";
    public static final String LOCATION_POINTER_MID = "Middle";

    private String productId;
    private String imageUrl;
    private String name;
    private String venue;
    private String deckNumber;
    private String locationPointer;
    private String date;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public String getDeckNumber() {
        return deckNumber;
    }

    public String getLocationPointer() {
        return locationPointer;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDeckNumber(String deckNumber) {
        this.deckNumber = deckNumber;
    }

    public void setLocationPointer(String locationPointer) {
        this.locationPointer = locationPointer;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {

        return date;
    }
}

