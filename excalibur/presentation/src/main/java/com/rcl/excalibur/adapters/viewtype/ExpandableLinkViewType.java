package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class ExpandableLinkViewType implements RecyclerViewType {

    private String title;
    private String[] content;

    public ExpandableLinkViewType(String title, String[] content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String[] getContent() {
        return content.clone();
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_EXPANDABLE_LINK;
    }
}
