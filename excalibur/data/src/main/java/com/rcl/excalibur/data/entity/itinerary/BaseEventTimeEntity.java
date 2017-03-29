package com.rcl.excalibur.data.entity.itinerary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = BaseEventTimeEntity.TABLE_NAME)
public class BaseEventTimeEntity extends Model {

    public static final String TABLE_NAME = "base_event_time";
    public static final String COLUMN_EVENT_DATE = "event_date";
    public static final String COLUMN_EVENT_TIME = "event_time";
    public static final String COLUMN_EVENT_TIME_ZONE = "event_time_zone";


    @Column(name = COLUMN_EVENT_DATE)
    public String eventDate;
    @Column(name = COLUMN_EVENT_TIME)
    public String eventTime;
    @Column(name = COLUMN_EVENT_TIME_ZONE)
    public String eventTimeZone;

    public BaseEventTimeEntity() {
        super();
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTimeZone() {
        return eventTimeZone;
    }

    public void setEventTimeZone(String eventTimeZone) {
        this.eventTimeZone = eventTimeZone;
    }
}
