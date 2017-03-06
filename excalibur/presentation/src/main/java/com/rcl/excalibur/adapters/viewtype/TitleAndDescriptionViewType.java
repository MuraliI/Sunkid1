package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class TitleAndDescriptionViewType implements RecyclerViewType {

    private String title;
    private String description;

    public TitleAndDescriptionViewType(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_TITLE_AND_DESCRIPTION;
    }
}
