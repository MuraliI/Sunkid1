package com.rcl.excalibur.utils;


import android.content.res.Resources;

import com.rcl.excalibur.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {

    private static final int AFTERNOON = 12;
    public static final int MINUTES_IN_HOUR = 60;

    private DateUtils() {

    }

    public static boolean isIncomingDateBeforeCurrent(String date) {
        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        boolean isBefore = false;
        try {
            Date incomingDate = df.parse(date);
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            Date currentDate = df.parse(reportDate);
            if (incomingDate.compareTo(currentDate) >= 0) {
                isBefore = false;
            } else {
                isBefore = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isBefore;
    }

    public static boolean isCurrentAfterIncomingDate(String date) {
        final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        boolean isBefore = true;
        try {
            Date incomingDate = df.parse(date);
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            Date currentDate = df.parse(reportDate);
            if (incomingDate.compareTo(currentDate) >= 0) {
                isBefore = true;
            } else {
                isBefore = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isBefore;
    }

    public static String getFormatedDates(String startDateString, String endDateString, Resources resources) throws ParseException {
        Date startDate = new SimpleDateFormat("MM/dd/yyyy").parse(startDateString);
        Date endDate = new SimpleDateFormat("MM/dd/yyyy").parse(endDateString);

        Calendar calendarStartDate = Calendar.getInstance();
        calendarStartDate.setTime(startDate);
        Calendar calendarEndDate = Calendar.getInstance();
        calendarEndDate.setTime(endDate);

        int startMonth = calendarStartDate.get(Calendar.MONTH);
        int startDay = calendarStartDate.get(Calendar.DAY_OF_MONTH);
        int endMonth = calendarEndDate.get(Calendar.MONTH);
        int endDay = calendarEndDate.get(Calendar.DAY_OF_MONTH);

        final String[] months = resources.getStringArray(R.array.array_months);
        final StringBuilder dateFormatted = new StringBuilder();

        if (startMonth == endMonth) {
            dateFormatted.append(months[startMonth]);
            dateFormatted.append(startDay);
            dateFormatted.append(resources.getString(R.string.hyphen_with_spaces));
            dateFormatted.append(endDay);
        } else {
            dateFormatted.append(months[startMonth]);
            dateFormatted.append(startDay);
            dateFormatted.append(resources.getString(R.string.hyphen_with_spaces));
            dateFormatted.append(months[endMonth]);
            dateFormatted.append(endDay);
        }

        return dateFormatted.toString();

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
        if (minutes == 0) {
            builder.append(hour);
        } else {
            builder.append(hour).append(resources.getString(R.string.itinerary_product_view_colon)).append(minutes);
        }

        builder.append(amPm == Calendar.AM
                ? resources.getString(R.string.itinerary_product_view_am)
                : resources.getString(R.string.itinerary_product_view_pm));

        return builder.toString();
    }
}
