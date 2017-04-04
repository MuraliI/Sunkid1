package com.rcl.excalibur.activity.guest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.mvp.presenter.guest.PasswordPresenter;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

public class PasswordActivity extends BaseActivity<PasswordPresenter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.imageViewBack)
    void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @OnFocusChange(R.id.editTextPassword)
    void onFocusChange(boolean hasFocus) {
        presenter.setFocus(hasFocus);
    }

    @OnCheckedChanged(R.id.checkboxShowPassword)
    void onCheckChange(boolean isChecked) {
        presenter.setVisibilityPassword(isChecked);
    }

    @OnTextChanged(R.id.editTextPassword)
    void onTextPasswordChange(Editable editable) {
        presenter.verifyPassword();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
