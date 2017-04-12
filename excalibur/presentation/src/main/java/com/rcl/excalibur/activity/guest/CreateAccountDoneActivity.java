package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.presenter.guest.CreateAccountDonePresenter;
import com.rcl.excalibur.mvp.view.guest.CreateAccountDoneView;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateAccountDoneActivity extends BaseActivity {
    protected CreateAccountDonePresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, CreateAccountDoneActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_done);
        ButterKnife.bind(this);
        presenter = new CreateAccountDonePresenter(new CreateAccountDoneView(this));
    }

    @OnClick(R.id.create_account_done_layout)
    public void onActivityClick() {
        AnalyticsUtils.trackEvent(new AnalyticEvent(AnalyticsConstants.KEY_GUEST_ACCOUNT_CREATE_ACCOUNT_SUBMIT));
        presenter.onActivityClick();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
