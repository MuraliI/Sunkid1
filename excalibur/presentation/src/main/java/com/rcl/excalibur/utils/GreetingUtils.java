package com.rcl.excalibur.utils;

import com.rcl.excalibur.R;

import java.util.GregorianCalendar;

public final class GreetingUtils {

    private static final int NUMBER_OF_MINUTES_IN_HOUR = 60;
    private static final int MINUTES_BOUNDARY_GOOD_MORNING = 719;
    private static final int MINUTES_BOUNDARY_GOOD_AFTERNOON = 1079;
    private static final int MINUTES_BOUNDARY_GOOD_EVENING = 1259;
    private static final int MINUTES_BOUNDARY_GOOD_NIGHT = 1439;

    private GreetingUtils() {
    }

    public static int getGreetingByDate(GregorianCalendar date) {

        int value;
        int currentHourInMinutes = getCurrentHourInMinutes(date);

        if (currentHourInMinutes <= MINUTES_BOUNDARY_GOOD_MORNING) {
            value = R.string.title_good_morning;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_GOOD_MORNING + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_GOOD_AFTERNOON) {
            value = R.string.title_good_afternoon;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_GOOD_AFTERNOON + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_GOOD_EVENING) {
            value = R.string.title_good_evening;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_GOOD_EVENING + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_GOOD_NIGHT) {
            value = R.string.title_good_night;
        } else {
            value = R.string.title_hello;
        }

        return value;
    }

    public static int getCurrentHourInMinutes(GregorianCalendar date) {
        int currentHour = date.get(date.HOUR_OF_DAY);
        int currentMinutes = date.get(date.MINUTE);
        return currentHour * NUMBER_OF_MINUTES_IN_HOUR + currentMinutes;
    }
}
