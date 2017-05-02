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
import com.rcl.excalibur.data.repository.ProductDataRepository;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.presenter.DeckMapPresenter;
import com.rcl.excalibur.mvp.view.DeckMapView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeckMapActivity extends BaseActivity implements View.OnTouchListener,
        ViewTreeObserver.OnGlobalLayoutListener {
    private static final String EXTRA_PRODUCT_ITEM_ID = "EXTRA_PRODUCT_ITEM_ID";


    protected DeckMapPresenter presenter;
    private GestureDetector gestureDetector;

    public static Intent getIntent(final BaseActivity activity, String productItemId) {
        Intent intent = new Intent(activity, DeckMapActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ITEM_ID, productItemId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_deck_map);

        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_PRODUCT_ITEM_ID)) {
            return;
        }
        final String productItemId = intent.getStringExtra(EXTRA_PRODUCT_ITEM_ID);
        presenter = new DeckMapPresenter(new DeckMapView(this),
                new GetProductDbUseCase(new ProductDataRepository()));
        presenter.init(productItemId);
        initGestureDetector(presenter);

        ButterKnife.bind(this);
    }

    // Cannot be passed to View because is very expensive to implement
    private void initGestureDetector(DeckMapPresenter presenter) {
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

    @OnClick(R.id.button_close_deck_map)
    public void onClosedButtonClicked(View view) {
        presenter.onCloseClicked();
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
