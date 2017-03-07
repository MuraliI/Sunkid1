package com.rcl.excalibur.adapters.viewtype;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

public class DinnerTimesViewType implements RecyclerViewType {
    private String lunchTime;
    private String lunchMenu;
    private String dinnerTime;
    private String dinnerMenu;

    public DinnerTimesViewType(String lunchTime, String lunchMenu, String dinnerTime,
                               String dinnerMenu) {
        this.lunchTime = lunchTime;
        this.lunchMenu = lunchMenu;
        this.dinnerTime = dinnerTime;
        this.dinnerMenu = dinnerMenu;
    }

    public String getLunchTime() {
        return lunchTime;
    }

    public String getLunchMenu() {
        return lunchMenu;
    }

    public String getDinnerTime() {
        return dinnerTime;
    }

    public String getDinnerMenu() {
        return dinnerMenu;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_DINNER_TIMES;
    }
}
