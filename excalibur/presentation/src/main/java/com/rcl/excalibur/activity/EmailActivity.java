package com.rcl.excalibur.activity;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.mvp.presenter.EmailPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends BaseActivity<EmailPresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        presenter.init();
    }

    @OnClick(R.id.imageViewBack)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }


    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, EmailActivity.class);
    }

}
