package com.rcl.excalibur.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity<P extends ActivityPresenter> extends AppCompatActivity {
    @Inject
    protected P presenter;
    protected RCLApp rclApp;
    private ActivityComponent activityComponent;

    protected void createComponent() {
        activityComponent = rclApp.getAppComponent().plus(new ActivityModule(this));
    }

    protected void destroyComponent() {
        activityComponent = null;
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rclApp = ((RCLApp) getApplication());
    }

    @CallSuper
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        createComponent();
        injectActivity(activityComponent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsUtils.startAnalytics(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AnalyticsUtils.stopAnalytics();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyComponent();
    }

    protected abstract void injectActivity(ActivityComponent activityComponent);
}
