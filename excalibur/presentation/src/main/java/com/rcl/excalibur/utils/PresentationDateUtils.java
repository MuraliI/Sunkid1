package com.rcl.excalibur.utils;


import android.content.res.Resources;

import com.rcl.excalibur.R;

import java.util.Calendar;
import java.util.Date;

public final class PresentationDateUtils {

    private static final int AFTERNOON = 12;
    public static final int MINUTES_IN_HOUR = 60;
    private static final int TEN_HOURS = 10;
    private static final String ZERO_STRING = "0";

    private PresentationDateUtils() {

    }

    public static String getDateHour(Date date, Resources resources) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR);
        int amPm = calendar.get(Calendar.AM_PM);

        if (hour == 0 && amPm == Calendar.PM) {
            hour = AFTERNOON;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(hour);

        builder.append(amPm == Calendar.AM
                ? resources.getString(R.string.itinerary_product_view_am)
                : resources.getString(R.string.itinerary_product_view_pm));

        return builder.toString();
    }

    public static String getDateTime(Date date, Resources resources) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int amPm = calendar.get(Calendar.AM_PM);

        if (hour == 0 && amPm == Calendar.PM) {
            hour = AFTERNOON;
        }

        StringBuilder builder = new StringBuilder();
        if (hour < TEN_HOURS) {
            builder.append(ZERO_STRING);
        }
        builder.append(hour).append(resources.getString(R.string.itinerary_product_view_colon));
        if (minutes < TEN_HOURS) {
            builder.append(ZERO_STRING);
        }
        builder.append(minutes);
        builder.append(amPm == Calendar.AM
                ? resources.getString(R.string.itinerary_product_view_am)
                : resources.getString(R.string.itinerary_product_view_pm));

        return builder.toString();
    }
}
