package com.rcl.excalibur.adapters.viewtype;

import android.content.res.Resources;
import android.support.v4.util.Pair;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.domain.LocationOperationHour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TIMES;

public class TimesViewType implements RecyclerViewType {

    private String title;
    private List<Pair<String, String>> times;

    public TimesViewType(String title, List<Pair<String, String>> times) {
        this.title = title;
        this.times = times;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Pair<String, String>> getTimes() {
        return times;
    }

    public void setTimes(List<Pair<String, String>> times) {
        this.times = times;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE_TIMES;
    }

    public static TimesViewType getViewTypes(String title, Resources res, List<LocationOperationHour> operationHours) {

        List<Pair<String, String>> times = new ArrayList<>();
        for (LocationOperationHour operationHour : operationHours) {
            String dayNumber = res.getString(R.string.day_arg, operationHour.getDayNumber());
            String range = operationHour.getStartTime() + " - " + operationHour.getEndTime();
            times.add(new Pair<>(dayNumber, range));
        }

        Collections.sort(times, (o1, o2) -> {
            try {
                int dayA = Integer.parseInt(o1.first);
                int dayB = Integer.parseInt(o2.first);

                if (dayA < dayB) {
                    return -1;
                } else {
                    return 1;
                }
            } catch (Exception ex) {
                return 0;
            }
        });

        return new TimesViewType(title, times);
    }
}
