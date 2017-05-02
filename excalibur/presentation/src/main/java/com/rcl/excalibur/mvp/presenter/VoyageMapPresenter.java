package com.rcl.excalibur.mvp.presenter;

import android.graphics.Point;
import android.text.TextUtils;
import android.view.Display;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.VoyageMapActivity;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.mvp.view.VoyageMapView;

public class VoyageMapPresenter {
    private VoyageMapView view;
    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    String day;

    public VoyageMapPresenter(VoyageMapView view, GetSailingPreferenceUseCase getSailingPreferenceUseCase) {
        this.view = view;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
    }

    public void init() {
        initVoyageMapImage();
        view.init(getScreenWidth());
    }

    public int getScreenWidth() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return 0;
        }
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public void initVoyageMapImage() {
        view.setCruiseCoordinate(796, 826);
        view.setCruiseAngle(20);
        view.hideShip();
        view.initVoyageMapImage(R.drawable.voyage_land);
    }

    public void onResume() {
        VoyageMapActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        day = getSailingPreferenceUseCase.getDay();
        if (TextUtils.isEmpty(day)) {
            day = activity.getResources().getString(R.string.day_1);
        } else {
            day = activity.getResources().getString(R.string.day_title) + day;
        }
        view.setHeader(day);
    }

    public void onBackPressed() {
        view.showShipAndFinishWithTransition();
    }
}
