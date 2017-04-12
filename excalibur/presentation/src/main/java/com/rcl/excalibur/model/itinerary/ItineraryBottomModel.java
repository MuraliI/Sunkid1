package com.rcl.excalibur.model.itinerary;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class ItineraryBottomModel implements RecyclerViewType {
    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ITINERARY_BOTTOM_VIEW;
    }
}
