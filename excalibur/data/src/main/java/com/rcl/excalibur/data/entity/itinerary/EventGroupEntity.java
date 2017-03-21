package com.rcl.excalibur.data.entity.itinerary;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = EventGroupEntity.TABLE_NAME)
public class EventGroupEntity extends Model {

    public static final String TABLE_NAME = "event_group";
    public static final String COLUMN_EVENT_GROUP_TYPE = "event_group_type";
    public static final String COLUMN_EVENT_GROUP_DESCRIPTION = "event_group_description";
    public static final String COLUMN_ITINERARY_EVENTS = "itinerary_events";

    @Column(name = COLUMN_EVENT_GROUP_TYPE)
    private String eventGroupType;
    @Column(name = COLUMN_EVENT_GROUP_DESCRIPTION)
    private String eventGroupDescription;
    @Column(name = COLUMN_ITINERARY_EVENTS)
    private ItineraryEventEntity itineraryEvents;

    public EventGroupEntity() {
        super();
    }

    public String getEventGroupType() {
        return eventGroupType;
    }

    public void setEventGroupType(String eventGroupType) {
        this.eventGroupType = eventGroupType;
    }

    public String getEventGroupDescription() {
        return eventGroupDescription;
    }

    public void setEventGroupDescription(String eventGroupDescription) {
        this.eventGroupDescription = eventGroupDescription;
    }
}
