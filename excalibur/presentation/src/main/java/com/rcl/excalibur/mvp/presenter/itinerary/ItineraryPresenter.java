package com.rcl.excalibur.mvp.presenter.itinerary;

import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.data.mapper.BaseDataMapper;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.SeparatorModel;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.presenter.FragmentPresenter;
import com.rcl.excalibur.mvp.view.itinerary.ItineraryView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItineraryPresenter implements FragmentPresenter {
    private ItineraryService itineraryService;
    private ItineraryView view;
    private ItineraryServiceObserver serviceObserver;
    private BaseDataMapper<ItineraryProductModel, ItineraryEvent> mapper;
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
            viewTypeList.add(new SeparatorModel());
            viewTypeList.add(productModel);
        }
        return viewTypeList;
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
