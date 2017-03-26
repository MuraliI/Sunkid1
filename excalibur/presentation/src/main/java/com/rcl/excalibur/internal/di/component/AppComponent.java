package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.component.products.ProductsComponent;
import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.module.AppModule;
import com.rcl.excalibur.internal.di.module.FragmentModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;
import com.rcl.excalibur.internal.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    ProductsComponent plus(ProductsServicesModule module);

    ActivityComponent plus(ActivityModule module);

    FragmentComponent plus(FragmentModule module);
}
