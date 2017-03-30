package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDeckMapActivityModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDeckMapActivityScope;
import com.rcl.excalibur.mvp.presenter.ProductDeckMapPresenterTest;

import dagger.Subcomponent;


@ProductDeckMapActivityScope
@Subcomponent(modules = {ProductsDeckMapActivityModule.class, ProductsDatabaseModule.class})
public interface ProductsDeckMapActivityComponentTest extends ProductsDeckMapActivityComponent {
    void inject(ProductDeckMapPresenterTest productDeckMapPresenterTest);
}
