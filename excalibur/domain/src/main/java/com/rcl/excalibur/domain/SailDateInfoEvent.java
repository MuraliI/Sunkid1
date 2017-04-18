package com.rcl.excalibur.domain;


public class SailDateInfoEvent {
    private String shipCode;
    private int duration;
    private SailDateItinerary itinerary;

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SailDateItinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(SailDateItinerary itinerary) {
        this.itinerary = itinerary;
    }
}
