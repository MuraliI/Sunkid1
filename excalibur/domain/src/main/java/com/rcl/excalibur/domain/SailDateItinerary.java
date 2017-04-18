package com.rcl.excalibur.domain;

import java.util.List;

public class SailDateItinerary {
    private String description;
    private List<SailDateEvent> events;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SailDateEvent> getEvents() {
        return events;
    }

    public void setEvents(List<SailDateEvent> events) {
        this.events = events;
    }
}
