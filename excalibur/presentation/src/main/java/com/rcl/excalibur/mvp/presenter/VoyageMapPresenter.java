package com.rcl.excalibur.mvp.presenter;

import android.text.TextUtils;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.mvp.view.VoyageMapView;

public class VoyageMapPresenter {
    private VoyageMapView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;

    public VoyageMapPresenter(VoyageMapView view, GetSailingPreferenceUseCase getSailingPreferenceUseCase) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
    }

    public void init() {
        initView();
    }

    public void initView() {
        view.setCruiseCoordinate(796, 826);
        view.setCruiseAngle(20);
        view.initVoyageMapImage(R.drawable.voyage_land);
    }

    public void onResume() {
        String day = getSailingPreferenceUseCase.getDay();
        if (TextUtils.isEmpty(day)) {
            day = "day 1";
        }
        view.setHeader(day);
    }
}
