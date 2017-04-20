
package com.rcl.excalibur.model;

import java.util.List;

public class ItineraryModel {

    private String description;
    private int indexCurrentDay = -1;
    private List<EventModel> events;

    public int getIndexCurrentDay() {
        return indexCurrentDay;
    }

    public void setIndexCurrentDay(int indexCurrentDay) {
        this.indexCurrentDay = indexCurrentDay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}
