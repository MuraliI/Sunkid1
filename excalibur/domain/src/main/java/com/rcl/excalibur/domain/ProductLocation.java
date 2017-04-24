package com.rcl.excalibur.domain;


import java.util.List;

public class ProductLocation {

    private String locationCode;
    private String locationTitle;
    private String locationType;
    private String latitude;
    private String longitude;
    private List<LocationDeckInfo> deckInfo;
    private List<LocationOperationHour> locationOperationHours;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<LocationDeckInfo> getDeckInfo() {
        return deckInfo;
    }

    public void setDeckInfo(List<LocationDeckInfo> deckInfo) {
        this.deckInfo = deckInfo;
    }

    public List<LocationOperationHour> getLocationOperationHours() {
        return locationOperationHours;
    }

    public void setLocationOperationHours(List<LocationOperationHour> locationOperationHours) {
        this.locationOperationHours = locationOperationHours;
    }
}
