package com.rcl.excalibur.mvp.presenter;


import android.content.res.Resources;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.mvp.view.DayPickerView;
import com.rcl.excalibur.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class DayPickerPresenter {

    private DayPickerView view;

    public DayPickerPresenter(DayPickerView view) {
        this.view = view;
    }

    public void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        view.setAdapterObserver(new AdapterObserver(this));
        view.init();
        view.setFotterDate(mockFooterDate(activity.getResources()));
        view.setHeader("Some description", "Day 1");
        ArrayList<EventModel> events = mockEventList(activity.getResources());
        showCollectionInView(events);
    }

    private void showCollectionInView(List<EventModel> events) {
        if (events == null || events.size() == 0) {
            Toast.makeText(view.getActivity(), R.string.no_items_to_show, Toast.LENGTH_LONG).show();
        } else {
            view.addAll(events);
        }
    }

    public class AdapterObserver extends DefaultPresentObserver<EventModel, DayPickerPresenter> {

        AdapterObserver(DayPickerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(EventModel value) {
            Timber.i("Adapter clicked");
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

    public ArrayList<EventModel> mockEventList(Resources resources) {

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

        return events;
    }

}
