package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.factory.DetailViewTypeFactory;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import java.util.List;

public class ProductDetailPresenter implements ActivityPresenter {
    private static final int MAX_BLUR_VALUE = 25;
    private static final int HEIGHT_FACTOR = 4;
    private static final float MULTIPLIER_LOCATION_Y = 0.85f;

    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;
    private Product product;
    private List<RecyclerViewType> viewTypes;

    protected boolean isTitleVisible = false;
    private long productId;

    public ProductDetailPresenter(long productId, ProductDetailView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.productId = productId;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init() {
        product = getProductDbUseCase.get(productId);
        if (product != null && view.getActivity() != null) {
            viewTypes = DetailViewTypeFactory.getAdaptersAndViewTypesForModel(product, view.getActivity().getResources());
            initView();
        } else {
            view.showToastAndFinishActivity("Discover Item Not Found");
        }
    }

    private void initView() {
        ProductDetailActivity activity = view.getActivity();
        if (activity != null) {
            view.initStatusBarHeight();
            view.setupToolbar();
            view.initAnimation();
            if (product.getProductMedia() != null
                    && product.getProductMedia().getMediaItem() != null
                    && product.getProductMedia().getMediaItem().size() > 0) {
                view.setHeroImage(BuildConfig.PREFIX_IMAGE + product.getHeroImageRefLink());
            } else {
                view.setHeroImage(null);
            }
            view.setViewObserver(new LocationOnScreenObserver(this));
            view.setAdapterObserver(new FindOnDeckClickObserver(this));
            view.render(viewTypes);
        }
    }

    public void onBackClicked() {
        ProductDetailActivity activity = view.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override
    public ProductDetailView getView() {
        return view;
    }

    public void onOffsetChanged(int verticalOffset, int totalScrollRange) {
        view.setBlurRadiusOnImage(getBlurRadius(verticalOffset, totalScrollRange));
    }

    protected float getBlurRadius(int verticalOffset, int totalScrollRange) {
        return (Math.abs(verticalOffset) * MAX_BLUR_VALUE) / totalScrollRange;
    }

    private class FindOnDeckClickObserver extends DefaultPresentObserver<Long, ProductDetailPresenter> {

        FindOnDeckClickObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Long productId) {
            final BaseActivity activity = view.getActivity();
            if (activity != null) {
                activity.startActivity(ProductDeckMapActivity.getIntent(activity, productId));
            }
        }
    }

    private class LocationOnScreenObserver extends DefaultPresentObserver<int[], ProductDetailPresenter> {

        LocationOnScreenObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(int[] values) {
            checkLocationOnScreen(values);
        }
    }

    private void checkLocationOnScreen(int[] values) {
        int outLocationY = values[0];
        float limitLocationY = (values[1] + values[2]) * MULTIPLIER_LOCATION_Y;

        if (outLocationY < limitLocationY && !isTitleVisible) {
            isTitleVisible = true;
            view.showCollapsingToolbarTitle();
        } else if (outLocationY >= limitLocationY && isTitleVisible) {
            isTitleVisible = false;
            view.hideCollapsingToolbarTitle();
        }
    }

    private class OnScrollObserver extends DefaultPresentObserver<int[], ProductDetailPresenter> {

        OnScrollObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(int[] values) {
            calculateScrollToTitle(values);
        }
    }

    protected void calculateScrollToTitle(int[] values) {
        int scrolledAmount = values[0];
        int parentPaddingTop = values[1];
        int titleHeight = values[2];

        if (scrolledAmount >= parentPaddingTop + titleHeight / HEIGHT_FACTOR
                && !isTitleVisible) {
            isTitleVisible = true;
            view.showCollapsingToolbarTitle();
        } else if (scrolledAmount < parentPaddingTop + titleHeight / HEIGHT_FACTOR
                && isTitleVisible) {
            isTitleVisible = false;
            view.hideCollapsingToolbarTitle();
        }
    }
}
