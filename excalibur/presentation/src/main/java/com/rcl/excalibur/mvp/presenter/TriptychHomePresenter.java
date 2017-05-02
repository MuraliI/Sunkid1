package com.rcl.excalibur.mvp.presenter;


import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.TriptychPagerAdapter;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetMenusUseCase;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.interactor.GetSubCategoriesUseCase;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.fragments.BaseTripTychFragment;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

import java.util.List;

public class TriptychHomePresenter {

    private static final String PORT_TYPE_EMBARK = "EMBARK";
    private static final String PORT_TYPE_DOCKED = "DOCKED";
    private static final String PORT_TYPE_DEBARK = "DISEMBARK";
    private static final String PORT_TYPE_CRUISING = "CRUISING";

    private TriptychHomeView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private String dayPreferences;

    private GetProductsUseCase getProductsUseCase;
    private GetSubCategoriesUseCase getSubCategoriesUseCase;
    private GetSaildDateUseCase getSaildDateUseCase;
    private GetMenusUseCase getMenusUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;

    public TriptychHomePresenter(
            TriptychHomeView view,
            GetProductsUseCase getProductsUseCase,
            GetSubCategoriesUseCase getSubCategoriesUseCase,
            GetSaildDateUseCase getSaildDateUseCase,
            GetSailingPreferenceUseCase getSailingPreferenceUseCase,
            GetSaildDateDbUseCase getSaildDateDbUseCase,
            GetMenusUseCase getMenusUseCase,
            SailingInformationModelDataMapper sailingInformationModelDataMapper) {
        this.view = view;
        this.getProductsUseCase = getProductsUseCase;
        this.getSubCategoriesUseCase = getSubCategoriesUseCase;
        this.getSaildDateUseCase = getSaildDateUseCase;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.getMenusUseCase = getMenusUseCase;
        this.sailingInformationModelDataMapper = sailingInformationModelDataMapper;
    }

    public void init() {
        view.init();

        getProductsUseCase.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                onServiceCallCompleted(value);
            }
        }, null);

        getSubCategoriesUseCase.execute(null);
        getSaildDateUseCase.execute(null);
        //TODO this will be call on other place
        getMenusUseCase.execute(null);
    }

    public void getShipLocationInfo() {
        dayPreferences = getSailingPreferenceUseCase.getDay();
        int selectedDay = Integer.valueOf(dayPreferences == null ? PlannerPresenter.DAY_DEFAULT_VALUE : dayPreferences);

        SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
        SailingInfoModel sailingInfoModel = sailingInformationModelDataMapper.transform(sailDateInfo);
        ItineraryModel itinerary = sailingInfoModel.getItinerary();
        if (itinerary == null) {
            addShipLocationValue(null, selectedDay);
        } else {
            List<EventModel> events = itinerary.getEvents();
            addShipLocationValue(events, selectedDay);
        }
    }

    public void onServiceCallCompleted(boolean successfully) {
        TriptychPagerAdapter adapter = (TriptychPagerAdapter) view.getViewAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            Fragment adapterFragment = adapter.getFragmentForPosition(i);
            if (adapterFragment instanceof BaseTripTychFragment) {
                ((BaseTripTychFragment) adapterFragment).onServiceCallCompleted(successfully);
            }
        }
    }

    public void addShipLocationValue(@NonNull List<EventModel> events, int day) {
        Activity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        Resources resources = activity.getResources();
        if (events == null) {
            view.setTextShipLocation(resources.getString(R.string.empty_string), resources.getString(R.string.day_number, day));
        } else {
            view.setTextShipLocation(getShipLocation(events, day, resources), resources.getString(R.string.day_number, day));
        }
    }

    public static String getShipLocation(List<EventModel> events, int day, Resources resources) {
        String shipLocation;
        PortModel sailPort = PortModel.getSailPortByDay(events, day);

        String modelPortType = sailPort.getPortType();

        if (PORT_TYPE_EMBARK.equals(modelPortType) || PORT_TYPE_DOCKED.equals(modelPortType) || PORT_TYPE_DEBARK.equals(modelPortType)) {
            shipLocation = sailPort.getPortName();
        } else if (PORT_TYPE_CRUISING.equals(modelPortType)) {
            shipLocation = resources.getString(R.string.port_type_at_sea);
        } else {
            shipLocation = ConstantsUtil.EMPTY;
        }
        return shipLocation;
    }

}
