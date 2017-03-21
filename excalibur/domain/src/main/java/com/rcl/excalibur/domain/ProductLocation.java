package com.rcl.excalibur.domain;


public class ProductLocation {

    private String locationId;
    private String locationCode;
    private String locationType;
    private int operatingHoursStart;
    private int operatingHoursEnd;

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

    public int getOperatingHoursStart() {
        return operatingHoursStart;
    }

    public void setOperatingHoursStart(int operatingHoursStart) {
        this.operatingHoursStart = operatingHoursStart;
    }

    public int getOperatingHoursEnd() {
        return operatingHoursEnd;
    }

    public void setOperatingHoursEnd(int operatingHoursEnd) {
        this.operatingHoursEnd = operatingHoursEnd;
    }
}
