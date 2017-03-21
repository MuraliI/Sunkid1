package com.rcl.excalibur.data.bean;

public class ItineraryEventDto {
    private String eventID;
    private String eventType;
    private String eventLocale;
    private EventLocationDto eventLocation;
    private BaseEventTimeDto eventStartTime;
    private BaseEventTimeDto eventEndTime;

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

    public EventLocationDto getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocationDto eventLocation) {
        this.eventLocation = eventLocation;
    }

    public BaseEventTimeDto getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(BaseEventTimeDto eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public BaseEventTimeDto getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(BaseEventTimeDto eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
