package com.rcl.excalibur;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.frogermcs.androiddevmetrics.AndroidDevMetrics;
import com.rcl.excalibur.internal.di.component.AppComponent;
import com.rcl.excalibur.internal.di.component.DaggerAppComponent;
import com.rcl.excalibur.internal.di.component.guest.GuestComponent;
import com.rcl.excalibur.internal.di.component.itinerary.ItineraryComponent;
import com.rcl.excalibur.internal.di.component.products.ProductDetailComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsListComponent;
import com.rcl.excalibur.internal.di.module.AppModule;
import com.rcl.excalibur.internal.di.module.guest.GuestModule;
import com.rcl.excalibur.internal.di.module.guest.GuestServicesModule;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryModule;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryServicesModule;
import com.rcl.excalibur.internal.di.module.products.ProductDetailModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class RCLApp extends Application {
    private AppComponent appComponent;
    private ProductsComponent productsComponent;
    private ItineraryComponent itineraryComponent;
    private ProductsListComponent productsListComponent;
    private ProductDetailComponent productDetailComponent;
    private GuestComponent guestComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        this.initializeInjector();
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

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public void createProductsComponent() {
        productsComponent = appComponent.plus(new ProductsServicesModule(), new ProductsDatabaseModule());
    }

    public ProductsComponent getProductsComponent() {
        return productsComponent;
    }

    public void destroyProductsComponent() {
        productsComponent = null;
    }

    public void createProductListComponent(int fragmentToShow) {
        productsListComponent = productsComponent.plus(new ProductsListModule(fragmentToShow));
    }

    public void createItineraryComponent() {
        itineraryComponent = appComponent.plus(new ItineraryServicesModule(), new ItineraryModule());
    }

    public ItineraryComponent getItineraryComponent() {
        return itineraryComponent;
    }

    public void destroyItineraryComponent() {
        itineraryComponent = null;
    }

    public ProductsListComponent getProductsListComponent() {
        return productsListComponent;
    }

    public void destroyProductsListComponent() {
        productsListComponent = null;
    }

    public void createProductDetailComponent(long productId) {
        productDetailComponent = productsListComponent.plus(new ProductDetailModule(productId));
    }

    public ProductDetailComponent getProductDetailComponent() {
        return productDetailComponent;
    }

    public void destroyProductDetailComponent() {
        productDetailComponent = null;
    }

    public void createGuestComponent() {
        guestComponent = appComponent.plus(new GuestServicesModule(getApplicationContext()), new GuestModule());
    }

    public GuestComponent getGuestComponent() {
        return guestComponent;
    }

    public void destroyGuestComponent() {
        guestComponent = null;
    }
}
