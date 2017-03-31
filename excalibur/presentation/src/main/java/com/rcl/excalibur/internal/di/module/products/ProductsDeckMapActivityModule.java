package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.internal.di.scopes.product.ProductDeckMapActivityScope;
import com.rcl.excalibur.mvp.presenter.ProductDeckMapPresenter;
import com.rcl.excalibur.mvp.view.ProductDeckMapView;

import dagger.Module;
import dagger.Provides;

@ProductDeckMapActivityScope
@Module
public class ProductsDeckMapActivityModule {
    private final BaseActivity activity;

    public ProductsDeckMapActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesBaseActivity() {
        return activity;
    }

    @Provides
    ProductDeckMapView providesProductDeckMapView(BaseActivity activity) {
        return new ProductDeckMapView((ProductDeckMapActivity) activity);
    }

    @Provides
    ProductDeckMapPresenter providesProductDeckMapPresenter(ProductDeckMapView productDetailView,
                                                            GetProductDbUseCase getProductDbUseCase) {
        return new ProductDeckMapPresenter(productDetailView, getProductDbUseCase);
    }
}
