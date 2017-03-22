package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.mvp.view.ItineraryView;

public class ItineraryPresenter implements BasePresenter {

    private ItineraryView view;
    private int type;

    public ItineraryPresenter(int type, ItineraryView view) {
        this.view = view;
        this.type = type;
        init();
    }

    private void init() {
        view.init();
        view.setGreetingText(new GreetingViewType());

    }

}
