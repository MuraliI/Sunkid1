package com.rcl.excalibur.data.service.response;


public class ProductDurationResponse {
    private boolean isAtYourLeisure;
    private int durationInMinutes;
    private int leadTimeInMinutes;
    private int lagTimeInMinutes;

    public boolean isAtYourLeisure() {
        return isAtYourLeisure;
    }

    public void setAtYourLeisure(boolean atYourLeisure) {
        isAtYourLeisure = atYourLeisure;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getLeadTimeInMinutes() {
        return leadTimeInMinutes;
    }

    public void setLeadTimeInMinutes(int leadTimeInMinutes) {
        this.leadTimeInMinutes = leadTimeInMinutes;
    }

    public int getLagTimeInMinutes() {
        return lagTimeInMinutes;
    }

    public void setLagTimeInMinutes(int lagTimeInMinutes) {
        this.lagTimeInMinutes = lagTimeInMinutes;
    }
}
