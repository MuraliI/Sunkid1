package com.rcl.excalibur.adapters.viewtype;


import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class DescriptionViewType implements RecyclerViewType {

    private String description;

    public DescriptionViewType(String description) {

        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_DESCRIPTION;
    }
}
