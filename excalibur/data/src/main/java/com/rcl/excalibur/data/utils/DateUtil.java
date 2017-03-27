package com.rcl.excalibur.data.utils;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    private static final String DATE_FORMAT = "yyyyMMddHHmm";
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private DateUtil() {
    }

    @Nullable
    public static Date parseDateResponse(String dateStr, String timeStr) {

        Date date = null;
        String str = dateStr + timeStr;
        try {
            date = DATE_FORMATTER.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDate(Date date) {
        return DATE_FORMATTER.format(date);
    }
}
