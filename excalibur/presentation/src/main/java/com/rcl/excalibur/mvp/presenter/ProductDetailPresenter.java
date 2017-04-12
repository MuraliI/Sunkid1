package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.factory.DetailViewTypeFactory;
import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.ProductDetailView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.List;

public class ProductDetailPresenter {
    private static final float MULTIPLIER_LOCATION_Y = 0.85f;
    private static final int MAX_BLUR_VALUE = 25;
    protected boolean isTitleVisible = false;
    private GetProductDbUseCase getProductDbUseCase;
    private ProductDetailView view;

    public ProductDetailPresenter(ProductDetailView view, GetProductDbUseCase getProductDbUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
    }

    public void init(String productId) {
        final ProductDetailActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        final Product product = getProductDbUseCase.get(productId);
        if (product == null) {
            view.showToastAndFinishActivity("Discover Item Not Found");
            return;
        }

        view.initStatusBarHeight();
        view.setupToolbar();
        view.initAnimation();
        final Media productMedia = product.getProductMedia();
        view.setHeroImage(productMedia != null
                && productMedia.getMediaItem() != null
                && productMedia.getMediaItem().size() > 0
                ? BuildConfig.PREFIX_IMAGE + product.getHeroImageRefLink()
                : null);

        view.setViewObserver(new LocationOnScreenObserver(this));
        view.setAdapterObserver(new FindOnDeckClickObserver(this));
        final List<RecyclerViewType> viewTypes = DetailViewTypeFactory.getAdaptersAndViewTypesForModel(product, activity.getResources());
        view.render(viewTypes);
    }

    public void onBackClicked() {
        ProductDetailActivity activity = view.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    public void onOffsetChanged(int verticalOffset, int totalScrollRange) {
        view.setBlurRadiusOnImage(getBlurRadius(verticalOffset, totalScrollRange));
    }

    protected float getBlurRadius(int verticalOffset, int totalScrollRange) {
        return (Math.abs(verticalOffset) * MAX_BLUR_VALUE) / totalScrollRange;
    }

    protected void checkLocationOnScreen(int[] values) {
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

    public static class FindOnDeckClickObserver extends DefaultPresentObserver<Long, ProductDetailPresenter> {

        FindOnDeckClickObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Long productId) {
            final BaseActivity activity = getPresenter().view.getActivity();
            if (activity == null) {
                return;
            }
            ActivityUtils.startActivity(activity, ProductDeckMapActivity.getIntent(activity, productId));
        }
    }

    public static class LocationOnScreenObserver extends DefaultPresentObserver<int[], ProductDetailPresenter> {

        LocationOnScreenObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(int[] values) {
            getPresenter().checkLocationOnScreen(values);
        }
    }
}
