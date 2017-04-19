package com.rcl.excalibur.mvp.presenter;

import android.content.SharedPreferences;
import android.os.Handler;

import com.rcl.excalibur.data.mapper.SailDateInfoDataMapper;
import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfoEvent;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TriptychHomePresenter {
    private static final int DEFAULT_INT = 0;
    private static final String DAY_VALUE = "dia";
    private static final long DELAY = 5000;

    private TriptychHomeView view;
    private SailDateInfoDataMapper mapper;


    public TriptychHomePresenter(TriptychHomeView view, SailDateInfoDataMapper infoDataMapper) {
        this.view = view;
        this.mapper = infoDataMapper;
    }

    public void init() {
        view.init();
        SharedPreferences preferences = view.getActivity().getApplicationContext().getSharedPreferences(DAY_VALUE, MODE_PRIVATE);
        int selectedDay = preferences.getInt(DAY_VALUE, DEFAULT_INT); //TODO: update this DAY_VALUE
        new Handler().postDelayed(() -> {
            SailDateInfoEvent sailDateInfoEvent = mapper.transform(new SailingInfoResponse(), null); //TODO: update this SailingInfoResponse
            SailDateItinerary itinerary = sailDateInfoEvent.getItinerary();
            List<SailDateEvent> events = itinerary.getEvents();
            view.addDayInformationValues(events, selectedDay);
        }, DELAY);
    }

}
