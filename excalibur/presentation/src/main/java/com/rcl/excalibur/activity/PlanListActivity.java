package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;
import com.rcl.excalibur.mvp.view.PlanListView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanListActivity extends BaseActivity {
    private static final String EXTRA_FRAGMENT_TYPE = "EXTRA_FRAGMENT_TYPE";
    protected PlanListPresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity, int fragmentToShow) {
        Intent intent = new Intent(activity, PlanListActivity.class);
        intent.putExtra(PlanListActivity.EXTRA_FRAGMENT_TYPE, fragmentToShow);

        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_FRAGMENT_TYPE)) {
            return;

        }
        final int fragmentToShow = intent.getIntExtra(EXTRA_FRAGMENT_TYPE, 0);
        presenter = new PlanListPresenter(new PlanListView(this));
        presenter.init(fragmentToShow);
    }

    @OnClick(R.id.plans_header_back)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }
}
