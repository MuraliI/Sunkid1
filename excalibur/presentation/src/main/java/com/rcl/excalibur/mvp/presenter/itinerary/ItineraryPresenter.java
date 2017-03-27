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

    private RecyclerViewType scrollToElement = null;
    private boolean onGoingIsAdded = false;

    private ItineraryView view;
    private ItineraryServiceObserver serviceObserver;

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
        if (scrollToElement != null) {
            view.scrollToPosition(scrollToElement);
        }
    }

    private List<RecyclerViewType> groupEventByDate(List<ItineraryProductModel> products) {

        List<RecyclerViewType> viewTypeList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            ItineraryProductModel productModel = products.get(i);
            ItinerarySeparatorModel separator = i != 0 ? createSeparator("") : null;
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

    private ItinerarySeparatorModel createCalendarSeparator(ItineraryProductModel productModel) {
        if (view.getActivity() != null) {
            String separatorLabel = DateUtils.getDateHour(productModel.getStartDate().getTime(),
                    view.getActivity().getResources());
            separatorLabel = separatorLabel.toLowerCase();
            return createSeparator(separatorLabel);
        }
        return null;
    }

    private ItinerarySeparatorModel createOnGoingSeparator() {
        if (view.getActivity() != null) {
            onGoingIsAdded = true;
            String label = view.getActivity().getString(R.string.itinerary_separator_title_on_going);
            ItinerarySeparatorModel separatorModel = createSeparator(label);
            scrollToElement = separatorModel;
            return separatorModel;
        }
        return null;
    }

    private ItinerarySeparatorModel createSeparator(String label) {
        ItinerarySeparatorModel itinerarySeparatorModel = new ItinerarySeparatorModel();
        itinerarySeparatorModel.setLabel(label);
        return itinerarySeparatorModel;
    }

    private class ItineraryServiceObserver extends DefaultPresentObserver<List<ItineraryEvent>, ItineraryPresenter> {

        private ItineraryServiceObserver(ItineraryPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(List<ItineraryEvent> value) {

            if (view.getActivity() != null) {
                ItineraryProductModelMapper mapper = new ItineraryProductModelMapper(view.getActivity().getResources());

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
