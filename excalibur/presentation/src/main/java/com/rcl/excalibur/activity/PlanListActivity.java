package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsListActivityComponent;
import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModule;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanListActivity extends BaseActivity<PlanListPresenter> {
    private ProductsListActivityComponent productsListActivityComponent;
    public static final String EXTRA_FRAGMENT_TYPE = "EXTRA_FRAGMENT_TYPE";

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, PlanListActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        ButterKnife.bind(this);
        presenter.init();
    }

    @OnClick(R.id.plans_header_back_layout)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @Override
    protected void createComponent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_FRAGMENT_TYPE)) {
            int fragmentToShow = intent.getExtras().getInt(EXTRA_FRAGMENT_TYPE);
            rclApp.createProductListComponent(fragmentToShow);

            productsListActivityComponent = rclApp.getProductsListComponent().plus(new ProductsListActivityModule(this));
        }
    }

    public ProductsListActivityComponent getProductsListActivityComponent() {
        return productsListActivityComponent;
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        productsListActivityComponent.inject(this);
    }

    @Override
    protected void destroyComponent() {
        productsListActivityComponent = null;
        rclApp.destroyProductsListComponent();
    }
}
