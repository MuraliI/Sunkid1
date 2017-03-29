package com.rcl.excalibur.activity;


import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.rcl.excalibur.R;
import com.rcl.excalibur.custom.view.DeckMapImageView;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsDeckMapActivityComponent;
import com.rcl.excalibur.mvp.presenter.ProductDeckMapPresenter;

public class ProductDeckMapActivity extends BaseActivity implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String EXTRA_PRODUCT_ITEM_ID = "EXTRA_PRODUCT_ITEM_ID";

    private ProductsDeckMapActivityComponent component;
    private GestureDetector gestureDetector;

    public static Intent getIntent(final BaseActivity activity, long productItemId) {
        Intent intent = new Intent(activity, ProductDeckMapActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ITEM_ID, productItemId);
        return intent;
    }

    @Override
    protected void createComponent() {
        rclApp.createProductDeckMapComponent(this);
        component = rclApp.getProductsDeckMapActivityComponent();
    }

    @Override
    protected void destroyComponent() {
        component = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_deck_map);

        long productItemId = 0L;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PRODUCT_ITEM_ID)) {
            productItemId = intent.getExtras().getLong(EXTRA_PRODUCT_ITEM_ID);
        }

        presenter.init(productItemId);
        initGestureDetector(presenter);
    }

    // Cannot be passed to View because is very expensive to implement
    private void initGestureDetector(ProductDeckMapPresenter presenter) {
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        presenter.onTouchDeckMapImage(new PointF(e.getX(), e.getY()));
                        return true;
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDismissPopupWindow();
        rclApp.destroyProductsDeckMapActivityComponent();
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (view instanceof DeckMapImageView) {
            return gestureDetector.onTouchEvent(event);
        }
        presenter.onDismissPopupWindow();
        return true;
    }

    @Override
    public void onGlobalLayout() {
        presenter.onGlobalLayout();
    }
}
