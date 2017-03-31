package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDeckMapActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsFragmentModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Subcomponent;

@ProductsScope
@Subcomponent(modules = {ProductsServicesModule.class, ProductsDatabaseModule.class})
public interface ProductsComponent {
    ProductsListComponent plus(ProductsListModule module);

    ProductsFragmentComponent plus(ProductsFragmentModule module);

    ProductsDeckMapActivityComponent plus(ProductsDeckMapActivityModule module);
}