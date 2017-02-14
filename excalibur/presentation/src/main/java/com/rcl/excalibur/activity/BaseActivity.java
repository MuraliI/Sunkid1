package com.rcl.excalibur.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rcl.excalibur.internal.di.component.ApplicationComponent;
import com.rcl.excalibur.RCLApp;

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

}
