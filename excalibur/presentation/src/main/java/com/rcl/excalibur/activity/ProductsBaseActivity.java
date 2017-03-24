package com.rcl.excalibur.activity;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.ProductsActivityComponent;
import com.rcl.excalibur.internal.di.module.ProductsActivityModule;
import com.rcl.excalibur.mvp.presenter.ProductsActivityPresenter;

public abstract class ProductsBaseActivity<P extends ProductsActivityPresenter> extends BaseActivity<P> {
    private ProductsActivityComponent productsActivityComponent;

    protected void createProductsComponent() {
        ((RCLApp) getApplication()).createProductsComponent();
    }

    protected void destroyProductsComponent() {
        ((RCLApp) getApplication()).destroyProductsComponent();
    }

    @Override
    protected void createActivityComponent() {
        productsActivityComponent = ((RCLApp) getApplication()).getProductsComponent()
                .plus(new ProductsActivityModule(this));
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        injectActivity(productsActivityComponent);
    }

    protected abstract void injectActivity(ProductsActivityComponent productsActivityComponent);
}
