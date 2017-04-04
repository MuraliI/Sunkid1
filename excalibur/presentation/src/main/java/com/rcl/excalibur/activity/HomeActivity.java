package com.rcl.excalibur.activity;


import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.mvp.presenter.guest.HomePresenter;

public class HomeActivity extends BaseActivity<HomePresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
