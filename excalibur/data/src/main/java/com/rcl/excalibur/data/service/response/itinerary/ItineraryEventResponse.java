package com.rcl.excalibur.data.service.response.itinerary;

public class ItineraryEventResponse {
    private String eventID;
    private String eventType;
    private String eventLocale;
    private EventLocationResponse eventLocation;
    private BaseEventTimeResponse eventStartTime;
    private BaseEventTimeResponse eventEndTime;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
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

    public BaseEventTimeResponse getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(BaseEventTimeResponse eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public BaseEventTimeResponse getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(BaseEventTimeResponse eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
