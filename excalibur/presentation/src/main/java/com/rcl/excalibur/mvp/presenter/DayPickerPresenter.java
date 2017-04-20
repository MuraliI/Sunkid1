package com.rcl.excalibur.mvp.presenter;


import android.content.res.Resources;
import android.text.TextUtils;
import android.widget.Toast;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.view.DayPickerView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class DayPickerPresenter {

    private DayPickerView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;

    public DayPickerPresenter(DayPickerView view,
                              GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                              GetSaildDateDbUseCase getSaildDateDbUseCase) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
    }

    public void init() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
        SailingInfoModel sailingInfoModel = new SailingInformationModelDataMapper().transform(sailDateInfo);
        if (sailingInfoModel == null || sailingInfoModel.getItinerary() == null) {
            return;
        }
        ItineraryModel itinerary = sailingInfoModel.getItinerary();
        view.setAdapterObserver(new AdapterObserver(this));
        view.init(itinerary.getIndexCurrentDay());
        setHeader(itinerary);
        setFooter(itinerary, activity.getResources());
        showCollectionInView(itinerary);
    }

    public void setFooter(ItineraryModel itinerary, Resources resources) {
        if (itinerary == null) {
            return;
        }
        if (CollectionUtils.isEmpty(itinerary.getEvents())) {
            return;
        }
        List<EventModel> events = itinerary.getEvents();
        int eventsize = events.size();
        String starDay = events.get(0).getPort().getArrivalDate();
        String endDay = events.get(eventsize - 1).getPort().getArrivalDate();
        if (!TextUtils.isEmpty(starDay) && !TextUtils.isEmpty(endDay)) {
            view.setFooterDate(DateUtils.getFormatedDates(starDay, endDay, resources));
        }

    }

    public void setHeader(ItineraryModel itinerary) {
        if (itinerary == null) {
            return;
        }
        String day = getDay(itinerary);
        String description = itinerary.getDescription();
        if (!TextUtils.isEmpty(description) && !TextUtils.isEmpty(day)) {
            view.setHeader(description, day);
        }
    }

    private void showCollectionInView(ItineraryModel itinerary) {
        if (itinerary == null) {
            return;
        }
        if (CollectionUtils.isEmpty(itinerary.getEvents())) {
            Toast.makeText(view.getActivity(), R.string.no_items_to_show, Toast.LENGTH_LONG).show();
        } else {
            view.addAll(itinerary.getEvents());
        }
    }

    private String getDay(ItineraryModel itineraryModel) {
        String day = "";
        List<EventModel> events = itineraryModel.getEvents();
        EventModel firstEvent = events.get(0);

        String preferenceDay = getSailingPreferenceUseCase.getDay();
        if (!TextUtils.isEmpty(preferenceDay)) {
            day = preferenceDay;
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
