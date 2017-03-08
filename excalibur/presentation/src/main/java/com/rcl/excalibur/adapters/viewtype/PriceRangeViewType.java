package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class PriceRangeViewType implements RecyclerViewType {

    private int count;

    public PriceRangeViewType(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_PRICE_RANGE;
    }
}
