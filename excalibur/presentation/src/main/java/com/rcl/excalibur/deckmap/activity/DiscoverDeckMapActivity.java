package com.rcl.excalibur.deckmap.activity;


import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.deckmap.custom.view.DeckMapImageView;
import com.rcl.excalibur.deckmap.mvp.presenter.DiscoverDeckMapPresenter;
import com.rcl.excalibur.deckmap.mvp.view.DiscoverDeckMapView;

public class DiscoverDeckMapActivity extends BaseActivity implements View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String EXTRA_PRODUCT_ITEM_ID = "EXTRA_PRODUCT_ITEM_ID";

    private DiscoverDeckMapPresenter presenter;
    private GestureDetector gestureDetector;

    public static Intent getIntent(final BaseActivity activity, long productItemId) {
        Intent intent = new Intent(activity, DiscoverDeckMapActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ITEM_ID, productItemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_deck_map);

        long productItemId = 0L;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PRODUCT_ITEM_ID)) {
            productItemId = intent.getExtras().getLong(EXTRA_PRODUCT_ITEM_ID);
        }

        presenter = new DiscoverDeckMapPresenter(new DiscoverDeckMapView(this), productItemId);
        initGestureDetector(presenter);
    }

    private void initGestureDetector(DiscoverDeckMapPresenter presenter) {
        gestureDetector = new GestureDetector(this,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        presenter.onTouchShipImage(new PointF(e.getX(), e.getY()));
                        return true;
                    }
                }
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDismissPopupWindow();
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
