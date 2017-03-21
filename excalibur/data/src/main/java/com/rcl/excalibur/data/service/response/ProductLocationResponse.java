package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class ProductLocationResponse {
    @SerializedName("locationID")
    private String locationId;
    private String locationCode;
    private String locationType;
    private String operatingHoursStart;
    private String operatingHoursEnd;

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
}
