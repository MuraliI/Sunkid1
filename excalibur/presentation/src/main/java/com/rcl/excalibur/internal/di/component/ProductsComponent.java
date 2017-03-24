package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.module.ProductsActivityModule;
import com.rcl.excalibur.internal.di.module.ProductsServicesModule;
import com.rcl.excalibur.internal.di.scopes.ProductsScope;

import dagger.Component;

@ProductsScope
@Component(modules = ProductsServicesModule.class)
public interface ProductsComponent {
    ProductsActivityComponent plus(ProductsActivityModule module);
}
