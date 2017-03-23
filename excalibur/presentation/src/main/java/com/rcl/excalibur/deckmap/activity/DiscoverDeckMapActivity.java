package com.rcl.excalibur.deckmap.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.deckmap.mvp.presenter.DiscoverDeckMapPresenter;
import com.rcl.excalibur.deckmap.mvp.view.DiscoverDeckMapView;

import butterknife.ButterKnife;

public class DiscoverDeckMapActivity extends BaseActivity {
    private static final String EXTRA_PRODUCT_ITEM_ID = "EXTRA_PRODUCT_ITEM_ID";

    private DiscoverDeckMapPresenter presenter;

    // TODO: Pending call from item detail
    public static Intent getIntent(final BaseActivity activity, long productItemId) {
        Intent intent = new Intent(activity, DiscoverDeckMapActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ITEM_ID, productItemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_deck_map);
        ButterKnife.bind(this);

        long productItemId = 0L;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PRODUCT_ITEM_ID)) {
            productItemId = intent.getExtras().getLong(EXTRA_PRODUCT_ITEM_ID);
        }

        presenter = new DiscoverDeckMapPresenter(new DiscoverDeckMapView(this), productItemId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
