package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.activity.ProductDetailActivity;
import com.rcl.excalibur.internal.di.module.products.ProductDetailActivityModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailActivityScope;

import dagger.Subcomponent;

@ProductDetailActivityScope
@Subcomponent(modules = ProductDetailActivityModule.class)
public interface ProductDetailActivityComponent {
    void inject(ProductDetailActivity activity);
}
