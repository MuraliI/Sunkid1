package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.view.VoyageMapView;

public class VoyageMapPresenter {
    private VoyageMapView view;

    public VoyageMapPresenter(VoyageMapView view) {
        this.view = view;
    }

    public void init() {
        initView();
    }

    public void onBackPressed() {
        view.showShipAndFinishWithTransition();
    }

    private void initView() {
        view.hideShip();
        view.initVoyageMapImage(R.drawable.voyage_land);
        view.setCruiseCoordinate(796, 826);
    }
}
