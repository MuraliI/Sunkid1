package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.repository.ShipStatsDataRepository;
import com.rcl.excalibur.data.repository.WeatherCurrentDataRepository;
import com.rcl.excalibur.data.service.ShipStatsServicesImpl;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.interactor.GetShipStatsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipStatsUseCase;
import com.rcl.excalibur.domain.interactor.GetWeatherCurrentDbUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.mvp.presenter.VoyageMapPresenter;
import com.rcl.excalibur.mvp.view.VoyageMapView;
import com.rcl.excalibur.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoyageMapActivity extends BaseActivity {
    VoyageMapPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_map);
        ButterKnife.bind(this);
        final SailingPreferences sailingPreferences = new SailingPreferenceImpl(getApplicationContext());
        final SailDateDataRepository sailDateDataRepository = new SailDateDataRepository();
        presenter = new VoyageMapPresenter(new VoyageMapView(this),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(sailDateDataRepository),
                new SailingInformationModelDataMapper(),
                new GetShipStatsDbUseCase(new ShipStatsDataRepository()),
                new GetWeatherCurrentDbUseCase(new WeatherCurrentDataRepository()),
                new GetShipStatsUseCase(new ShipStatsServicesImpl(new ShipStatsDataRepository())));
        presenter.initTab();
        Transition transition = getWindow().getSharedElementEnterTransition();
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                // Nothing
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                presenter.initMap();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                // Nothing
            }

            @Override
            public void onTransitionPause(Transition transition) {
                // Nothing
            }

            @Override
            public void onTransitionResume(Transition transition) {
                // Nothing
            }
        });
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @OnClick(R.id.date_picker_plans_tab)
    void onDayPickerClick() {
        ActivityUtils.startActivity(this, DayPickerActivity.getStartIntent(this));
    }

    @OnClick(R.id.bottom_back_view)
    void onBackClick() {
        this.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, VoyageMapActivity.class);
    }
}
