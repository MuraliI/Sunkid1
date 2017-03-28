package com.rcl.excalibur.mvp.presenter.itinerary;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.ItinerarySeparatorModel;
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
    private int scrollPosition = 0;
    private boolean onGoingIsAdded = false;

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
        itineraryService.myItinerary(serviceObserver);
    }

    private void refreshPositioning() {
        view.scrollToPosition(scrollPosition);
    }

    private List<RecyclerViewType> groupEventByDate(List<ItineraryProductModel> products) {

        List<RecyclerViewType> viewTypeList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            ItineraryProductModel productModel = products.get(i);

            switch (productModel.getState()) {
                case STATE_ON_GOING:

                    if (!onGoingIsAdded) {
                        addOnGoingSeparator(viewTypeList, i);
                    }

                    break;
                case STATE_UP_COMING:

                    ItineraryProductModel prevProduct = i > 0 ? products.get(i - 1) : null;

                    if (prevProduct == null
                            || (prevProduct.getState() != STATE_UP_COMING || productModel.hourIsDifferent(prevProduct))) {
                        addCalendarSeparator(viewTypeList, productModel, i);
                    }

                    break;
                default:
                    break;
            }

            viewTypeList.add(productModel);
        }

        return viewTypeList;
    }

    private void addCalendarSeparator(List<RecyclerViewType> list, ItineraryProductModel productModel, int position) {

        if (view.getActivity() != null) {
            String separatorLabel = DateUtils.getDateHour(productModel.getStartDate().getTime(),
                    view.getActivity().getResources());
            separatorLabel = separatorLabel.toLowerCase();

            if (!onGoingIsAdded) {
                scrollPosition = position + 1;
            }
            addSeparator(list, separatorLabel);
        }
    }

    private void addOnGoingSeparator(List<RecyclerViewType> list, int position) {
        if (view.getActivity() != null) {
            scrollPosition = position + 1;
            onGoingIsAdded = true;
            String label = view.getActivity().getString(R.string.itinerary_separator_title_on_going);
            addSeparator(list, label);
        }
    }

    private void addSeparator(List<RecyclerViewType> list, String label) {
        ItinerarySeparatorModel itinerarySeparatorModel = new ItinerarySeparatorModel();
        itinerarySeparatorModel.setLabel(label);
        list.add(itinerarySeparatorModel);
    }

    @Override
    public ItineraryView getView() {
        return view;
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
