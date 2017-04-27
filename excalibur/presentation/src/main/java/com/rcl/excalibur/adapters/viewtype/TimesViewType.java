package com.rcl.excalibur.adapters.viewtype;

import android.content.res.Resources;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.domain.LocationOperationHour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TIMES;

public class TimesViewType implements RecyclerViewType {

    private String title;
    private Map<String, String> times;

    public TimesViewType(String title, Map<String, String> times) {
        this.title = title;
        this.times = times;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, String> getTimes() {
        return times;
    }

    public void setTimes(Map<String, String> times) {
        this.times = times;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE_TIMES;
    }

    public static TimesViewType getViewTypes(String title, Resources res, List<LocationOperationHour> operationHours) {

        Map<String, String> map = new HashMap<>();
        for (LocationOperationHour operationHour : operationHours) {
            String dayNumber = res.getString(R.string.day_arg, operationHour.getDayNumber());
            String range = operationHour.getStartTime() + " - " + operationHour.getEndTime();
            map.put(dayNumber, range);
        }

        return new TimesViewType(title, map);
    }
}
