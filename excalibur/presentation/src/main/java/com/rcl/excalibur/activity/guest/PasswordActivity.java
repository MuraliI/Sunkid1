package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.guest.GuestPasswordActivityComponent;
import com.rcl.excalibur.internal.di.module.guest.GuestPasswordActivityModule;
import com.rcl.excalibur.mvp.presenter.guest.PasswordPresenter;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class PasswordActivity extends BaseActivity<PasswordPresenter> {
    private GuestPasswordActivityComponent guestActivityComponent;

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, PasswordActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_PASSWORD);
    }

    @OnClick(R.id.image_back_screen)
    void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @Override
    protected void createComponent() {
        rclApp.createGuestComponent();
        guestActivityComponent = rclApp.getGuestComponent().plus(new GuestPasswordActivityModule(this));
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


    @OnClick(R.id.image_next_screen)
    public void onClickImageViewNext() {
        AnalyticsUtils.trackEvent(new AnalyticEvent(AnalyticsConstants.KEY_GUEST_ACCOUNT_SUBMIT_CREDENTIALS));
        presenter.onClickImageViewNext();
    }

    @OnFocusChange(R.id.edit_create_password)
    void onFocusChange(boolean hasFocus) {
        presenter.setFocus(hasFocus);
    }

    @OnCheckedChanged(R.id.checkbox_show_password)
    void onCheckChange(boolean isChecked) {
        presenter.setVisibilityPassword(isChecked);
    }

    @OnTextChanged(R.id.edit_create_password)
    void onTextPasswordChange(Editable editable) {
        presenter.verifyPassword();
    }

    @OnClick(R.id.container_layout)
    void onClickContainer() {
        presenter.hideKeyBoard();
    }

    @OnEditorAction(R.id.edit_create_password)
    boolean onEditorAction() {
        AnalyticsUtils.trackEvent(new AnalyticEvent(AnalyticsConstants.KEY_GUEST_ACCOUNT_SUBMIT_CREDENTIALS));
        if (presenter.isValidData()) {
            presenter.onClickImageViewNext();
        }
        return true;
    }
}
