package com.rcl.excalibur.data.entity.itinerary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = ItineraryEventEntity.TABLE_NAME)
public class ItineraryEventEntity extends Model {

    public static final String TABLE_NAME = "itinerary_event";
    public static final String COLUMN_EVENT_ID = "event_id";
    public static final String COLUMN_EVENT_TYPE = "event_type";
    public static final String COLUMN_EVENT_LOCALE = "event_locale";
    public static final String COLUMN_EVENT_LOCATION = "event_location";
    public static final String COLUMN_EVENT_START_TIME = "event_start_time";
    public static final String COLUMN_EVENT_END_TIME = "event_end_time";

    @Column(name = COLUMN_EVENT_ID)
    public String eventID;
    @Column(name = COLUMN_EVENT_TYPE)
    public String eventType;
    @Column(name = COLUMN_EVENT_LOCALE)
    public String eventLocale;
    @Column(name = COLUMN_EVENT_LOCATION)
    public EventLocationEntity eventLocation;
    @Column(name = COLUMN_EVENT_START_TIME)
    public BaseEventTimeEntity eventStartTime;
    @Column(name = COLUMN_EVENT_END_TIME)
    public BaseEventTimeEntity eventEndTime;


    public ItineraryEventEntity() {
        super();
    }

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

    public EventLocationEntity getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(EventLocationEntity eventLocation) {
        this.eventLocation = eventLocation;
    }

    public BaseEventTimeEntity getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(BaseEventTimeEntity eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public BaseEventTimeEntity getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(BaseEventTimeEntity eventEndTime) {
        this.eventEndTime = eventEndTime;
    }
}
