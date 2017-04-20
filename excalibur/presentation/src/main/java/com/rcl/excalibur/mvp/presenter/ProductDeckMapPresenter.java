package com.rcl.excalibur.mvp.presenter;


import android.graphics.PointF;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDeckMapView;

import static com.rcl.excalibur.utils.CategoryUtils.ACTIVITIES;
import static com.rcl.excalibur.utils.CategoryUtils.DINING;
import static com.rcl.excalibur.utils.CategoryUtils.ENTERTAINMENT;
import static com.rcl.excalibur.utils.CategoryUtils.SHOREX;
import static com.rcl.excalibur.utils.CategoryUtils.SPA;

public class ProductDeckMapPresenter {

    private static final int X_1 = 196;
    private static final int Y_1 = 526;
    private static final int X_2 = 116;
    private static final int Y_2 = 841;
    private static final int X_3 = 192;
    private static final int Y_3 = 421;
    private static final int X_4 = 120;
    private static final int Y_4 = 539;
    private static final int X_5 = 243;
    private static final int Y_5 = 558;

    private GetProductDbUseCase getProductDbUseCase;
    private ProductDeckMapView view;

    private Product product;
    private float xCoord;
    private float yCoord;

    public ProductDeckMapPresenter(ProductDeckMapView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(String productId) {
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

    // TODO: In a future we'll get this coordinate from the service
    private void setCoordinate(String productType) {
        switch (productType) {
            case SPA:
                xCoord = X_1;
                yCoord = Y_1;
                break;
            case ENTERTAINMENT:
                xCoord = X_2;
                yCoord = Y_2;
                break;
            case ACTIVITIES:
                xCoord = X_3;
                yCoord = Y_3;
                break;
            case DINING:
                xCoord = X_4;
                yCoord = Y_4;
                break;
            case SHOREX:
                xCoord = X_5;
                yCoord = Y_5;
                break;
            default:
                xCoord = X_1;
                yCoord = Y_1;
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
