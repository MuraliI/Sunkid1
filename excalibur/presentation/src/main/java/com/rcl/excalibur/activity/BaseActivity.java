package com.rcl.excalibur.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.AppComponent;
import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity<P extends ActivityPresenter> extends AppCompatActivity {
    @Inject
    protected P presenter;
    private ActivityComponent activityComponent;

    protected void createActivityComponent() {
        activityComponent = getAppComponent().plus(new ActivityModule(this));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        createActivityComponent();
        injectActivity(activityComponent);
    }

    public AppComponent getAppComponent() {
        return ((RCLApp) getApplication()).getAppComponent();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityComponent = null;
    }

    protected abstract void injectActivity(ActivityComponent activityComponent);
}
