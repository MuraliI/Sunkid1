package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.ProductModelMapper;
import com.rcl.excalibur.mvp.view.ItineraryView;

import java.util.List;

import javax.inject.Inject;

public class ItineraryPresenter implements BasePresenter {

    @Inject ItineraryService itineraryService;

    private ItineraryView view;

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
        itineraryService.myItinerary(new ItineraryServiceObserver(this));
    }

    private class ItineraryServiceObserver extends DefaultPresentObserver<List<ItineraryEvent>, ItineraryPresenter> {

        ItineraryServiceObserver(ItineraryPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(List<ItineraryEvent> value) {
            if (view.getActivity() != null) {
                ProductModelMapper mapper = new ProductModelMapper(view.getActivity().getResources());
                view.addPlans(mapper.transform(value));
            }
        }
    }

}
