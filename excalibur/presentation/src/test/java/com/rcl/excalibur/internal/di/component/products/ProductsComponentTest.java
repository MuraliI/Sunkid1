package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDeckMapActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Subcomponent;

@ProductsScope
@Subcomponent(modules = {ProductsServicesModule.class, ProductsDatabaseModule.class})
public interface ProductsComponentTest extends ProductsComponent {
    @Override
    ProductsListComponentTest plus(ProductsListModule module);

    @Override
    ProductsDeckMapActivityComponentTest plus(ProductsDeckMapActivityModule module);
}
