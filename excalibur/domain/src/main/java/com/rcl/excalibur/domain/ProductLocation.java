package com.rcl.excalibur.domain;


public class ProductLocation {

    private String locationName;
    private String locationId;
    private String locationCode;
    private String locationType;
    private String locationVenue;
    private String locationPort;
    private int locationDeckNumber;
    private String locationDirection;
    private LocationOperationHour[] locationOperationHours;

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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public LocationOperationHour[] getLocationOperationHours() {
        return locationOperationHours;
    }

    public void setLocationOperationHours(LocationOperationHour[] locationOperationHours) {
        this.locationOperationHours = locationOperationHours;
    }
}
