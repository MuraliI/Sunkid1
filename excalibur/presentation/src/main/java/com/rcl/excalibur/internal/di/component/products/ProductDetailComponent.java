package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.internal.di.module.products.ProductDetailActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductDetailModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailScope;

import dagger.Subcomponent;

@ProductDetailScope
@Subcomponent(modules = ProductDetailModule.class)
public interface ProductDetailComponent {
    ProductDetailActivityComponent plus(ProductDetailActivityModule module);
}
