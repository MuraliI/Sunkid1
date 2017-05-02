package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.preference.SailingPreferenceImpl;
import com.rcl.excalibur.data.repository.SailDateDataRepository;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.domain.preference.SailingPreferences;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.mvp.presenter.VoyageMapPresenter;
import com.rcl.excalibur.mvp.view.VoyageMapView;

public class VoyageMapActivity extends BaseActivity {

    VoyageMapPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_map);
        final SailingPreferences sailingPreferences = new SailingPreferenceImpl(getApplicationContext());
        final SailDateDataRepository sailDateDataRepository = new SailDateDataRepository();
        presenter = new VoyageMapPresenter(new VoyageMapView(this),
                new GetSailingPreferenceUseCase(sailingPreferences),
                new GetSaildDateDbUseCase(sailDateDataRepository),
                new SailingInformationModelDataMapper());
        presenter.init();
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
