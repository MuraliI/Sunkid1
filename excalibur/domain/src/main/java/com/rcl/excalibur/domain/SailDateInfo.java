package com.rcl.excalibur.domain;


public class SailDateInfo {
    private String shipCode;
    private String duration;
    private SailDateItinerary itinerary;

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public SailDateItinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(SailDateItinerary itinerary) {
        this.itinerary = itinerary;
    }
}
