package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

public class DiscoverItemDetailActivity extends BaseActivity {

    private static final String EXTRA_DISCOVER_ITEM_ID = "EXTRA_DISCOVER_ITEM_ID";

    public static Intent getIntent(final BaseActivity activity, String discoverItemId) {
        Intent intent = new Intent(activity, DiscoverItemDetailActivity.class);
        intent.putExtra(EXTRA_DISCOVER_ITEM_ID, discoverItemId);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_item_detail);

        String discoverItemId = null;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DISCOVER_ITEM_ID)) {
            discoverItemId = getIntent().getExtras().getString(EXTRA_DISCOVER_ITEM_ID);
        }

        DiscoverItemDetailPresenter presenter = new DiscoverItemDetailPresenter(new DiscoverItemDetailView(this), discoverItemId);
    }


}
