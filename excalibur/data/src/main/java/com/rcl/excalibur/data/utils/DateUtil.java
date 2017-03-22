package com.rcl.excalibur.data.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {
    }

    public static Date parseDateResponse(String dateStr, String timeStr) {

        Date date = new Date();
        String str = dateStr + timeStr;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
