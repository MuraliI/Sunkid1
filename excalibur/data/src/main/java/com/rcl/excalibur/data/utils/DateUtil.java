package com.rcl.excalibur.data.utils;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    public static final String DATE_FORMAT = "yyyyMMddHHmm";

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
            e.printStackTrace();
        }
        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        return dateFormatter.format(date);
    }
}
