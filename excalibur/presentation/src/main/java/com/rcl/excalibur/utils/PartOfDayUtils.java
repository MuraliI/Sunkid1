package com.rcl.excalibur.utils;


import android.support.annotation.NonNull;

import java.util.Calendar;

import static com.rcl.excalibur.model.PlannerProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_MORNING;

public final class PartOfDayUtils {
    private static final int NUMBER_OF_MINUTES_IN_HOUR = 60;

    private static final int MINUTES_START_MORNING = 360;
    private static final int MINUTES_START_AFTERNOON = 720;
    private static final int MINUTES_START_EVENING = 1020;
    private static final int MINUTES_START_LATE_NIGHT = 1380;

    private PartOfDayUtils() {
    }

    public static int getPartOfDayState(@NonNull Calendar date) {
        int state;

        int currentHourInMinutes = getCurrentHourInMinutes(date);
        if (currentHourInMinutes >= MINUTES_START_MORNING && currentHourInMinutes < MINUTES_START_AFTERNOON) {
            state = STATE_MORNING;
        } else if (currentHourInMinutes >= MINUTES_START_MORNING && currentHourInMinutes < MINUTES_START_EVENING) {
            state = STATE_AFTERNOON;
        } else if (currentHourInMinutes >= MINUTES_START_EVENING && currentHourInMinutes < MINUTES_START_LATE_NIGHT) {
            state = STATE_EVENING;
        } else {
            state = STATE_LATE_NIGHT;
        }

        return state;
    }

    static int getCurrentHourInMinutes(@NonNull Calendar date) {
        int currentHour = date.get(Calendar.HOUR_OF_DAY);
        int currentMinutes = date.get(Calendar.MINUTE);
        return currentHour * NUMBER_OF_MINUTES_IN_HOUR + currentMinutes;
    }
}
