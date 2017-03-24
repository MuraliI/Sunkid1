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

public class DiscoverDeckMapPresenter implements MarkerImageView.OnMarkerClickListener {
    private static final String SPA = "SPA";
    private static final String ENTERTAINMENT = "ENTERTAINMENT";
    private static final String ACTIVITIES = "ACTIVITIES";
    private static final String DINING = "DINING";
    private static final String SHOREX = "SHOREX";
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
    private static final int X_6 = 196;
    private static final int Y_6 = 526;

    /*@Inject */ GetProductDbUseCase getProductDbUseCase;
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
                productCoord.set(X_1, Y_1);
                break;
            case ENTERTAINMENT:
                productCoord.set(X_2, Y_2);
                break;
            case ACTIVITIES:
                productCoord.set(X_3, Y_3);
                break;
            case DINING:
                productCoord.set(X_4, Y_4);
                break;
            case SHOREX:
                productCoord.set(X_5, Y_5);
                break;
            default:
                productCoord.set(X_6, Y_6);
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
        }
    }

    private void initInjection() {
        final BaseActivity activity = (BaseActivity) view.getActivity();
        if (activity == null) {
            return;
        }
        /*activity.getApplicationComponent().inject(this);*/
    }

    public void onBackClicked() {
        if (view.getActivity() != null) {
            view.getActivity().finish();
        }
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
}
