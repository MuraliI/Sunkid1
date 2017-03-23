package com.rcl.excalibur.data.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateUtil {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmm", Locale.US);

    private DateUtil() {
    }

    @Nullable
    public static Date parseDateResponse(String dateStr, String timeStr) {

        Date date = null;
        String str = dateStr + timeStr;
        try {
            date = DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
