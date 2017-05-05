package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.ShipStatsDataRepository;
import com.rcl.excalibur.data.service.ShipStatsServicesImpl;
import com.rcl.excalibur.domain.interactor.GetShipStatsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipStatsUseCase;
import com.rcl.excalibur.mvp.presenter.VoyageMapPresenter;
import com.rcl.excalibur.mvp.view.VoyageMapView;

public class VoyageMapActivity extends BaseActivity {

    VoyageMapPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voyage_map);
        presenter = new VoyageMapPresenter(new VoyageMapView(this),
                new GetShipStatsUseCase(new ShipStatsServicesImpl(new ShipStatsDataRepository())),
                new GetShipStatsDbUseCase(new ShipStatsDataRepository())
        );
        presenter.init();
    }

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, VoyageMapActivity.class);
    }

}
