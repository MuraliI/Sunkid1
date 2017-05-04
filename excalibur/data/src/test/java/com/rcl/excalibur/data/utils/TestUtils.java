package com.rcl.excalibur.data.utils;


import java.text.ParseException;
import java.util.Date;

public final class TestUtils {

    public static final String OFFERING_ID = "1";
    public static final String OFFERING_START_DATE = "20170501";
    public static final String OFFERING_START_TIME = "0600";
    public static final String PRODUCT_ID = "2";
    public static final String PRICE_CURRENCY = "USD";
    public static final float PRICE_ADULT = 20.0F;
    private TestUtils() {

    }

    public static Date getOfferingSampleDate() throws ParseException {
        return DateUtil.parseDateResponse(TestUtils.OFFERING_START_DATE, TestUtils.OFFERING_START_TIME);
    }

}
