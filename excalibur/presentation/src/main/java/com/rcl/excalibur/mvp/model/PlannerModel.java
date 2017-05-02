package com.rcl.excalibur.mvp.model;

import android.support.annotation.NonNull;

import com.rcl.excalibur.domain.utils.ConstantsUtil;

public final class PlannerModel {

    private static final int DEFAULT_TIME_VALUE = 0;
    private static final int MIDDAY_TIME_VALUE = 12;
    private static final int MIDNIGHT_TIME_VALUE = 24;
    private static final int MIN_TIME_HOUR = 120000;
    private static final int MAX_TIME_HOUR = 240000;
    private static final int BEGIN_TIME_INDEX = 0;
    private static final int MIDDLE_TIME_INDEX = 2;
    private static final int END_TIME_INDEX = 4;

    private static final String TIME_FORMAT_SEPARATOR = ":";
    private static final String TIME_FORMAT_AM = " AM";
    private static final String TIME_FORMAT_PM = " PM";

    public String getTimeFormat(@NonNull int arrivalTime) {
        String time = ConstantsUtil.EMPTY;
        if (arrivalTime > 0) {
            String arrivalTimeString = String.valueOf(arrivalTime);
            int endString = arrivalTimeString.length();
            String timeAmPm;
            String timeHour;
            String timeMinute = getStringTimeData(arrivalTimeString, endString, DEFAULT_TIME_VALUE, true);
            if (arrivalTime >= MIN_TIME_HOUR && arrivalTime < MAX_TIME_HOUR) {
                timeAmPm = TIME_FORMAT_PM;
                timeHour = getStringTimeData(arrivalTimeString, endString, MIDDAY_TIME_VALUE, false);
            } else {
                timeAmPm = TIME_FORMAT_AM;
                timeHour = getStringTimeData(arrivalTimeString, endString, DEFAULT_TIME_VALUE, false);
            }
            time = timeHour + TIME_FORMAT_SEPARATOR + timeMinute +  timeAmPm;
        }
        return time;
    }

    private String getStringTimeData(String arrivalTimeString, int endString, int dif, boolean isMinute) {
        String stringTime;
        if (isMinute) {
            stringTime = arrivalTimeString.substring(endString - END_TIME_INDEX, endString - MIDDLE_TIME_INDEX);
        } else {
            String subString = arrivalTimeString.substring(BEGIN_TIME_INDEX, MIDDLE_TIME_INDEX);
            int hour = Integer.valueOf(subString);
            int subStringValue = (hour == MIDNIGHT_TIME_VALUE || hour == MIDDAY_TIME_VALUE) ? MIDDAY_TIME_VALUE : (hour - dif);
            stringTime = String.valueOf(subStringValue);
        }
        return stringTime;
    }
}
