package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductDetailModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListScope;

import dagger.Subcomponent;

@ProductsListScope
@Subcomponent(modules = ProductsListModule.class)
public interface ProductsListComponentTest extends ProductsListComponent {
    @Override
    ProductsListActivityComponentTest plus(ProductsListActivityModule module);

    @Override
    ProductDetailComponent plus(ProductDetailModule module);
}
