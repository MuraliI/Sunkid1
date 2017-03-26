package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductsActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModule;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModule;
import com.rcl.excalibur.internal.di.module.products.lists.ProductsListModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsScope;

import dagger.Subcomponent;

@ProductsScope
@Subcomponent(modules = {ProductsServicesModule.class, ProductsDatabaseModule.class})
public interface ProductsComponent {
    ProductsListComponent plus(ProductsListModule module);

    ProductsActivityComponent plus(ProductsActivityModule module);
}
