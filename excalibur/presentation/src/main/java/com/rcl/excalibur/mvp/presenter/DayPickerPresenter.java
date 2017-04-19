package com.rcl.excalibur.mvp.presenter;


import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.mvp.view.DayPickerView;
import com.rcl.excalibur.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DayPickerPresenter {

    private DayPickerView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;

    public DayPickerPresenter(DayPickerView view, GetSailingPreferenceUseCase getSailingPreferenceUseCase) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
    }

    public void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        ItineraryModel itineraryModel = mockItineraryModel(activity.getResources());
        List<EventModel> events = itineraryModel.getEvents();
        view.setFotterDate(mockFooterDate(activity.getResources()));
        view.setHeader(itineraryModel.getDescription(), getDay(itineraryModel));
        showCollectionInView(events);
    }

    private void showCollectionInView(List<EventModel> events) {
        if (events == null || events.size() == 0) {
            Toast.makeText(view.getActivity(), R.string.no_items_to_show, Toast.LENGTH_LONG).show();
        } else {
            view.addAll(events);
        }
    }

    private String getDay(ItineraryModel itineraryModel) {
        String day = "";
        List<EventModel> events = itineraryModel.getEvents();
        EventModel firstEvent = events.get(0);

        if (!TextUtils.isEmpty(getSailingPreferenceUseCase.getDay())) {
            day = getSailingPreferenceUseCase.getDay();
        } else {
            if (itineraryModel.getIndexCurrentDay() == -1) {
                if (!TextUtils.isEmpty(firstEvent.getDay())) {
                    day = firstEvent.getDay();
                }
            } else {
                EventModel todayEvent = events.get(itineraryModel.getIndexCurrentDay());
                if (todayEvent != null && TextUtils.isEmpty(todayEvent.getDay())) {
                    day = todayEvent.getDay();
                }
            }
        }
        return day;
    }

    public class AdapterObserver extends DefaultPresentObserver<EventModel, DayPickerPresenter> {

        AdapterObserver(DayPickerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(EventModel value) {
            getSailingPreferenceUseCase.putDay(value.getDay());
        }
    }

    public String mockFooterDate(Resources resources) {

        String mockDateStart = "03/18/2017";
        String mockDateEnd = "04/28/2017";
        String date = "";
        try {
            date = DateUtils.getFormatedDates(mockDateStart, mockDateEnd, resources);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public ItineraryModel mockItineraryModel(Resources resources) {

        ArrayList<EventModel> events = new ArrayList<>();
        EventModel event1 = new EventModel();
        PortModel port1 = new PortModel();
        port1.setPortName("Fort Lauderdale");
        port1.setPortType(PortModel.PORT_TYPE_EMBARK);
        event1.setDay("Day 1");
        event1.setPort(port1);

        EventModel event2 = new EventModel();
        PortModel port2 = new PortModel();
        port2.setPortName("At Sea");
        event2.setDay(resources.getString(R.string.today_day_title));
        port2.setPortType(PortModel.PORT_TYPE_CRUISING);
        event2.setPort(port2);

        events.add(event1);
        events.add(event2);

        ItineraryModel itineraryModel = new ItineraryModel();
        itineraryModel.setDescription("Description");
        itineraryModel.setEvents(events);
        itineraryModel.setIndexCurrentDay(1);

        return itineraryModel;
    }

}
