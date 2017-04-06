package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.model.ProductAccessibilityModel;

import java.util.List;

public class ExpandableAccesibilityViewType implements RecyclerViewType {
    private String title;
    private List<ProductAccessibilityModel> accessibilities;

    public ExpandableAccesibilityViewType(String title, List<ProductAccessibilityModel> accessibilities) {
        this.title = title;
        this.accessibilities = accessibilities;
    }

    public String getTitle() {
        return title;
    }

    public List<ProductAccessibilityModel> getAccessibilities() {
        return accessibilities;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ACCESSIBILITY_VIEW;
    }
}
