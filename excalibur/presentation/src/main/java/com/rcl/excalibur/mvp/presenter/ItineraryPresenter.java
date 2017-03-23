package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.itinerary.GreetingViewType;
import com.rcl.excalibur.domain.ItineraryEvent;
import com.rcl.excalibur.domain.service.ItineraryService;
import com.rcl.excalibur.model.itinerary.ItineraryProductModel;
import com.rcl.excalibur.model.itinerary.ItineraryProductModelMapper;
import com.rcl.excalibur.model.itinerary.ItinerarySeparatorModel;
import com.rcl.excalibur.model.itinerary.ProductStateEnum;
import com.rcl.excalibur.mvp.view.ItineraryView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ItineraryPresenter implements BasePresenter {

    @Inject
    ItineraryService itineraryService;

    private ItineraryView view;
    private int scrollPosition = 0;

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

    public void refreshPositioning() {
        /*TODO: List should scrool to ON_GOING or UP_COMMING event*/
//        view.scrollToPosition(scrollPosition);
    }

    private class ItineraryServiceObserver extends DefaultPresentObserver<List<ItineraryEvent>, ItineraryPresenter> {

        ItineraryServiceObserver(ItineraryPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(List<ItineraryEvent> value) {

            if (view.getActivity() != null) {
                ItineraryProductModelMapper mapper = new ItineraryProductModelMapper(view.getActivity().getResources());

                List<ItineraryProductModel> productModels = mapper.transform(value);
                Collections.sort(productModels);
                List<RecyclerViewType> viewTypeList = groupEventByDate(productModels);

                view.addPlans(viewTypeList);
                refreshPositioning();
            }
        }
    }

    private List<RecyclerViewType> groupEventByDate(List<ItineraryProductModel> products) {

        List<RecyclerViewType> viewTypeList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            ItineraryProductModel productModel = products.get(i);

            switch (productModel.getState()) {
                case ON_GOING:

                    String separatorOnGoing = "On Going";

                    if (i == 0) {
                        addOnGoingSeparator(viewTypeList, separatorOnGoing, i);
                    } else if (i > 0) {
                        ItineraryProductModel prevProduct = products.get(i - 1);
                        if (prevProduct.getState() != ProductStateEnum.ON_GOING) {
                            addOnGoingSeparator(viewTypeList, separatorOnGoing, i);
                        }
                    }

                    break;
                case UP_COMING:

                    String separatorLabel = DateUtils.getDateHour(productModel.getStartDate(),
                            view.getActivity().getResources());

                    if (i == 0) {
                        addCalendarSeparator(viewTypeList, separatorLabel, i);
                    } else if (i > 0) {
                        ItineraryProductModel prevProduct = products.get(i - 1);
                        if (prevProduct.getState() != ProductStateEnum.UP_COMING
                                || productModel.hourIsDifferent(prevProduct)) {
                            addCalendarSeparator(viewTypeList, separatorLabel, i);
                        }
                    }

                    break;
                case PAST:
                    break;
                default:
                    break;
            }

            viewTypeList.add(productModel);
        }


        return viewTypeList;
    }

    private void addCalendarSeparator(List<RecyclerViewType> list, String label, int position) {
        if (scrollPosition == 0) {
            scrollPosition = position + 1;
        }
        addSeparator(list, label);
    }

    private void addOnGoingSeparator(List<RecyclerViewType> list, String label, int position) {
        scrollPosition = position + 1;
        addSeparator(list, label);
    }

    private void addSeparator(List<RecyclerViewType> list, String label) {
        ItinerarySeparatorModel itinerarySeparatorModel = new ItinerarySeparatorModel();
        itinerarySeparatorModel.setLabel(label);
        list.add(itinerarySeparatorModel);
    }

}
