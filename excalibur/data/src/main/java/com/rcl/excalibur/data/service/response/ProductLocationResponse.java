package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;
import com.rcl.excalibur.domain.LocationOperationHour;

import java.util.List;


public class ProductLocationResponse {
    @SerializedName("locationID")
    private String locationId;
    private String locationCode;
    private String locationTitle;
    private String locationType;
    private String latitude;
    private String longitude;
    private String locationVenue;
    private String locationPort;
    private List<DeckInfoResponse> deckInfo;
    private List<LocationOperationHour> locationOperationHours;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

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

    public String getLocationVenue() {
        return locationVenue;
    }

    public void setLocationVenue(String locationVenue) {
        this.locationVenue = locationVenue;
    }

    public String getLocationPort() {
        return locationPort;
    }

    public void setLocationPort(String locationPort) {
        this.locationPort = locationPort;
    }

    public List<DeckInfoResponse> getDeckInfo() {
        return deckInfo;
    }

    public void setDeckInfo(List<DeckInfoResponse> deckInfo) {
        this.deckInfo = deckInfo;
    }

    public List<LocationOperationHour> getLocationOperationHours() {
        return locationOperationHours;
    }

    public void setLocationOperationHours(List<LocationOperationHour> locationOperationHours) {
        this.locationOperationHours = locationOperationHours;
    }
}
