package com.rcl.excalibur.data.service.response.itinerary;

import java.util.List;

public class ListEventGroupResponse<T> {
    private List<T> itineraryEventGroups;

    public List<T> getItineraryEventGroups() {
        return itineraryEventGroups;
    }

    public void setItineraryEventGroups(List<T> itineraryEventGroups) {
        this.itineraryEventGroups = itineraryEventGroups;
    }
}
