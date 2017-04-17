
package com.rcl.excalibur.model;

public class SailingInfoModel {

    private Long duration;
    private ItineraryModel itinerary;
    private String shipCode;


    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
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
