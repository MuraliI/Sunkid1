package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.DiscoverItemModel;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

public class DiscoverItemDetailActivity extends BaseActivity {

    private static final String EXTRA_DISCOVER_ITEM_MODEL = "EXTRA_DISCOVER_ITEM_MODEL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        DiscoverItemModel discoverItemModel = null;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_DISCOVER_ITEM_MODEL)) {
            discoverItemModel = getIntent().getExtras().getParcelable(EXTRA_DISCOVER_ITEM_MODEL);
        }

        DiscoverItemDetailPresenter presenter = new DiscoverItemDetailPresenter(new DiscoverItemDetailView(this), discoverItemModel);
    }
}
