package com.rcl.excalibur.utils;


import com.rcl.excalibur.R;

import java.util.Calendar;

import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_MORNING;

public final class PartOfDayUtils {
    private static final int NUMBER_OF_MINUTES_IN_HOUR = 60;
    private static final int MINUTES_BOUNDARY_MORNING = 719;
    private static final int MINUTES_BOUNDARY_AFTERNOON = 1079;
    private static final int MINUTES_BOUNDARY_EVENING = 1259;
    private static final int MINUTES_BOUNDARY_NIGHT = 1439;

    private PartOfDayUtils() {
    }

    public static int getPartOfDayResource(Calendar date) {
        int value;
        int currentHourInMinutes = getCurrentHourInMinutes(date);

        if (currentHourInMinutes <= MINUTES_BOUNDARY_MORNING) {
            value = R.string.title_morning;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_MORNING + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_AFTERNOON) {
            value = R.string.title_afternoon;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_AFTERNOON + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_EVENING) {
            value = R.string.title_evening;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_EVENING + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_NIGHT) {
            value = R.string.title_late_night;
        } else {
            value = R.string.empty_string;
        }

        return value;
    }

    public static int getPartOfDayState(Calendar date) {
        int state;

        int currentHourInMinutes = getCurrentHourInMinutes(date);
        if (currentHourInMinutes <= MINUTES_BOUNDARY_MORNING) {
            state = STATE_MORNING;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_MORNING + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_AFTERNOON) {
            state = STATE_AFTERNOON;
        } else if (currentHourInMinutes >= MINUTES_BOUNDARY_AFTERNOON + 1 && currentHourInMinutes <= MINUTES_BOUNDARY_EVENING) {
            state = STATE_EVENING;
        } else {
            state = STATE_LATE_NIGHT;
        }

        return state;
    }

    public static int getCurrentHourInMinutes(Calendar date) {
        int currentHour = date.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = date.get(Calendar.MINUTE);
        return currentHour * NUMBER_OF_MINUTES_IN_HOUR + currentMinutes;
    }
}
