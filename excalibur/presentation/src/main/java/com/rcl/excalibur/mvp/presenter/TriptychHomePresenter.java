package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.interactor.GetSubCategoriesUseCase;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

import java.util.List;

public class TriptychHomePresenter {

    private static final String DAY_DEFAULT_VALUE = "1";
    private TriptychHomeView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private String dayPreferences;

    private GetProductsUseCase getProductsUseCase;
    private GetSubCategoriesUseCase getSubCategoriesUseCase;
    private GetSaildDateUseCase getSaildDateUseCase;

    public TriptychHomePresenter(
            TriptychHomeView view,
            GetProductsUseCase getProductsUseCase,
            GetSubCategoriesUseCase getSubCategoriesUseCase,
            GetSaildDateUseCase getSaildDateUseCase,
            GetSailingPreferenceUseCase getSailingPreferenceUseCase,
            GetSaildDateDbUseCase getSaildDateDbUseCase) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
        this.getSubCategoriesUseCase = getSubCategoriesUseCase;
        this.getSaildDateUseCase = getSaildDateUseCase;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
    }

    public void init() {
        view.init();

        getProductsUseCase.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                view.onServiceCallCompleted(value);
            }
        }, null);

        getSubCategoriesUseCase.execute(null);
        getSaildDateUseCase.execute(null);
    }

    public void getShipLocationInfo() {
        dayPreferences = getSailingPreferenceUseCase.getDay();
        int selectedDay = Integer.valueOf(dayPreferences == null ? DAY_DEFAULT_VALUE : dayPreferences);

        SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
        SailingInfoModel sailingInfoModel = new SailingInformationModelDataMapper().transform(sailDateInfo);
        ItineraryModel itinerary = sailingInfoModel.getItinerary();
        if (itinerary == null) {
            view.addShipLocationValue(null, selectedDay);
        } else {
            List<EventModel> events = itinerary.getEvents();
            view.addShipLocationValue(events, selectedDay);
        }
    }

}
