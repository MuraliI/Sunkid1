package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.domain.ProductAdvisement;

import java.util.List;

public class ExpandableAccesibilityViewType implements RecyclerViewType {
    private String title;
    private List<ProductAdvisement> accessibilities;

    public ExpandableAccesibilityViewType(String title, List<ProductAdvisement> accessibilities) {
        this.title = title;
        this.accessibilities = accessibilities;
    }

    public String getTitle() {
        return title;
    }

    public List<ProductAdvisement> getAccessibilities() {
        return accessibilities;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ACCESSIBILITY_VIEW;
    }
}
