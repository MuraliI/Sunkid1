package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.mvp.presenter.guest.EmailPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;

public class EmailActivity extends BaseActivity<EmailPresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.image_back_screen)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @OnFocusChange(R.id.edit_email)
    void onFocusChange(boolean hasFocus) {
        presenter.setFocus(hasFocus);
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, EmailActivity.class);
    }

    @OnEditorAction(R.id.edit_email)
    boolean onEditorAction() {
        presenter.verifyEmail();
        return true;
    }
}
