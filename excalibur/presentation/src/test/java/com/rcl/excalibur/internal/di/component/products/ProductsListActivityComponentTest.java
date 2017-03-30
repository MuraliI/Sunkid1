package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListActivityScope;
import com.rcl.excalibur.mvp.presenter.PlanListPresenterTest;

import dagger.Subcomponent;

@ProductsListActivityScope
@Subcomponent(modules = ProductsListActivityModule.class)
public interface ProductsListActivityComponentTest extends ProductsListActivityComponent {
    void inject(PlanListPresenterTest planListPresenterTest);
}
