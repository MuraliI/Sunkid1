package com.rcl.excalibur.activity.guest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.guest.GuestActivityComponent;
import com.rcl.excalibur.internal.di.module.guest.GuestActivityModule;
import com.rcl.excalibur.mvp.presenter.guest.SecurityQuestionsPresenter;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

public class SecurityQuestionsActivity extends BaseActivity<SecurityQuestionsPresenter> {
    private GuestActivityComponent guestActivityComponent;

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, SecurityQuestionsActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_questions);
        presenter.init();
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_SECURITY_QUESTION);
    }

    @Override
    protected void createComponent() {
        rclApp.createGuestComponent();
        guestActivityComponent = rclApp.getGuestComponent().plus(new GuestActivityModule(this));
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        guestActivityComponent.inject(this);
    }

    @Override
    protected void destroyComponent() {
        guestActivityComponent = null;
        rclApp.destroyGuestComponent();
    }
}
