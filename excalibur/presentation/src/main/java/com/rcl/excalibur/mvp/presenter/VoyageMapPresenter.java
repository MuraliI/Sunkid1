package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Display;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.view.VoyageMapView;

import java.util.List;

import static com.rcl.excalibur.model.PortModel.PORT_TYPE_EMBARK;
import static com.rcl.excalibur.mvp.presenter.TriptychHomePresenter.PORT_TYPE_CRUISING;
import static com.rcl.excalibur.mvp.presenter.TriptychHomePresenter.PORT_TYPE_DEBARK;
import static com.rcl.excalibur.mvp.presenter.TriptychHomePresenter.PORT_TYPE_DOCKED;

public class VoyageMapPresenter {
    private VoyageMapView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;

    public VoyageMapPresenter(VoyageMapView view,
                              GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                              GetSaildDateDbUseCase getSaildDateDbUseCase,
                              SailingInformationModelDataMapper sailingInformationModelDataMapper) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.sailingInformationModelDataMapper = sailingInformationModelDataMapper;
    }

    public void init() {
        view.init(getScreenWidth());
        initVoyageMapImage();
    }

    public int getScreenWidth() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public void initVoyageMapImage() {
        view.setCruiseCoordinate(796, 826);
        view.setCruiseAngle(20);
        view.initVoyageMapImage(R.drawable.voyage_land);
    }

    public void onResume() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        String day = getSailingPreferenceUseCase.getDay();

        if (TextUtils.isEmpty(day)) {
            day = activity.getResources().getString(R.string.day_1);
        } else {
            day = activity.getResources().getString(R.string.day_title) + day;
        }
        //view.setHeader(day);
        getShipLocationInfo(day);
    }

    public void getShipLocationInfo(String day) {
        if (TextUtils.isEmpty(day)) {
            return;
        }
        day = getSailingPreferenceUseCase.getDay();
        int selectedDay = Integer.valueOf(day == null ? PlannerPresenter.DAY_DEFAULT_VALUE : day);

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
