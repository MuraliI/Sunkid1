package com.rcl.excalibur.data.service.response;

import java.util.List;


public class ProductLocationResponse {

    private String locationCode;
    private String locationTitle;
    private String locationType;
    private String latitude;
    private String longitude;
    private List<DeckInfoResponse> deckInfo;
    private List<OperationHourResponse> locationOperationHours;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationType() {
        return locationType;
    }


    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
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

    public List<DeckInfoResponse> getDeckInfo() {
        return deckInfo;
    }

    public void setDeckInfo(List<DeckInfoResponse> deckInfo) {
        this.deckInfo = deckInfo;
    }

    public List<OperationHourResponse> getLocationOperationHours() {
        return locationOperationHours;
    }

    public void setLocationOperationHours(List<OperationHourResponse> locationOperationHours) {
        this.locationOperationHours = locationOperationHours;
    }
}
