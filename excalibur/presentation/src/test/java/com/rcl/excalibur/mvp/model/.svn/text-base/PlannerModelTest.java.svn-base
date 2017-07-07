package com.rcl.excalibur.mvp.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlannerModelTest {

    private PlannerModel model;

    @Before
    public void setUp() throws Exception {
        model = new PlannerModel();
    }

    @Test
    public void tesTimeFormatReturnValueMorning() throws Exception {
        String timeFormat = model.getTimeFormat("1030");
        assertEquals(timeFormat, "10:30 AM");
    }

    @Test
    public void tesTimeFormatReturnValueMidday() throws Exception {
        String timeFormat = model.getTimeFormat("1230");
        assertEquals(timeFormat, "12:30 PM");
    }

    @Test
    public void tesTimeFormatReturnValueAfternoon() throws Exception {
        String timeFormat = model.getTimeFormat("1430");
        assertEquals(timeFormat, "2:30 PM");
    }

    @Test
    public void tesTimeFormatReturnValueMidnight() throws Exception {
        String timeFormat = model.getTimeFormat("2430");
        assertEquals(timeFormat, "12:30 AM");
    }

    @Test
    public void tesTimeFormatReturnEmpty() throws Exception {
        String timeFormat = model.getTimeFormat("0");
        assertEquals(timeFormat, "");
    }
}