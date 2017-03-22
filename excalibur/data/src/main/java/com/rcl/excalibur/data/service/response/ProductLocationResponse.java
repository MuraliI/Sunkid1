package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class ProductLocationResponse {
    @SerializedName("locationID")
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


    public void setOperatingHoursStart(int operatingHoursStart) {
        this.operatingHoursStart = operatingHoursStart;
    }

    public int getOperatingHoursEnd() {
        return operatingHoursEnd;
    }

    public void setOperatingHoursEnd(int operatingHoursEnd) {
        this.operatingHoursEnd = operatingHoursEnd;
    }

    public int getOperatingHoursStart() {
        return operatingHoursStart;
    }
}
