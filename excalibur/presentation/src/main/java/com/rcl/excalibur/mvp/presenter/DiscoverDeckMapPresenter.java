package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverDeckMapView;
import com.rcl.excalibur.utils.DetailModelProvider;

public class DiscoverDeckMapPresenter implements BasePresenter {
    private DiscoverDeckMapView view;
    private DiscoverItemModel itemModel;

    public DiscoverDeckMapPresenter(DiscoverDeckMapView view, String discoverItemId) {
        this.view = view;
        itemModel = DetailModelProvider.discoverItemMap.get(discoverItemId); //TODO query database
        initView();
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.initDeckImage(R.drawable.map_05_fwd);
        }
    }

    public void onMarkerClicked() {

    }

    public void onBackClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
    }
}
