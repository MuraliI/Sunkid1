package com.rcl.excalibur.adapters.viewtype.itinerary;

import android.support.annotation.StringRes;

import com.rcl.excalibur.adapters.base.RecyclerViewConstants;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.utils.GreetingUtils;

import java.util.Calendar;
import java.util.Date;


public class GreetingViewType implements RecyclerViewType {

    @StringRes
    public int getGreeting() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return GreetingUtils.getGreetingByDate(calendar);
    }

    @Override
    public int getViewType() {
        return RecyclerViewConstants.VIEW_TYPE_ITINERARY_GREETINGS;
    }
}
