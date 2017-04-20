package com.rcl.excalibur.data.utils;

import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import timber.log.Timber;

public final class DateUtil {

    public static final String DATE_FORMAT = "yyyyMMddHHmm";
    public static final String TIME_FORMAT = "HH:mm";

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
}
