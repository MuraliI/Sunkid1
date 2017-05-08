package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.BuildConfig;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.DeckMapActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.delegate.factory.DetailViewTypeFactory;
import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.Menu;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.GetMenusUseCase;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.ProductDetailView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public class ProductDetailPresenter {
    private static final float MULTIPLIER_LOCATION_Y = 0.85f;
    private static final int MAX_BLUR_VALUE = 25;
    protected boolean isTitleVisible = false;
    private GetProductDbUseCase getProductDbUseCase;
    private GetOfferingsDbUseCase getOfferingsDbUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private GetMenusUseCase getMenusUseCase;
    private ProductDetailView view;

    private Product product;

    public ProductDetailPresenter(ProductDetailView view
            , GetProductDbUseCase getProductDbUseCase
            , GetOfferingsDbUseCase getOfferingsDbUseCase
            , GetSaildDateDbUseCase getSaildDateDbUseCase
            , GetMenusUseCase getMenusUseCase) {
        this.view = view;
        this.getProductDbUseCase = getProductDbUseCase;
        this.getOfferingsDbUseCase = getOfferingsDbUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.getMenusUseCase = getMenusUseCase;
    }

    public void init(String productId) {
        final ProductDetailActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }

        product = getProductDbUseCase.get(productId);

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


        starGetMenus();
    }

    public void starGetMenus() {
        getMenusUseCase.execute(new DisposableObserver<List<Menu>>() {
            @Override
            public void onNext(List<Menu> menus) {
                Timber.d("getMenusUseCase", "onNext");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("getMenusUseCase", "onError");
            }

            @Override
            public void onComplete() {
                final List<Offering> offerings = getOfferingsDbUseCase.getOfferingsForProduct(product);
                final SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
                final List<RecyclerViewType> viewTypes = DetailViewTypeFactory.getAdaptersAndViewTypesForModel(product
                        , offerings
                        , sailDateInfo
                        , view.getActivity().getResources());
                view.render(viewTypes);

            }
        }, null);
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

    public static class FindOnDeckClickObserver extends DefaultPresentObserver<String, ProductDetailPresenter> {

        FindOnDeckClickObserver(ProductDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(String productId) {
            final BaseActivity activity = getPresenter().view.getActivity();
            if (activity == null) {
                return;
            }
            ActivityUtils.startActivity(activity, DeckMapActivity.getIntent(activity, productId));
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
