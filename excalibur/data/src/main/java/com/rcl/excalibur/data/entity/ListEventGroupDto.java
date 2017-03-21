package com.rcl.excalibur.data.bean;

import java.util.List;

public class ListEventGroupDto<T> {
    private List<T> itineraryEventGroups;

    public List<T> getItineraryEventGroups() {
        return itineraryEventGroups;
    }

    public void setItineraryEventGroups(List<T> itineraryEventGroups) {
        this.itineraryEventGroups = itineraryEventGroups;
    }
}
