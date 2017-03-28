package com.rcl.excalibur.model.itinerary;


import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class ItinerarySeparatorModel implements RecyclerViewType {


    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_CALENDAR_VIEW;
    }
}
