
package com.rcl.excalibur.data.service.response;

public class ProductLocationResponse {

    private String locationCode;
    private String locationType;
    private String operatingHoursEnd;
    private String operatingHoursStart;


    public ProductLocationResponse() {
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


    public String getOperatingHoursEnd() {
        return operatingHoursEnd;
    }

    public void setOperatingHoursEnd(String operatingHoursEnd) {
        this.operatingHoursEnd = operatingHoursEnd;
    }

    public String getOperatingHoursStart() {
        return operatingHoursStart;
    }

    public void setOperatingHoursStart(String operatingHoursStart) {
        this.operatingHoursStart = operatingHoursStart;
    }
}
