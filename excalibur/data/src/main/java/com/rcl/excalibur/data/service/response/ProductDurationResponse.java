
package com.rcl.excalibur.data.service.response;

public class ProductDurationResponse {

    private Boolean isAtYourLeisure;
    private Long durationInMinutes;
    private Long lagTimeInMinutes;
    private Long leadTimeInMinutes;

    public ProductDurationResponse() {
    }

    public Long getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Long durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Boolean getIsAtYourLeisure() {
        return isAtYourLeisure;
    }

    public void setIsAtYourLeisure(Boolean isAtYourLeisure) {
        this.isAtYourLeisure = isAtYourLeisure;
    }

    public Long getLagTimeInMinutes() {
        return lagTimeInMinutes;
    }

    public void setLagTimeInMinutes(Long lagTimeInMinutes) {
        this.lagTimeInMinutes = lagTimeInMinutes;
    }

    public Long getLeadTimeInMinutes() {
        return leadTimeInMinutes;
    }

    public void setLeadTimeInMinutes(Long leadTimeInMinutes) {
        this.leadTimeInMinutes = leadTimeInMinutes;
    }

}
