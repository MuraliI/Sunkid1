package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class ProductLocationResponse {
    @SerializedName("locationID")
    private String locationId;
    private String locationCode;
    private String locationType;
    private String operatingHoursStart;
    private String operatingHoursEnd;
    private String locationVenue;
    private String locationPort;
    private int locationDeckNumber;
    private String locationDirection;

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

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getOperatingHoursStart() {
        return operatingHoursStart;
    }

    public void setOperatingHoursStart(String operatingHoursStart) {
        this.operatingHoursStart = operatingHoursStart;
    }

    public String getOperatingHoursEnd() {
        return operatingHoursEnd;
    }

    public void setOperatingHoursEnd(String operatingHoursEnd) {
        this.operatingHoursEnd = operatingHoursEnd;
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

    public int getLocationDeckNumber() {
        return locationDeckNumber;
    }

    public void setLocationDeckNumber(int locationDeckNumber) {
        this.locationDeckNumber = locationDeckNumber;
    }

    public String getLocationDirection() {
        return locationDirection;
    }

    public void setLocationDirection(String locationDirection) {
        this.locationDirection = locationDirection;
    }
}
