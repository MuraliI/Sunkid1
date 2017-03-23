package com.rcl.excalibur.mvp.presenter;


import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.view.DiscoverDeckMapView;
import com.rcl.excalibur.utils.DetailModelProvider;

import javax.inject.Inject;

public class DiscoverDeckMapPresenter implements BasePresenter {
    @Inject GetProductDbUseCase getProductDbUseCase;
    private DiscoverDeckMapView view;
    private DiscoverItemModel itemModel;

    public DiscoverDeckMapPresenter(DiscoverDeckMapView view, long productId) {
        this.view = view;

        Product product = getProductDbUseCase.get(0);

        itemModel = DetailModelProvider.discoverItemMap.get(productId); //TODO query database
        initInjection();
        initView();
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.initDeckImage(R.drawable.map_05_fwd);
        }
    }

    private void initInjection() {
        final BaseActivity activity = (BaseActivity) view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    public void onMarkerClicked() {

    }

    public void onBackClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
    }
}
