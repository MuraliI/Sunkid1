package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class AccessibilityViewType implements RecyclerViewType {

    private String content;

    public AccessibilityViewType(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ACCESSIBILITY;
    }
}
