package com.rcl.excalibur.mvp.presenter;


import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.TriptychPagerAdapter;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.data.utils.DownloadProductsManager;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetMenusUseCase;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.fragments.BaseTripTychFragment;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class TriptychHomePresenter {

    public static final String PORT_TYPE_EMBARK = "EMBARK";
    public static final String PORT_TYPE_DOCKED = "DOCKED";
    public static final String PORT_TYPE_DEBARK = "DEBARK";
    public static final String PORT_TYPE_CRUISING = "CRUISING";

    private TriptychHomeView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private GetMenusUseCase getMenusUseCase;
    private String dayPreferences;

    private GetProductDbUseCase getProductsDbUseCase;
    private GetSaildDateUseCase getSaildDateUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;

    public TriptychHomePresenter(
            TriptychHomeView view,
            GetProductDbUseCase getProductsDbUseCase,
            GetSaildDateUseCase getSaildDateUseCase,
            GetSailingPreferenceUseCase getSailingPreferenceUseCase,
            GetSaildDateDbUseCase getSaildDateDbUseCase,
            SailingInformationModelDataMapper sailingInformationModelDataMapper,
            GetMenusUseCase getMenusUseCase) {
        this.view = view;
        this.getProductsDbUseCase = getProductsDbUseCase;
        this.getSaildDateUseCase = getSaildDateUseCase;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.sailingInformationModelDataMapper = sailingInformationModelDataMapper;
        this.getMenusUseCase = getMenusUseCase;
    }

    public void init() {
        view.init();

        //FIXME refactor to coordinate with manager when all products were downloaded because we can have just the first batch downloaded
        //and the logic will execute the service call completed or the manager could have already finished and we wouldn't know if
        //the observer will get notified.
        List<Product> products = getProductsDbUseCase.getAll();
        if (!CollectionUtils.isEmpty(products)) {
            onServiceCallCompleted(true);
        } else {
            DownloadProductsManager.addObserver(new DefaultObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    onServiceCallCompleted(value);
                    DownloadProductsManager.removeObserver(this);
                }
            });
        }
        //TODO remove this and move it to DownloadManager
        getMenusUseCase.execute(new DisposableObserver<List<Menu>>() {
            @Override
            public void onNext(List<Menu> value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, null);
        getSaildDateUseCase.execute(null);
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

    public Pair<Pair<View, String>, Pair<View, String>> getPlannerSharedElementPairs() {
        return view.getPlannerSharedElements();
    }
}
