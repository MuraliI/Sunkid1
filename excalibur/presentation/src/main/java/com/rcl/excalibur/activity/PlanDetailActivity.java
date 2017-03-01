package com.rcl.excalibur.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.PlanModel;
import com.rcl.excalibur.mvp.presenter.PlanDetailPresenter;
import com.rcl.excalibur.mvp.view.PlanDetailView;

public class PlanDetailActivity extends BaseActivity {

    private static final String PLAN_EXTRA = "PLAN_EXTRA";

    public static Intent createIntent(Context context, PlanModel planModel) {
        Intent intent = new Intent(context, PlanDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(PLAN_EXTRA, planModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        PlanModel planModel = null;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PLAN_EXTRA)) {
            planModel = getIntent().getExtras().getParcelable(PLAN_EXTRA);
        }

        PlanDetailPresenter planDetailPresenter = new PlanDetailPresenter(new PlanDetailView(this), planModel);
    }
}
