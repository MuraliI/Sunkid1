package com.rcl.excalibur.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rcl.excalibur.R;
import com.rcl.excalibur.data.repository.ShipTimeDataRepository;
import com.rcl.excalibur.data.service.ShipTimeServicesImpl;
import com.rcl.excalibur.domain.interactor.GetShipTimeDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipTimeUseCase;
import com.rcl.excalibur.mvp.presenter.ShipTimePresenter;
import com.rcl.excalibur.mvp.view.ShipTimeView;

public class ShipTimeFragment extends Fragment {
    protected ShipTimePresenter shipTimePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.ship_time, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ShipTimeDataRepository shipTimeDataRepository = new ShipTimeDataRepository();
        shipTimePresenter = new ShipTimePresenter(new ShipTimeView(this)
                , new GetShipTimeUseCase(new ShipTimeServicesImpl(shipTimeDataRepository))
                , new GetShipTimeDbUseCase(shipTimeDataRepository));
    }

    @Override
    public void onResume() {
        super.onResume();
        shipTimePresenter.register();
    }

    @Override
    public void onPause() {
        super.onPause();
        shipTimePresenter.unregister();
    }
}
