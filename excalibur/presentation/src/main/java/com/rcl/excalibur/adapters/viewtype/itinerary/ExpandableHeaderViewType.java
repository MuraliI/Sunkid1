package com.rcl.excalibur.adapters.viewtype.itinerary;


import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class ExpandableHeaderViewType implements RecyclerViewType {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_HEADER_VIEW;
    }
}
