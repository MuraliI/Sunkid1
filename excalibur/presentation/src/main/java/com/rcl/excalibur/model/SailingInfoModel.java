
package com.rcl.excalibur.model;

public class SailingInfoModel {

    private int duration;
    private ItineraryModel itinerary;
    private String shipCode;


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ItineraryModel getItinerary() {
        return itinerary;
    }

    public void setItinerary(ItineraryModel itinerary) {
        this.itinerary = itinerary;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }
}
