package com.rcl.excalibur.mvp.presenter.itinerary;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.ExpandableHeaderViewType;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.CalendarSeparatorModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModelMapper;
import com.rcl.excalibur.model.itinerary.SeparatorModel;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.itinerary.ItineraryProductModel.STATE_MORNING;

public class ItineraryPresenter {
    private ItineraryService itineraryService;
    private ItineraryView view;
    private ItineraryServiceObserver serviceObserver;
    private ItineraryProductModelMapper mapper;
    private RecyclerViewType scrollToElement = null;

    private boolean onGoingIsAdded = false;

    private boolean morningAdded = false;
    private boolean afternoonAdded = false;
    private boolean eveningAdded = false;
    private boolean lateNightAdded = false;

    public ItineraryPresenter(ItineraryView view,
                              ItineraryService itineraryService,
                              ItineraryProductModelMapper modelMapper) {
        this.view = view;
        this.itineraryService = itineraryService;
        this.mapper = modelMapper;
        this.serviceObserver = new ItineraryServiceObserver(this);
    }

    public void init() {
        view.init();
        //view.setGreetingText(new GreetingViewType());
        refreshItinerary();
    }

    public void refreshItinerary() {
        view.setIsLoadingData(true);
        onGoingIsAdded = false;

        morningAdded = false;
        afternoonAdded = false;
        eveningAdded = false;
        lateNightAdded = false;

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
                /*case STATE_ON_GOING:

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

                    break;*/

                case STATE_MORNING:
                    if (!morningAdded) {
                        viewTypeList.add(createExpandableHeader(R.string.title_morning));
                        morningAdded = true;
                    }
                    break;
                case STATE_AFTERNOON:
                    if (!afternoonAdded) {
                        viewTypeList.add(createExpandableHeader(R.string.title_afternoon));
                        afternoonAdded = true;
                    }
                    break;
                case STATE_EVENING:
                    if (!eveningAdded) {
                        viewTypeList.add(createExpandableHeader(R.string.title_evening));
                        eveningAdded = true;
                    }
                    break;
                case STATE_LATE_NIGHT:
                    if (!lateNightAdded) {
                        viewTypeList.add(createExpandableHeader(R.string.title_late_night));
                        lateNightAdded = true;
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

    private ExpandableHeaderViewType createExpandableHeader(int textRes) {
        BaseActivity activity = view.getActivity();
        if (activity == null) {
            return null;
        }

        ExpandableHeaderViewType expandableHeaderViewType = new ExpandableHeaderViewType();
        expandableHeaderViewType.setLabel(view.getActivity().getString(textRes));
        return expandableHeaderViewType;
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
