package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.CalendarSeparatorModel;
import com.rcl.excalibur.model.itinerary.ProductModel;
import com.rcl.excalibur.model.itinerary.ProductModelMapper;
import com.rcl.excalibur.mvp.view.ItineraryView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class ItineraryPresenter implements BasePresenter {

    @Inject
    ItineraryService itineraryService;

    private ItineraryView view;
    private int type;

    public ItineraryPresenter(int type, ItineraryView view) {
        this.view = view;
        this.type = type;
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
        itineraryService.myItinerary(new ItineraryServiceObserver(this));
    }

    private class ItineraryServiceObserver extends DefaultPresentObserver<List<ItineraryEvent>, ItineraryPresenter> {

        public ItineraryServiceObserver(ItineraryPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(List<ItineraryEvent> value) {

            ProductModelMapper productModelMapper = new ProductModelMapper(view.getActivity().getResources());
            List<ProductModel> productModels = productModelMapper.transform(value);
            Collections.sort(productModels);
            List<RecyclerViewType> viewTypeList = groupEventByDate(productModels);
            Timber.e("Grouping", viewTypeList.size());
        }
    }

    private List<RecyclerViewType> groupEventByDate(List<ProductModel> products) {

        List<RecyclerViewType> viewTypeList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            ProductModel productModel = products.get(i);

            boolean addCalendar = false;

            if (i == 0) {
                addCalendar = true;
            } else if (i > 0) {
                ProductModel prevProduct = products.get(i - 1);
                if (productModel.hourIsDifferent(prevProduct)) {
                    addCalendar = true;
                }
            }

            if (addCalendar) {
                CalendarSeparatorModel calendarSeparatorModel = new CalendarSeparatorModel();
                calendarSeparatorModel.setDate(DateUtils.getDateHour(productModel.getStartDate(), view.getActivity().getResources()));
                viewTypeList.add(calendarSeparatorModel);
            }
            viewTypeList.add(productModel);

        }


        return viewTypeList;
    }

}
