package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.activity.ProductsBaseActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.domain.interactor.GetProductsUseCase;
import com.rcl.excalibur.internal.di.scopes.product.ProductsActivityScope;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.view.TriptychHomeView;

import dagger.Module;
import dagger.Provides;

@ProductsActivityScope
@Module
public class ProductsActivityModule {
    private ProductsBaseActivity activity;

    public ProductsActivityModule(ProductsBaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    ProductsBaseActivity providesBaseActivity() {
        return activity;
    }

    @Provides
    TriptychHomeView providesTriptychHomeView(ProductsBaseActivity activity) {
        return new TriptychHomeView(((TriptychHomeActivity) activity));
    }

    @Provides
    TriptychHomePresenter providesTriptychHomePresenter(TriptychHomeView triptychHomeView, GetProductsUseCase getProductsUseCase) {
        return new TriptychHomePresenter(triptychHomeView, getProductsUseCase);
    }
}
