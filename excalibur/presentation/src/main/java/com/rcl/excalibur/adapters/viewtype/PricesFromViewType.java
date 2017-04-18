package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.HashMap;
import java.util.Map;


public class PricesFromViewType implements RecyclerViewType {

    private String title;
    private String subtitle;
    private Map<String, String> prices;

    public PricesFromViewType(String title, String subtitle, Map<String, String> prices) {
        this.title = title;
        this.subtitle = subtitle;
        this.prices = prices;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Map<String, String> getPrices() {
        return prices;
    }

    public void setPrices(HashMap<String, String> prices) {
        this.prices = prices;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_PRICES_FROM;
    }
}
