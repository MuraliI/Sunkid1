package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ProductsActivityComponent;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;

public class TriptychHomeActivity extends ProductsBaseActivity<TriptychHomePresenter> {

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, TriptychHomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createProductsComponent();
        setContentView(R.layout.activity_triptych_home_screen);
        presenter.start();
    }

    @Override
    protected void injectActivity(ProductsActivityComponent productsActivityComponent) {
        productsActivityComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyProductsComponent();
    }
}
