package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.ItineraryView;
import com.rcl.excalibur.utils.GreetingUtils;

import java.util.GregorianCalendar;

public class ItineraryPresenter implements BasePresenter {

    private ItineraryView view;
    private int type;

    public ItineraryPresenter(int type, ItineraryView view) {
        this.view = view;
        this.type = type;

    }

    public void onResume() {
        getGreeting();
    }

    public void getGreeting() {
        view.setGreetingText(GreetingUtils.getGreetingByDate(new GregorianCalendar()));
    }

}
