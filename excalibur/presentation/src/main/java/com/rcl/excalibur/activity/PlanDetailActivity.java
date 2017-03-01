package com.rcl.excalibur.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.PlanModel;
import com.rcl.excalibur.mvp.presenter.PlanDetailPresenter;
import com.rcl.excalibur.mvp.view.PlanDetailView;

public class PlanDetailActivity extends BaseActivity {

    private static final String EXTRA_PLAN_MODEL = "EXTRA_PLAN_MODEL";

    public static Intent createIntent(Context context, PlanModel planModel) {
        Intent intent = new Intent(context, PlanDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(EXTRA_PLAN_MODEL, planModel);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_detail);

        PlanModel planModel = null;
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_PLAN_MODEL)) {
            planModel = getIntent().getExtras().getParcelable(EXTRA_PLAN_MODEL);
        }

        PlanDetailPresenter planDetailPresenter = new PlanDetailPresenter(new PlanDetailView(this), planModel);
    }
}
