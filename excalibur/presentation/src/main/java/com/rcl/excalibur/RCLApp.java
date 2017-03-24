package com.rcl.excalibur;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.rcl.excalibur.internal.di.component.AppComponent;
import com.rcl.excalibur.internal.di.component.DaggerAppComponent;
import com.rcl.excalibur.internal.di.component.DaggerProductsComponent;
import com.rcl.excalibur.internal.di.component.ProductsComponent;
import com.rcl.excalibur.internal.di.module.AppModule;
import com.rcl.excalibur.internal.di.module.ProductsServicesModule;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class RCLApp extends Application {
    private AppComponent appComponent;
    private ProductsComponent productsComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        this.initializeInjector();
        this.initializeLeakDetection();
        this.initCalligraphy();
        AnalyticsUtils.initializeAnalyticsTool(this.getApplicationContext());

        if (BuildConfig.DEBUG) {
            AndroidDevMetrics.initWith(this);
            Timber.plant(new Timber.DebugTree());
            Timber.d("RCL Timber is: %s", "ON");
        }
    }

    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    private void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.roboto_regular))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public void createProductsComponent() {
        productsComponent = DaggerProductsComponent.builder()
                .productsServicesModule(new ProductsServicesModule())
                .build();
    }

    public ProductsComponent getProductsComponent() {
        return productsComponent;
    }

    public void destroyProductsComponent() {
        productsComponent = null;
    }
}
