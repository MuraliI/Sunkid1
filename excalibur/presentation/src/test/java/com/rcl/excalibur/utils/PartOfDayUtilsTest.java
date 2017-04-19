package com.rcl.excalibur.utils;

import org.junit.Test;

import java.util.GregorianCalendar;

import static com.rcl.excalibur.model.PlannerProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_MORNING;
import static junit.framework.Assert.assertEquals;

public class PartOfDayUtilsTest {

    @Test
    public void shouldReturnMorningState() throws Exception {
        int state = PartOfDayUtils.getPartOfDayState(new GregorianCalendar(2017, 4, 14, 6, 20));
        assertEquals(state, STATE_MORNING);
    }

    @Test
    public void shouldReturnAfternoonState() throws Exception {
        int state = PartOfDayUtils.getPartOfDayState(new GregorianCalendar(2017, 4, 14, 13, 0));
        assertEquals(state, STATE_AFTERNOON);
    }

    @Test
    public void shouldReturnEveningState() throws Exception {
        int state = PartOfDayUtils.getPartOfDayState(new GregorianCalendar(2017, 4, 14, 18, 59));
        assertEquals(state, STATE_EVENING);
    }

    @Test
    public void shouldReturnLateNightState() throws Exception {
        int state = PartOfDayUtils.getPartOfDayState(new GregorianCalendar(2017, 4, 14, 23, 40));
        assertEquals(state, STATE_LATE_NIGHT);
    }

    @Test
    public void shouldGetCurrentHourInMinutes() throws Exception {
        assertEquals(PartOfDayUtils.getCurrentHourInMinutes(new GregorianCalendar(2017, 2, 16, 11, 20)), 680);
    }
}