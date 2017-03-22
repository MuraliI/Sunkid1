package com.rcl.excalibur.data.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;

public class DateUtilTest {

    String date1;
    String time1;

    String date2;
    String time2;

    @Before
    public void setUp() {
        // date format yyyyMMdd
        // time format HHmm
        date1 = "20170326";
        time1 = "1700";

        date2 = ""; //invalid date
        time2 = "";

    }

    @Test
    public void parseDateResponse() throws Exception {

        Date dateObj1 = DateUtil.parseDateResponse(date1, time1);
        Date dateObj2 = DateUtil.parseDateResponse(date2, time2);

        assertNotNull(dateObj1);
        assertEquals(DateUtil.DATE_FORMAT.format(dateObj1), date1+time1);

        assertNull(dateObj2);
    }

}

