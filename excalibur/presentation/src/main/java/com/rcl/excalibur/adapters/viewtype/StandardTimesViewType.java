package com.rcl.excalibur.adapters.viewtype;

import android.support.annotation.NonNull;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;

import java.util.List;

public class StandardTimesViewType implements RecyclerViewType {
    private String title;
    private List<String[]> daysAndTimes;

    public StandardTimesViewType(@NonNull String title, @NonNull List<String[]> daysAndTimes) {
        this.title = title;
        this.daysAndTimes = daysAndTimes;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public List<String[]> getDaysAndTimes() {
        return daysAndTimes;
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_STANDARD_TIMES;
    }
}
