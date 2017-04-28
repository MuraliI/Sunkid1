package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.mapper.SubCategoryResponseDataMapper;
import com.rcl.excalibur.data.preference.DiscoverPreferenceImpl;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.data.repository.SubCategoriesDataRepository;
import com.rcl.excalibur.data.service.DiscoverServicesImpl;
import com.rcl.excalibur.data.service.SailDateServicesImpl;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.interactor.GetSubCategoriesUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomeView;
import com.appdynamics.eumagent.runtime.Instrumentation;


public class TriptychHomeActivity extends BaseActivity {
    private static final String KEY_REPORTING_APP_DYNAMICS = "AD-AAB-AAD-PKJ";

    protected TriptychHomePresenter presenter;
    private SailingPreferences sailingPreferences;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instrumentation.start(TriptychHomeActivity.KEY_REPORTING_APP_DYNAMICS, getApplicationContext());
        setContentView(R.layout.activity_triptych_home_screen);
        sailingPreferences = new SailingPreferenceImpl(this);

        DiscoverServicesImpl impl = new DiscoverServicesImpl(new ProductDataRepository()
                , new DiscoverPreferenceImpl(this));
        impl.setSubCategoryRepository(new SubCategoriesDataRepository());
        impl.setSubCategoryResponseDataMapper(new SubCategoryResponseDataMapper());

        presenter = new TriptychHomePresenter(
                new TriptychHomeView(this),
                new GetProductsUseCase(impl),
                new GetSubCategoriesUseCase(impl),
                new GetSaildDateUseCase(new SailDateServicesImpl(new SailDateDataRepository())),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(new SailDateDataRepository()),
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
