package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.WeatherCurrent;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.interactor.GetShipStatsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipStatsUseCase;
import com.rcl.excalibur.domain.interactor.GetWeatherCurrentDbUseCase;
import com.rcl.excalibur.domain.interactor.GetWeatherCurrentUseCase;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.model.ShipStatsModel;
import com.rcl.excalibur.model.VoyageMapModel;
import com.rcl.excalibur.mvp.view.VoyageMapView;
import com.rcl.excalibur.utils.CompassUtils;
import com.rcl.excalibur.utils.DateUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static com.rcl.excalibur.model.PortModel.PORT_TYPE_CRUISING;
import static com.rcl.excalibur.model.PortModel.PORT_TYPE_DEBARK;
import static com.rcl.excalibur.model.PortModel.PORT_TYPE_DOCKED;
import static com.rcl.excalibur.model.PortModel.PORT_TYPE_EMBARK;


public class VoyageMapPresenter implements SubsamplingScaleImageView.OnAnimationEventListener {

    private VoyageMapView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private GetShipStatsDbUseCase getShipStatsDbUseCase;
    private GetShipStatsUseCase getShipStatsUseCase;
    private GetWeatherCurrentDbUseCase getWeatherCurrentDbUseCase;
    private GetWeatherCurrentUseCase getWeatherCurrentUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;
    private String day;
    private VoyageMapModel voyageModel;
    private Boolean isCurrentSelected = false;

    public VoyageMapPresenter(VoyageMapView view,
                              GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                              GetSaildDateDbUseCase getSaildDateDbUseCase,
                              SailingInformationModelDataMapper sailingInformationModelDataMapper,
                              GetShipStatsDbUseCase getShipStatsDbUseCase,
                              GetWeatherCurrentUseCase getWeatherCurrentUseCase,
                              GetWeatherCurrentDbUseCase getWeatherCurrentDbUseCase,
                              GetShipStatsUseCase getShipStatsUseCase) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.sailingInformationModelDataMapper = sailingInformationModelDataMapper;
        this.getShipStatsDbUseCase = getShipStatsDbUseCase;
        this.getShipStatsUseCase = getShipStatsUseCase;
        this.getWeatherCurrentDbUseCase = getWeatherCurrentDbUseCase;
        this.getWeatherCurrentUseCase = getWeatherCurrentUseCase;
    }

    public void initMap() {
        initVoyageMapImage();
    }

    public void initTab() {
        voyageModel = new VoyageMapModel();
        setImageDimensions(R.drawable.caribbean_map_4);
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

    private void setImageDimensions(int resource) {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(activity.getResources(), resource, options);
        voyageModel.setWidth(options.outWidth);
        voyageModel.setHeigth(options.outHeight);
    }

    private void initVoyageMapImage() {
        view.initVoyageMapImage();
        day = getSailingPreferenceUseCase.getDay() == null
                ? PlannerPresenter.DAY_DEFAULT_VALUE : getSailingPreferenceUseCase.getDay();
        view.setCruiseCoordinate(voyageModel.getMockCoordinate(day.charAt(0), true));
        view.setScaleAndCenter(voyageModel.getMockCoordinate(day.charAt(0), false));
        view.hideShip();
        getShipStatsUseCase.execute(new ShipStatsObserver(this), null);
    }

    public void onResume() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        day = getSailingPreferenceUseCase.getDay() == null ? PlannerPresenter.DAY_DEFAULT_VALUE : getSailingPreferenceUseCase.getDay();
        getShipLocationInfo(day);
        view.setScaleAndCenter(voyageModel.getMockCoordinate(day.charAt(0), false));
        view.setCruiseCoordinate(voyageModel.getMockCoordinate(day.charAt(0), true));

        addListMock();
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
            isCurrentSelected = selectedDay == itinerary.getIndexCurrentDay();
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

    public void onBackPressed() {
        view.animatePointToCenter(voyageModel.getMockCoordinate(day.charAt(0), false), this);
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

    private void addListMock() {
        ShipStatsInfo shipStatsInfo = getShipStatsDbUseCase.get();
        WeatherCurrent weatherCurrent = getWeatherCurrentDbUseCase.get();

        if (shipStatsInfo != null && weatherCurrent != null) {
            ShipStatsModel weather = new ShipStatsModel();
            weather.setName(weatherCurrent.getWeatherStats());
            weather.setResource(R.drawable.icon_voyage_sunny);

            ShipStatsModel sunrise = new ShipStatsModel();
            sunrise.setName(DateUtils.getDayHour(weatherCurrent.getSunrise()));
            sunrise.setResource(R.drawable.icon_voyage_sunrise);

            ShipStatsModel sunset = new ShipStatsModel();
            sunset.setName(DateUtils.getDayHour(weatherCurrent.getSunset()));
            sunset.setResource(R.drawable.icon_voyage_sunset);

            ShipStatsModel speed = new ShipStatsModel();
            speed.setName(shipStatsInfo.getShipLocationStats().getShipSpeed());
            speed.setResource(R.drawable.icon_voyage_speed);

            ShipStatsModel compass = new ShipStatsModel();
            compass.setName(CompassUtils.getCompassByUnit(shipStatsInfo.getShipLocationStats().getHeading()));
            compass.setResource(R.drawable.icon_voyage_compass);


            ShipStatsModel gangwayUp = new ShipStatsModel();
            //FIXME change when services return data for that
            gangwayUp.setName("3:13 am");
            gangwayUp.setResource(R.drawable.icon_voyage_gangway_up);

            ShipStatsModel gangwayDown = new ShipStatsModel();
            //FIXME change when services return data for that
            gangwayDown.setName("3:14 am");
            gangwayDown.setResource(R.drawable.icon_voyage_gangway_down);


            List<ShipStatsModel> list = new ArrayList<>();
            list.add(weather);
            list.add(sunrise);
            list.add(sunset);
            list.add(speed);
            list.add(compass);
            list.add(gangwayUp);
            list.add(gangwayDown);

            if (isCurrentSelected)
                view.addAll(list);
            else
                view.addAll(new ArrayList<>());
        }
    }

    public void onDestroy() {
        view.onDestroy();
    }

    private void loadShipWeather() {
        ShipStatsInfo shipStatsInfo = getShipStatsDbUseCase.get();
        if (shipStatsInfo != null) {
            getWeatherCurrentUseCase.execute(new DisposableObserver<Boolean>() {

                @Override
                public void onNext(Boolean value) {
                    addListMock();
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            }, shipStatsInfo);
        }
    }


    private class ShipStatsObserver extends DisposableObserver<Boolean> {

        WeakReference<VoyageMapPresenter> presenterWeakReference;

        ShipStatsObserver(VoyageMapPresenter presenter) {
            presenterWeakReference = new WeakReference<>(presenter);
        }

        @Override
        public void onNext(Boolean value) {
            if (presenterWeakReference != null)
                presenterWeakReference.get().loadShipWeather();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
