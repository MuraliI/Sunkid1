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
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.view.DayPickerView;
import com.rcl.excalibur.utils.DateUtils;

import java.text.ParseException;
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
            Toast.makeText(activity, R.string.error_message_no_information_yet, Toast.LENGTH_LONG).show();
            return;
        }
        ItineraryModel itinerary = sailingInfoModel.getItinerary();
        view.setAdapterObserver(new AdapterObserver(this));
        String day = getSailingPreferenceUseCase.getDay();
        day = getDay(itinerary, day);
        int selectedDayPosition = day != null ? Integer.parseInt(day) - 1 : -1;
        view.init(itinerary.getIndexCurrentDay(), selectedDayPosition);
        setHeader(itinerary, sailingInfoModel.getShipCode(), activity.getResources(), day);
        setFooter(itinerary, activity.getResources());
        showCollectionInView(itinerary);
    }

    public void setFooter(ItineraryModel itinerary, Resources resources) {
        if (CollectionUtils.isEmpty(itinerary.getEvents())) {
            return;
        }
        List<EventModel> events = itinerary.getEvents();
        int eventsize = events.size();
        String startDay = events.get(0).getPort().getArrivalDate();
        String endDay = events.get(eventsize - 1).getPort().getArrivalDate();
        if (startDay != null && endDay != null) {
            try {
                view.setFooterDate(DateUtils.getFormatedDates(startDay, endDay, resources));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void setHeader(ItineraryModel itinerary, String shipCode, Resources resources, String calculatedDay) {
        StringBuilder formatedDay = new StringBuilder();
        formatedDay.append(resources.getString(R.string.day_title));
        formatedDay.append(calculatedDay);
        String day = formatedDay.toString();
        String description = itinerary.getDescription();
//TODO the endpoint is not developed yet and if read from the json file will crash the test
        String shipName = resources.getString(R.string.hardcoded_day_picker_ship_name);
        if (!TextUtils.isEmpty(description) && !TextUtils.isEmpty(day)) {
            view.setHeader(description, day, shipName);
        }
    }

    private void showCollectionInView(ItineraryModel itinerary) {
        if (CollectionUtils.isEmpty(itinerary.getEvents())) {
            Toast.makeText(view.getActivity(), R.string.no_items_to_show, Toast.LENGTH_LONG).show();
        } else {
            view.addAll(itinerary.getEvents());
        }
    }

    private String getDay(ItineraryModel itineraryModel, String day) {
        if (CollectionUtils.isEmpty(itineraryModel.getEvents())) {
            return "";
        }
        List<EventModel> events = itineraryModel.getEvents();
        EventModel firstEvent = events.get(0);
        StringBuilder calculatedDay = new StringBuilder();
        if (!TextUtils.isEmpty(day)) {
            calculatedDay.append(day);
        } else {
            if (itineraryModel.getIndexCurrentDay() == -1) {
                if (!TextUtils.isEmpty(firstEvent.getDay())) {
                    calculatedDay.append(firstEvent.getDay());
                }
            } else {
                EventModel todayEvent = events.get(itineraryModel.getIndexCurrentDay());
                if (todayEvent != null && !TextUtils.isEmpty(todayEvent.getDay())) {
                    calculatedDay.append(todayEvent.getDay());
                } else {
                    calculatedDay.append("");
                }
            }
        }
        return calculatedDay.toString();
    }

    public class AdapterObserver extends DefaultPresentObserver<EventModel, DayPickerPresenter> {

        AdapterObserver(DayPickerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(EventModel value) {
            getSailingPreferenceUseCase.putDay(value.getDay());
            view.notifyDataChange();
        }
    }
}
