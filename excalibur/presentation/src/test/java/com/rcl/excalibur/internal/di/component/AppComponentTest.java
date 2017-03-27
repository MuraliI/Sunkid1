package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.module.AppModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponentTest extends AppComponent {
    @Override
    ActivityComponentTest plus(ActivityModule activityModuleTest);

    @Override
    ProductsComponentTest plus(ProductsServicesModule servicesModule, ProductsDatabaseModule productsDatabaseModule);
}
