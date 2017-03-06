package com.rcl.excalibur.activity;


import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.mvp.presenter.PlanListPresenter;
import com.rcl.excalibur.mvp.view.PlanListView;

public class PlanListActivity extends BaseActivity {

    private PlanListPresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, PlanListActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);
        presenter = new PlanListPresenter(new PlanListView(this));
    }

}
