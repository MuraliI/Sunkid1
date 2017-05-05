package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.Display;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
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

public class VoyageMapPresenter implements SubsamplingScaleImageView.OnAnimationEventListener,
        SubsamplingScaleImageView.OnImageEventListener {
    private static final int CENTER_OFFSET = 130;
    private static final int IMAGE_HEIGHT = 1720;
    private static final int IMAGE_WIDTH = 2522;

    private VoyageMapView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;
    private String day;

    public VoyageMapPresenter(VoyageMapView view,
                              GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                              GetSaildDateDbUseCase getSaildDateDbUseCase,
                              SailingInformationModelDataMapper sailingInformationModelDataMapper) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.sailingInformationModelDataMapper = sailingInformationModelDataMapper;
    }

    public void initMap() {
        initVoyageMapImage();
    }

    public void initTab() {
        view.init(getScreenWidth());
    }

    private int getScreenWidth() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    private void initVoyageMapImage() {
        view.hideShip();
        view.initVoyageMapImage(R.drawable.caribbean_map_2_1, this);
        day = getSailingPreferenceUseCase.getDay() == null ? PlannerPresenter.DAY_DEFAULT_VALUE : getSailingPreferenceUseCase.getDay();
        view.setCruiseCoordinate(getMockCoordinate(day.charAt(0), true));
        view.setScaleAndCenter(getMockCoordinate(day.charAt(0), false));
    }

    public void onResume() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        day = getSailingPreferenceUseCase.getDay() == null ? PlannerPresenter.DAY_DEFAULT_VALUE : getSailingPreferenceUseCase.getDay();
        getShipLocationInfo(day);
        view.setScaleAndCenter(getMockCoordinate(day.charAt(0), false));
        view.setCruiseCoordinate(getMockCoordinate(day.charAt(0), true));
    }

    public void getShipLocationInfo(String day) {
        int selectedDay = Integer.valueOf(day);
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

    private PointF getMockCoordinate(char c, boolean hasOffset) {
        PointF coordinate = new PointF();
        int midWidht = IMAGE_WIDTH / 2;
        int midHeight = IMAGE_HEIGHT / 2;
        int differenceDays = 100;
        switch (c) {
            case '1':
                coordinate.set(midWidht - 3 * differenceDays, midHeight);
                break;
            case '2':
                coordinate.set(midWidht - 2 * differenceDays, midHeight);
                break;
            case '3':
                coordinate.set(midWidht - 1 * differenceDays, midHeight);
                break;
            case '4':
                coordinate.set(midWidht, midHeight);
                break;
            case '5':
                coordinate.set(midWidht + 1 * differenceDays, midHeight);
                break;
            case '6':
                coordinate.set(midWidht + 2 * differenceDays, midHeight);
                break;
            case '7':
                coordinate.set(midWidht + 3 * differenceDays, midHeight);
                break;
            case '8':
                coordinate.set(midWidht + 4 * differenceDays, midHeight);
                break;
            default:
                coordinate.set(midWidht, midHeight);
                break;
        }

        if (hasOffset) {
            coordinate.y = coordinate.y + CENTER_OFFSET;
        }

        return coordinate;
    }

    public void onBackPressed() {
        view.animatePointToCenter(getMockCoordinate(day.charAt(0), false), this);
    }

    @Override
    public void onComplete() {
        view.showShipAndFinishWithTransition();
    }

    @Override
    public void onInterruptedByUser() {

    }

    @Override
    public void onInterruptedByNewAnim() {

    }

    @Override
    public void onReady() {
        view.hideShip();
    }

    @Override
    public void onImageLoaded() {

    }

    @Override
    public void onPreviewLoadError(Exception e) {

    }

    @Override
    public void onImageLoadError(Exception e) {

    }

    @Override
    public void onTileLoadError(Exception e) {

    }

    @Override
    public void onPreviewReleased() {

    }
}
