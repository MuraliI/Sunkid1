package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverItemDetailActivity extends BaseActivity {

    private static final String EXTRA_DISCOVER_ITEM_ID = "EXTRA_DISCOVER_ITEM_ID";

    private DiscoverItemDetailPresenter presenter;

    public static Intent getIntent(final BaseActivity activity, String discoverItemId) {
        Intent intent = new Intent(activity, DiscoverItemDetailActivity.class);
        intent.putExtra(EXTRA_DISCOVER_ITEM_ID, discoverItemId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_item_detail);
        ButterKnife.bind(this);

        String discoverItemId = null;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DISCOVER_ITEM_ID)) {
            discoverItemId = getIntent().getExtras().getString(EXTRA_DISCOVER_ITEM_ID);
        }

        presenter = new DiscoverItemDetailPresenter(new DiscoverItemDetailView(this), discoverItemId);
    }

    @OnClick(R.id.back_arrow)
    void onBackClicked(View view) {
        presenter.onBackClicked();
    }

}
