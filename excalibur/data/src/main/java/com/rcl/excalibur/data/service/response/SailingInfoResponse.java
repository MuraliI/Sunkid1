package com.rcl.excalibur.data.service.response;


public class SailingInfoResponse {
    private DateItineraryResponse itinerary;
    private String shipCode;
    private int duration;

    public DateItineraryResponse getItinerary() {
        return itinerary;
    }

    public void setItinerary(DateItineraryResponse itinerary) {
        this.itinerary = itinerary;
    }

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
}
