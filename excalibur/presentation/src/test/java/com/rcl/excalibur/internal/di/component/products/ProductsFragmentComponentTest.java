package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductsFragmentModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsFragmentScope;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenterTest;

import dagger.Subcomponent;

@ProductsFragmentScope
@Subcomponent(modules = ProductsFragmentModule.class)
public interface ProductsFragmentComponentTest extends ProductsFragmentComponent{

    void inject(DiscoverTabPresenterTest discoverTabPresenterTest);
}
