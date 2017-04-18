package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SailingInfoResponse {
    private static final String ITINERARY = "itinerary";

    @SerializedName(ITINERARY)
    private List<DateItineraryResponse> itineraries;
    private String shipCode;
    private int duration;

    public List<DateItineraryResponse> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<DateItineraryResponse> itineraries) {
        this.itineraries = itineraries;
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
