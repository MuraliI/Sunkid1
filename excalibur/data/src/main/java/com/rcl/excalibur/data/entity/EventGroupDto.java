package com.rcl.excalibur.data.bean;

import java.util.List;

public class EventGroupDto {
    private String eventGroupType;
    private String eventGroupDescription;
    private List<ItineraryEventDto> itineraryEvents;

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

    public List<ItineraryEventDto> getItineraryEvents() {
        return itineraryEvents;
    }

    public void setItineraryEvents(List<ItineraryEventDto> itineraryEvents) {
        this.itineraryEvents = itineraryEvents;
    }
}
