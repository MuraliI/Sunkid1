package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.repository.WeatherCurrentDataRepository;
import com.rcl.excalibur.data.service.SailDateServicesImpl;
import com.rcl.excalibur.data.service.WeatherInfoServicesImpl;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.interactor.GetWeatherCurrentUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

public class TriptychHomeActivity extends BaseActivity {
    protected TriptychHomePresenter presenter;
    private SailingPreferences sailingPreferences;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);
        sailingPreferences = new SailingPreferenceImpl(this);
        presenter = new TriptychHomePresenter(
                new TriptychHomeView(this),
                new GetProductDbUseCase(new ProductDataRepository()),
                new GetSaildDateUseCase(new SailDateServicesImpl(new SailDateDataRepository())),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository()),
                new GetWeatherCurrentUseCase(new WeatherInfoServicesImpl(new WeatherCurrentDataRepository())),
                new SailingInformationModelDataMapper()
        );
        presenter.init();
    }

    public void getShipLocationInfo() {
        presenter.getShipLocationInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getShipLocationInfo();
    }
}
