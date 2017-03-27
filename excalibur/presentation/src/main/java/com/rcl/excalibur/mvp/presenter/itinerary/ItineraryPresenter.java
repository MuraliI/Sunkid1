package com.rcl.excalibur.mvp.presenter.itinerary;

import android.app.Activity;

import com.rcl.excalibur.R;
import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModelMapper;
import com.rcl.excalibur.model.itinerary.ItinerarySeparatorModel;
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

    private int scrollPosition = 0;
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