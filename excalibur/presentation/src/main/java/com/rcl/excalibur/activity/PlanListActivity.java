package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;
import com.rcl.excalibur.mvp.view.PlanListView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlanListActivity extends BaseActivity {

    private PlanListPresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, PlanListActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        ButterKnife.bind(this);
        presenter = new PlanListPresenter(new PlanListView(this));
    }


    @OnClick(R.id.plans_header_back)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

}