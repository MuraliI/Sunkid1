package com.rcl.excalibur.internal.di.component.detail;

import com.rcl.excalibur.internal.di.component.products.ProductDetailActivityComponent;
import com.rcl.excalibur.internal.di.module.products.ProductDetailActivityModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailActivityScope;
import com.rcl.excalibur.mvp.presenter.ProductDetailPresenterTest;

import dagger.Subcomponent;

@ProductDetailActivityScope
@Subcomponent(modules = ProductDetailActivityModule.class)
public interface ProductDetailActivityComponentTest extends ProductDetailActivityComponent {
    void inject(ProductDetailPresenterTest productDetailPresenterTest);
}
