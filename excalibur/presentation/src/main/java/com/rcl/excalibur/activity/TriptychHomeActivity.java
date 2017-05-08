package com.rcl.excalibur.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.rcl.excalibur.BuildConfig;
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
import com.rcl.excalibur.utils.ActivityUtils;

import java.lang.reflect.Method;

import butterknife.BindView;

public class TriptychHomeActivity extends BaseActivity {
    @BindView(R.id.day_picker_tab)
    View tabElement;
    @BindView(R.id.image_ship)
    View shipElement;
    @BindView(R.id.pager_triptych_pager)
    View pagerElement;
    private TriptychHomePresenter presenter;
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

    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                Toast.makeText(context, (String) value, Toast.LENGTH_LONG).show();
            } catch (Exception ignore) {

            }
        }
    }

    public void goToVoyageActivity() {
        Pair<View, String> tabPair = Pair.create(tabElement, getString(R.string.shared_element_tab));
        Pair<View, String> shipPair = Pair.create(shipElement, shipElement.getTransitionName());
        ActivityUtils.startActivityWithSharedElements(this, VoyageMapActivity.getStartIntent(this), tabPair, shipPair,
                presenter.getPlannerSharedElementPair());
    }
}
