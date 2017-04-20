package com.rcl.excalibur;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.rcl.excalibur.scheduler.SchedulerManager;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class RCLApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        this.initCalligraphy();
        AnalyticsUtils.initializeAnalyticsTool(this.getApplicationContext());
        SchedulerManager.init(getApplicationContext());
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Timber.d("RCL Timber is: %s", "ON");
        }
    }

    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.roboto_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SchedulerManager.get().stop();
        ActiveAndroid.dispose();
    }

}
