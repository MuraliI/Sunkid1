package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomePresenter {

    private TriptychHomeView view;
    /*private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;*/


    public TriptychHomePresenter(TriptychHomeView view/*,
                                 GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                                 GetSaildDateDbUseCase getSaildDateDbUseCase*/) {
        this.view = view;
        /*this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;*/
    }

    public void init() {
        view.init();
        /*int selectedDay = Integer.valueOf(getSailingPreferenceUseCase.getDay());

        SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
        SailingInfoModel sailingInfoModel = new SailingInformationModelDataMapper().transform(sailDateInfo);
        SailDateItinerary itinerary = sailingInfoModel.getItinerary();
        List<SailDateEvent> events = itinerary.getEvents();
        view.addDayInformationValues(events, selectedDay);*/
    }

}
