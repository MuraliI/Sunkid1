package com.rcl.excalibur.model.itinerary;


import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class SeparatorModel implements RecyclerViewType {

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_SEPARATOR_VIEW;
    }
}
