package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.component.itinerary.ItineraryComponent;
import com.rcl.excalibur.internal.di.component.products.ProductsComponent;
import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.module.AppModule;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryModule;
import com.rcl.excalibur.internal.di.module.itinerary.ItineraryServicesModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;
import com.rcl.excalibur.internal.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    ProductsComponent plus(ProductsServicesModule servicesModule, ProductsDatabaseModule productsDatabaseModule);

    ItineraryComponent plus(ItineraryServicesModule itineraryServicesModule, ItineraryModule itineraryModule);

    ActivityComponent plus(ActivityModule module);
}