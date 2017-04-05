package com.rcl.excalibur.mvp.presenter.itinerary;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.CalendarSeparatorModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.SeparatorModel;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.presenter.FragmentPresenter;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_ON_GOING;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_UP_COMING;

public class ItineraryPresenter implements FragmentPresenter {
    private ItineraryService itineraryService;
    private ItineraryView view;
    private ItineraryServiceObserver serviceObserver;
    private BaseDataMapper<ItineraryProductModel, ItineraryEvent> mapper;
    private boolean onGoingIsAdded = false;
    private RecyclerViewType scrollToElement = null;

    public ItineraryPresenter(ItineraryView view,
                              ItineraryService itineraryService,
                              BaseDataMapper<ItineraryProductModel, ItineraryEvent> modelMapper) {
        this.view = view;
        this.itineraryService = itineraryService;
        this.mapper = modelMapper;
        this.serviceObserver = new ItineraryServiceObserver(this);
    }

    public void init() {
        view.init();
        view.setGreetingText(new GreetingViewType());
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
            RecyclerViewType separator = i != 0 ? new SeparatorModel() : null;
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

    @Override
    public ItineraryView getView() {
        return view;
    }

    private final class ItineraryServiceObserver extends DefaultPresentObserver<List<ItineraryEvent>, ItineraryPresenter> {

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
