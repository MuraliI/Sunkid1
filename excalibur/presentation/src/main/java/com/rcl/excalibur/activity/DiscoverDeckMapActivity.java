package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DiscoverDeckMapPresenter;
import com.rcl.excalibur.mvp.view.DiscoverDeckMapView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverDeckMapActivity extends BaseActivity {
    private static final String EXTRA_DISCOVER_ITEM_ID = "EXTRA_DISCOVER_ITEM_ID";

    private DiscoverDeckMapPresenter presenter;

    public static Intent getIntent(final BaseActivity activity, String discoverItemId) {
        Intent intent = new Intent(activity, DiscoverDeckMapActivity.class);
        intent.putExtra(EXTRA_DISCOVER_ITEM_ID, discoverItemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_deck_map);
        ButterKnife.bind(this);

        String discoverItemId = null;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DISCOVER_ITEM_ID)) {
            discoverItemId = intent.getExtras().getString(EXTRA_DISCOVER_ITEM_ID);
        }

        presenter = new DiscoverDeckMapPresenter(new DiscoverDeckMapView(this), discoverItemId);
    }

    @OnClick(R.id.back_arrow)
    void onBackClicked(View view) {
        presenter.onBackClicked();
    }
}
