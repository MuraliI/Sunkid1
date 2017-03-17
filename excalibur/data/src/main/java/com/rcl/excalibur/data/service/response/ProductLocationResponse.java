
package com.rcl.excalibur.data.service.response;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ProductLocationResponse {

    private String locationCode;
    private String locationType;
    private Long operatingHoursEnd;
    private Long operatingHoursStart;


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

    public Long getOperatingHoursEnd() {
        return operatingHoursEnd;
    }

    public void setOperatingHoursEnd(Long operatingHoursEnd) {
        this.operatingHoursEnd = operatingHoursEnd;
    }

    public Long getOperatingHoursStart() {
        return operatingHoursStart;
    }

    public void setOperatingHoursStart(Long operatingHoursStart) {
        this.operatingHoursStart = operatingHoursStart;
    }
}
