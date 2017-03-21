package com.rcl.excalibur.data.service.response.itinerary;

import java.util.List;

public class EventGroupResponse {
    private String eventGroupType;
    private String eventGroupDescription;
    private List<ItineraryEventResponse> itineraryEvents;

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

    public List<ItineraryEventResponse> getItineraryEvents() {
        return itineraryEvents;
    }

    public void setItineraryEvents(List<ItineraryEventResponse> itineraryEvents) {
        this.itineraryEvents = itineraryEvents;
    }
}
