package com.rcl.excalibur.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.adobe.mobile.Config;
import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.internal.di.component.ApplicationComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Get the Main Application component for dependency injection.
     */
    public ApplicationComponent getApplicationComponent() {
        return ((RCLApp) getApplication()).getApplicationComponent();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Config.pauseCollectingLifecycleData();
    }
}
