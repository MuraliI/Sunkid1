package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.guest.GuestNameActivityComponent;
import com.rcl.excalibur.internal.di.module.guest.GuestNameActivityModule;
import com.rcl.excalibur.mvp.presenter.guest.NamePresenter;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;


public class NameActivity extends BaseActivity<NamePresenter> {
    private GuestNameActivityComponent guestActivityComponent;

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, NameActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ButterKnife.bind(this);
        presenter.init();
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_NAME);
    }

    @Override
    protected void createComponent() {
        rclApp.createGuestComponent();
        guestActivityComponent = rclApp.getGuestComponent().plus(new GuestNameActivityModule(this));
    }

    @Override
    protected void destroyComponent() {
        guestActivityComponent = null;
        rclApp.destroyGuestComponent();
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        guestActivityComponent.inject(this);
    }


    @OnClick(R.id.arrow_back)
    public void onArrowBackClick() {
        presenter.onArrowBack();
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick() {
        presenter.onNextClick();
    }

    @OnTextChanged(R.id.full_name)
    public void onNameChanged() {
        presenter.onNameChanged();
    }

    @OnClick(R.id.container_layout)
    public void onContainerClick() {
        presenter.hideKeyboard();
    }

    @OnEditorAction(R.id.full_name)
    boolean onEditorAction() {
        presenter.onNextClick();
        return true;
    }

}
