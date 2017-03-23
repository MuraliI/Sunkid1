package com.rcl.excalibur.data.service.response.itinerary;

public class ItineraryEventResponse {
    private String thumbnail;
    private String eventID;
    private String eventName;
    private String eventType;
    private String eventLocale;
    private EventLocationResponse eventLocation;
    private EventTimeResponse eventStartTime;
    private EventTimeResponse eventEndTime;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventLocale() {
        return eventLocale;
    }

    public void setEventLocale(String eventLocale) {
        this.eventLocale = eventLocale;
    }

    public EventLocationResponse getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocationResponse eventLocation) {
        this.eventLocation = eventLocation;
    }

    public EventTimeResponse getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(EventTimeResponse eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public EventTimeResponse getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(EventTimeResponse eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
