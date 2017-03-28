package com.rcl.excalibur.mvp.presenter;


import android.graphics.PointF;
import android.support.annotation.NonNull;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.mvp.view.DiscoverDeckMapView;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;

import javax.inject.Inject;

public class DiscoverDeckMapPresenter {
    private static final String SPA = "SPA";
    private static final String ENTERTAINMENT = "ENTERTAINMENT";
    private static final String ACTIVITIES = "ACTIVITIES";
    private static final String DINING = "DINING";
    private static final String SHOREX = "SHOREX";

    @Inject GetProductDbUseCase getProductDbUseCase;
    private DiscoverDeckMapView view;

    private Product product;
    private float xCoord;
    private float yCoord;

    public DiscoverDeckMapPresenter(@NonNull DiscoverDeckMapView view, long productId) {
        this.view = view;

        initInjection();

        product = getProductDbUseCase.get(productId);
        if (product != null) {
            setCoordinate(product.getProductType().getProductType());
        }

        initView();
    }

    private void initView() {
        view.initDeckImage(R.drawable.map_05_fwd);
        view.setProductCoordinate(xCoord, yCoord);
        view.initPopupLayout();
    }

    private void initInjection() {
        final DiscoverDeckMapActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        activity.getApplicationComponent().inject(this);
    }

    // TODO: In a future we'll get this coordinate from the service
    private void setCoordinate(String productType) {
        switch (productType) {
            case SPA:
                xCoord = 196;
                yCoord = 526;
                break;
            case ENTERTAINMENT:
                xCoord = 116;
                yCoord = 841;
                break;
            case ACTIVITIES:
                xCoord = 192;
                yCoord = 421;
                break;
            case DINING:
                xCoord = 120;
                yCoord = 539;
                break;
            case SHOREX:
                xCoord = 243;
                yCoord = 558;
                break;
            default:
                xCoord = 196;
                yCoord = 526;
                break;
        }
    }

    public void onTouchDeckMapImage(PointF touchedLocation) {
        if (view.isDeckMapImageReady() && view.getMarkerArea().contains(touchedLocation.x, touchedLocation.y)) {
            moveToCoordinateAndShowPopup();
        }
    }

    public void onDismissPopupWindow() {
        view.dismissPopupWindow();
    }

    public void onGlobalLayout() {
        if (view.isDeckMapImageReady()) {
            view.removeTreeObserver();
            moveToCoordinateAndShowPopup();
        }
    }

    private void moveToCoordinateAndShowPopup() {
        if (product == null) {
            return;
        }
        view.moveToProductCoordinate(xCoord, yCoord);
        view.showProductOnPopupLayout(product);
    }
}
