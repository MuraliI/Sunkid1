package com.rcl.excalibur.data.service.response;


import java.util.List;

public class DateItineraryResponse {
    private String description;
    private List<SailDateEventResponse> events;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SailDateEventResponse> getEvents() {
        return events;
    }

    public void setEvents(List<SailDateEventResponse> events) {
        this.events = events;
    }
}
