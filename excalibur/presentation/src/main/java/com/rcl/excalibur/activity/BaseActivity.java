package com.rcl.excalibur.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

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
}
