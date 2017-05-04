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
    private static final int TIME_LENGTH = 4;

    public static final String DATE_FORMAT = "yyyyMMddHHmm";
    public static final String HOURLESS_DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_PARSING_ERROR = "Date parsing failed in %s1, parser exception: %s2";
    public static final String TIME_FORMAT = "h:mm a";
    public static final String DATE_FORMAT_ISO = "yyyyMMdd'T'HHmmss";
    public static final String HOUR_MINUTE_FORMAT = "HHmm";
    public static final String DATE_STANDARD_FORMAT = "yyyy/MM/dd";

    private DateUtil() {
    }

    @Nullable
    public static Date parseDateResponse(String dateStr, String timeStr) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        Date date = null;

        if (timeStr.length() < TIME_LENGTH) {
            timeStr = 0 + timeStr;
        }

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

    public static String getTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + String.valueOf(calendar.get(Calendar.MINUTE));
    }

    public static String formattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_STANDARD_FORMAT);
        return dateFormat.format(date);
    }

    public static String formattedHourMinute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(HOUR_MINUTE_FORMAT);
        return format.format(date);
    }

    public static String parseHourless(Date date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOURLESS_DATE_FORMAT, Locale.US);
        return simpleDateFormat.format(date);
    }

    public static Date parseHourless(String date) throws ParseException {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOURLESS_DATE_FORMAT, Locale.US);
        return simpleDateFormat.parse(date);
    }

    public static Date parseDateISO(String dateToTransform) {
        Date date = null;
        SimpleDateFormat dateformat = new SimpleDateFormat(DATE_FORMAT_ISO, Locale.US);
        try {
            date = dateformat.parse(dateToTransform);
        } catch (ParseException e) {
            //FIXME Remove when Service data is fixed
            try {
                date = dateformat.parse("20170702T000000");
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return date;
    }
}
