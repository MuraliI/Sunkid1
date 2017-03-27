package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductDetailModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListScope;

import dagger.Subcomponent;

@ProductsListScope
@Subcomponent(modules = ProductsListModule.class)
public interface ProductsListComponent {
    ProductsListActivityComponent plus(ProductsListActivityModule module);

    ProductDetailComponent plus(ProductDetailModule module);
}
