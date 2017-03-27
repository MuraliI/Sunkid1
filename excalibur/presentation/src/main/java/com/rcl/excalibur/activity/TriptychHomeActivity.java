package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsActivityComponent;
import com.rcl.excalibur.internal.di.module.products.ProductsActivityModule;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;

public class TriptychHomeActivity extends BaseActivity<TriptychHomePresenter> {
    private ProductsActivityComponent productsActivityComponent;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triptych_home_screen);
        presenter.start();
    }

    @Override
    protected void createComponent() {
        rclApp.createProductsComponent();

        productsActivityComponent = rclApp.getProductsComponent().plus(new ProductsActivityModule(this));
    }

    public ProductsActivityComponent getProductsActivityComponent() {
        return productsActivityComponent;
    }

    @Override
    protected void destroyComponent() {
        productsActivityComponent = null;
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        productsActivityComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        rclApp.destroyProductsComponent();
    }
}
