package com.rcl.excalibur.adapters.viewtype;

import android.content.res.Resources;
import android.support.v4.util.Pair;

import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.LocationOperationHour;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.rcl.excalibur.adapters.base.RecyclerViewConstants.VIEW_TYPE_TIMES;

public class TimesViewType implements RecyclerViewType {

    private String title;
    private List<Pair<String, String>> times;

    private static final Comparator<Pair<String, String>> PAIR_COMPARATOR = (o1, o2) -> {
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
    };

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

    public static void addTimesViewTypes(List<RecyclerViewType> recyclerViewTypes, String title, List<LocationOperationHour> operationHours) {

        List<Pair<String, String>> times = new ArrayList<>();
        for (LocationOperationHour operationHour : operationHours) {
            String dayNumber = operationHour.getDayNumber();
            String range = operationHour.getStartTime() + " - " + operationHour.getEndTime();
            times.add(new Pair<>(dayNumber, range));
        }
        Collections.sort(times, PAIR_COMPARATOR);
        recyclerViewTypes.add(new TimesViewType(title, times));
    }

    public static void addTimesViewTypes(List<RecyclerViewType> recyclerViewTypes, String title,
                                         Resources res, List<Offering> offerings, SailDateInfo sailDateInfo) {
//        if (sailDateInfo == null || sailDateInfo.getShipCode() == null) {
//            return;
//        }
//        long dateTime = Long.parseLong(SAILING_ID);
        long dateTime = 1493596801000L;
        Calendar sailingDate = Calendar.getInstance();
        sailingDate.setTimeInMillis(dateTime);
//        int duration = Integer.parseInt(sailDateInfo.getDuration());
        int duration = 5;
        int dayCounter = 1;

        List<Pair<String, Date>> voyageDays = new ArrayList<>();
        do {
            voyageDays.add(new Pair(String.valueOf(dayCounter), sailingDate.getTime()));
            sailingDate.add(Calendar.DATE, 1);
            dayCounter++;
        } while (dayCounter <= duration);

        List<Pair<String, String>> times = new ArrayList<>();
        for (Pair<String, Date> day : voyageDays) {
            String dateFilter = DateUtil.getHourlessDateParser().format(day.second);
            Observable.fromIterable(offerings)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .filter(object -> dateFilter.equals(DateUtil.getHourlessDateParser().format(object.getDate())))
                    .toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(offeringsForDay -> {
                        String dayNumber = day.first;
                        StringBuilder hours = new StringBuilder();
                        for (Offering offering : offeringsForDay) {
                            hours.append(DateUtils.getDateHour(offering.getDate(), res));
                            hours.append(",");
                        }
                        String hoursSet = hours.substring(0, hours.length() - 1).toString();
                        times.add(new Pair<>(dayNumber, hoursSet));
                    });
        }
        Collections.sort(times, PAIR_COMPARATOR);
        recyclerViewTypes.add(new TimesViewType(title, times));
    }
}
