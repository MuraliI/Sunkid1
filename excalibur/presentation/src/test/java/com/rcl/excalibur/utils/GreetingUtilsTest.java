package com.rcl.excalibur.utils;

import com.rcl.excalibur.R;

import org.junit.Assert;
import org.junit.Test;

import java.util.GregorianCalendar;

public class GreetingUtilsTest {

    @Test
    public void testIfGreetingIsGoodMorning() throws Exception {
        int greeting = GreetingUtils.getGreetingByDate(new GregorianCalendar(2017, 2, 16, 11, 20));
        Assert.assertEquals(greeting, R.string.title_good_morning);
    }

    @Test
    public void testIfGreetingIsGoodAfternoon() throws Exception {
        int greeting = GreetingUtils.getGreetingByDate(new GregorianCalendar(2017, 2, 16, 17, 59));
        Assert.assertEquals(greeting, R.string.title_good_afternoon);
    }

    @Test
    public void testIfGreetingIsGoodEvening() throws Exception {
        int greeting = GreetingUtils.getGreetingByDate(new GregorianCalendar(2017, 2, 16, 18, 20));
        Assert.assertEquals(greeting, R.string.title_good_evening);
    }

    @Test
    public void testIfGreetingIsGoodNight() throws Exception {
        int greeting = GreetingUtils.getGreetingByDate(new GregorianCalendar(2017, 2, 16, 22, 20));
        Assert.assertEquals(greeting, R.string.title_good_night);
    }

    @Test
    public void testIfGetCurrentHourInMinutesWorks() throws Exception {

        Assert.assertEquals(GreetingUtils.getCurrentHourInMinutes(new GregorianCalendar(2017, 2, 16, 11, 20)), 680);
    }

}