package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.deckmap.activity.DiscoverDeckMapActivity;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;
import com.rcl.excalibur.mvp.view.PlanListView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class PlanListActivity extends BaseActivity {

    private PlanListPresenter presenter;
    public static final String EXTRA_FRAGMENT_TYPE = "EXTRA_FRAGMENT_TYPE";

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, PlanListActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        ButterKnife.bind(this);

        int fragmentToShow = 0;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_FRAGMENT_TYPE)) {
            fragmentToShow = intent.getExtras().getInt(EXTRA_FRAGMENT_TYPE);
        }

        presenter = new PlanListPresenter(new PlanListView(this), fragmentToShow);
    }


    @OnClick(R.id.plans_header_back_layout)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    private static final long ACTIVITIES_TEMP_ID = 100000002783668857L;
    private static final long DINING_TEMP_ID = 100000002903890058L;

    @OnClick(R.id.plan_list_filter)
    public void onFilterClick() {
        Timber.i("onFilterClick");
        startActivity(DiscoverDeckMapActivity.getIntent(this, DINING_TEMP_ID));
    }
}
