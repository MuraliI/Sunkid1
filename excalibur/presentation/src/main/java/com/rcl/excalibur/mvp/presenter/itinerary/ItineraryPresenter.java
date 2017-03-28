package com.rcl.excalibur.mvp.presenter.itinerary;

import android.app.Activity;

import com.rcl.excalibur.R;
import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.CalendarSeparatorModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModelMapper;
import com.rcl.excalibur.model.itinerary.SeparatorModel;
import com.rcl.excalibur.mvp.presenter.BasePresenter;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_ON_GOING;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_UP_COMING;

public class ItineraryPresenter implements BasePresenter {

    @Inject
    ItineraryService itineraryService;

    private RecyclerViewType scrollToElement = null;
    private boolean onGoingIsAdded = false;

    private ItineraryView view;
    private ItineraryServiceObserver serviceObserver;
    private ItineraryProductModelMapper mapper;

    public ItineraryPresenter(ItineraryView view) {
        this.view = view;
        initInjection();
        init();
    }

    private void initInjection() {
        final Activity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ((RCLApp) activity.getApplication()).getApplicationComponent().inject(this);
    }

    private void init() {
        mapper = new ItineraryProductModelMapper(view.getActivity().getResources());
        view.init();
        view.setGreetingText(new GreetingViewType());

        serviceObserver = new ItineraryServiceObserver(this);
        refreshItinerary();
    }

    public void refreshItinerary() {
        view.setIsLoadingData(true);
        onGoingIsAdded = false;
        scrollToElement = null;
        itineraryService.myItinerary(serviceObserver);
    }

    private void refreshPositioning() {
        if (scrollToElement != null) {
            view.scrollToPosition(scrollToElement);
        }
    }

    private List<RecyclerViewType> groupEventByDate(List<ItineraryProductModel> products) {

        List<RecyclerViewType> viewTypeList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            ItineraryProductModel productModel = products.get(i);
            RecyclerViewType separator = null;
            switch (productModel.getState()) {
                case STATE_ON_GOING:
                    if (!onGoingIsAdded) {
                        separator = createOnGoingSeparator();
                    }
                    break;
                case STATE_UP_COMING:
                    ItineraryProductModel prevProduct = i > 0 ? products.get(i - 1) : null;
                    if (prevProduct == null
                            || (prevProduct.getState() != STATE_UP_COMING || productModel.hourIsDifferent(prevProduct))) {
                        separator = createCalendarSeparator(productModel);
                    }
                    break;
                default:
                    if (i != 0) {
                        separator = new SeparatorModel();
                    }
                    break;
            }

            if (separator != null) {
                viewTypeList.add(separator);
            }
            viewTypeList.add(productModel);
        }

        return viewTypeList;
    }

    private CalendarSeparatorModel createCalendarSeparator(ItineraryProductModel productModel) {
        if (view.getActivity() != null) {
            String separatorLabel = DateUtils.getDateHour(productModel.getStartDate().getTime(),
                    view.getActivity().getResources());
            separatorLabel = separatorLabel.toLowerCase();
            CalendarSeparatorModel separatorModel = createSeparator(separatorLabel);
            if (scrollToElement == null) {
                scrollToElement = separatorModel;
            }
            return separatorModel;
        }
        return null;
    }

    private CalendarSeparatorModel createOnGoingSeparator() {
        if (view.getActivity() != null) {
            onGoingIsAdded = true;
            String label = view.getActivity().getString(R.string.itinerary_separator_title_on_going);
            CalendarSeparatorModel separatorModel = createSeparator(label);
            scrollToElement = separatorModel;
            return separatorModel;
        }
        return null;
    }

    private CalendarSeparatorModel createSeparator(String label) {
        CalendarSeparatorModel calendarSeparatorModel = new CalendarSeparatorModel();
        calendarSeparatorModel.setLabel(label);
        return calendarSeparatorModel;
    }

    private class ItineraryServiceObserver extends DefaultPresentObserver<List<ItineraryEvent>, ItineraryPresenter> {

        private ItineraryServiceObserver(ItineraryPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(List<ItineraryEvent> value) {
            if (view.getActivity() != null) {
                List<ItineraryProductModel> productModels = mapper.transform(value);
                Collections.sort(productModels);
                List<RecyclerViewType> viewTypeList = groupEventByDate(productModels);

                view.setIsLoadingData(false);
                view.addPlans(viewTypeList);
                refreshPositioning();
            }
        }
    }
}
