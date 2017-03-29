package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailActivityScope;
import com.rcl.excalibur.mvp.presenter.ProductDetailPresenter;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import dagger.Module;
import dagger.Provides;

@ProductDetailActivityScope
@Module
public class ProductDetailActivityModule {
    private final BaseActivity activity;

    public ProductDetailActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesBaseActivity() {
        return activity;
    }

    @Provides
    ProductDetailView providesProductDetailView(BaseActivity activity) {
        return new ProductDetailView(((ProductDetailActivity) activity));
    }

    @Provides
    ProductDetailPresenter providesProductDetailPresenter(long productId, ProductDetailView productDetailView,
                                                          GetProductDbUseCase getProductDbUseCase) {
        return new ProductDetailPresenter(productId, productDetailView, getProductDbUseCase);
    }
}
