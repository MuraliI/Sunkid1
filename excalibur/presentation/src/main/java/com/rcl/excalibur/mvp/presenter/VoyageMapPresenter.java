package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetShipStatsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetShipStatsUseCase;
import com.rcl.excalibur.model.ShipStatsModel;
import com.rcl.excalibur.mvp.view.VoyageMapView;

import java.util.ArrayList;
import java.util.List;

public class VoyageMapPresenter {
    private VoyageMapView view;
    private GetShipStatsUseCase getShipStatsUseCase;
    private GetShipStatsDbUseCase getShipStatsDbUserCase;

    public VoyageMapPresenter(VoyageMapView view, GetShipStatsUseCase getShipStatsUseCase, GetShipStatsDbUseCase getShipStatsDbUserCase) {
        this.view = view;
        this.getShipStatsUseCase = getShipStatsUseCase;
        this.getShipStatsDbUserCase = getShipStatsDbUserCase;
    }

    public void init() {
        initView();
    }

    public void initView() {
        view.initVoyageMapImage(R.drawable.voyage_land);
        view.setCruiseCoordinate(796, 826);
        getShipStatsUseCase.execute(new DefaultObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                List<ShipStatsModel> list = new ArrayList<>();
                ShipStatsModel shipStatsModel = new ShipStatsModel();
                shipStatsModel.setName("Mock data");
                list.add(shipStatsModel);
                list.add(shipStatsModel);
                list.add(shipStatsModel);
                list.add(shipStatsModel);
                view.addAll(list);
            }
        }, null);
    }
}
