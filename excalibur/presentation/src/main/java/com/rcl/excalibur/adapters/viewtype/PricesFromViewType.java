package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;


public class PricesFromViewType implements RecyclerViewType {

    private String adultPrice;
    private String childrenPrice;

    public PricesFromViewType(String adultPrice, String childrenPrice) {
        this.adultPrice = adultPrice;
        this.childrenPrice = childrenPrice;
    }

    public String getAdultPrice() {
        return adultPrice;
    }

    public String getChildrenPrice() {
        return childrenPrice;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_PRICES_FROM;
    }
}
