package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.products.ProductDetailActivityComponent;
import com.rcl.excalibur.internal.di.module.products.ProductDetailActivityModule;
import com.rcl.excalibur.mvp.presenter.ProductDetailPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> {
    private static final String EXTRA_DISCOVER_ITEM_ID = "EXTRA_DISCOVER_ITEM_ID";
    private long productId = 0;
    private ProductDetailActivityComponent component;

    public static Intent getIntent(final BaseActivity activity, long productId) {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        intent.putExtra(EXTRA_DISCOVER_ITEM_ID, productId);
        return intent;
    }

    @Override
    protected void createComponent() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DISCOVER_ITEM_ID)) {
            productId = getIntent().getExtras().getLong(EXTRA_DISCOVER_ITEM_ID);
        }
        rclApp.createProductDetailComponent(productId);

        component = rclApp.getProductDetailComponent().plus(new ProductDetailActivityModule(this));
    }

    @Override
    protected void destroyComponent() {
        component = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_item_detail);
        ButterKnife.bind(this);
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rclApp.destroyProductDetailComponent();
    }

    @OnClick(R.id.back_arrow)
    void onBackClicked(View view) {
        presenter.onBackClicked();
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        component.inject(this);
    }
}
