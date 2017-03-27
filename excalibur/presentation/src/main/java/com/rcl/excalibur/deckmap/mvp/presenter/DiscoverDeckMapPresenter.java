package com.rcl.excalibur.deckmap.mvp.presenter;


import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.deckmap.custom.view.MarkerImageView;
import com.rcl.excalibur.deckmap.model.ProductDeckMapModel;
import com.rcl.excalibur.deckmap.mvp.view.DiscoverDeckMapView;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;

import javax.inject.Inject;

public class DiscoverDeckMapPresenter implements MarkerImageView.OnMarkerClickListener, DiscoverDeckMapView.OnViewReadyListener {
    private static final String SPA = "SPA";
    private static final String ENTERTAINMENT = "ENTERTAINMENT";
    private static final String ACTIVITIES = "ACTIVITIES";
    private static final String DINING = "DINING";
    private static final String SHOREX = "SHOREX";

    @Inject GetProductDbUseCase getProductDbUseCase;
    private DiscoverDeckMapView view;

    private ProductDeckMapModel productDeckMapModel;

    public DiscoverDeckMapPresenter(DiscoverDeckMapView view, long productId) {
        this.view = view;

        initInjection();
        initProductDeckMap(productId);
        initView();
    }

    private void initProductDeckMap(long productId) {
        Product product = getProductDbUseCase.get(productId);

        productDeckMapModel = new ProductDeckMapModel();
        productDeckMapModel.setProduct(product);
        productDeckMapModel.setCoordinate(getCoordinate(product.getProductType().getProductType()));
    }

    private PointF getCoordinate(String productType) {
        PointF productCoord = new PointF();
        switch (productType) {
            case SPA:
                productCoord.set(196, 526);
                break;
            case ENTERTAINMENT:
                productCoord.set(116, 841);
                break;
            case ACTIVITIES:
                productCoord.set(192, 421);
                break;
            case DINING:
                productCoord.set(120, 539);
                break;
            case SHOREX:
                productCoord.set(243, 558);
                break;
            default:
                productCoord.set(196, 526);
                break;
        }
        return productCoord;
    }

    private void initView() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.initDeckImage(R.drawable.map_05_fwd);
            view.initPopupLayout();
            view.setProductDeckMapModel(productDeckMapModel);
            view.setOnMarkerClickListener(this);
            view.setListener(this);
        }
    }

    private void initInjection() {
        final BaseActivity activity = (BaseActivity) view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    @Override
    public void isInsideRegion() {
        AppCompatActivity activity = view.getActivity();
        if (activity != null) {
            view.moveToProductCoordinate(productDeckMapModel.getCoordinate());
            view.showProductOnPopupLayout(productDeckMapModel.getProduct());
        }
    }

    public void onDestroy() {
        view.onDestroy();
    }

    @Override
    public void onViewReady() {
        isInsideRegion();
    }
}
