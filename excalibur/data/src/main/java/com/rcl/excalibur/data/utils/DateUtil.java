package com.rcl.excalibur.data.utils;

import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");

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
