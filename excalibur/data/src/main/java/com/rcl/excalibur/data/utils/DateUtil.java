package com.rcl.excalibur.data.utils;

import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

public final class DateUtil {


    public static final String DATE_FORMAT = "yyyyMMddHHmm";
    public static final String HOURLESS_DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_PARSING_ERROR = "Date parsing failed in %s1, parser exception: %s2";
    public static final String TIME_FORMAT = "h:mm a";
    public static final String DATE_FORMAT_ISO = "yyyyMMdd'T'HHmmss";

    private DateUtil() {
    }

    @Nullable
    public static Date parseDateResponse(String dateStr, String timeStr) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        Date date = null;
        String str = dateStr + timeStr;
        try {
            date = dateFormatter.parse(str);
        } catch (ParseException e) {
            Timber.d(e.getMessage());
        }
        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return dateFormatter.format(date);
    }

    public static String parseTime(Date date, TimeZone timeZone) {
        DateFormat dateFormatter = new SimpleDateFormat(TIME_FORMAT);
        dateFormatter.setTimeZone(timeZone);
        return dateFormatter.format(date);
    }

    public static SimpleDateFormat getStandardDateParser() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.US);
    }

    public static SimpleDateFormat getHourlessDateParser() {
        return new SimpleDateFormat(HOURLESS_DATE_FORMAT, Locale.US);
    }

    public static SimpleDateFormat getDateFormatISO() {
        return new SimpleDateFormat(DATE_FORMAT_ISO, Locale.US);
    }

    public static String getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + String.valueOf(calendar.get(Calendar.MINUTE));
    }


}
