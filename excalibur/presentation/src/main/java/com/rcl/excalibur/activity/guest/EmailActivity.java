package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.guest.GuestEmailActivityComponent;
import com.rcl.excalibur.internal.di.module.guest.GuestEmailActivityModule;
import com.rcl.excalibur.mvp.presenter.guest.EmailPresenter;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class EmailActivity extends BaseActivity<EmailPresenter> {
    private GuestEmailActivityComponent guestActivityComponent;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, EmailActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_EMAIL_SCREEN);
    }

    @OnClick(R.id.image_back_screen)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @OnFocusChange(R.id.edit_email)
    void onFocusChange(boolean hasFocus) {
        presenter.setFocus(hasFocus);
    }

    @OnTextChanged(value = R.id.edit_email, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterEmailInput() {
        presenter.verifyEmail();
    }


    @OnEditorAction(R.id.edit_email)
    boolean onEditorAction() {
        presenter.checkDone();
        return true;
    }

    @OnClick(R.id.image_next_screen)
    public void onClickImageViewNext() {
        presenter.checkDone();
    }

    @Override
    protected void createComponent() {
        rclApp.createGuestComponent();
        guestActivityComponent = rclApp.getGuestComponent().plus(new GuestEmailActivityModule(this));
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

}
