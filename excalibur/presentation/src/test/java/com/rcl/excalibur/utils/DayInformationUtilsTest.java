package com.rcl.excalibur.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayInformationUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAppendValues_returnValue() throws Exception {
        String appendvalues = DayInformationUtils.appendValues("11", ":", "35");
        assertEquals(appendvalues, "11:35");
    }

    @Test
    public void testAppendValues_returnEmpty() throws Exception {
        String appendvalues = DayInformationUtils.appendValues("", ":", "35");
        assertEquals(appendvalues, "");
    }

    @Test
    public void tesTimeFormat_returnValueMorning() throws Exception {
        String timeFormat = DayInformationUtils.getTimeFormat(103000);
        assertEquals(timeFormat, "10:30 AM");
    }

    @Test
    public void tesTimeFormat_returnValueMidday() throws Exception {
        String timeFormat = DayInformationUtils.getTimeFormat(123000);
        assertEquals(timeFormat, "12:30 PM");
    }

    @Test
    public void tesTimeFormat_returnValueAfternoon() throws Exception {
        String timeFormat = DayInformationUtils.getTimeFormat(143000);
        assertEquals(timeFormat, "2:30 PM");
    }

    @Test
    public void tesTimeFormat_returnValueMidnight() throws Exception {
        String timeFormat = DayInformationUtils.getTimeFormat(243000);
        assertEquals(timeFormat, "12:30 AM");
    }

    @Test
    public void tesTimeFormat_returnEmpty() throws Exception {
        String timeFormat = DayInformationUtils.getTimeFormat(0);
        assertEquals(timeFormat, "");
    }
}