package com.rcl.excalibur.model.itinerary;


import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class CalendarSeparatorModel implements RecyclerViewType {

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_CALENDAR_VIEW;
    }
}
