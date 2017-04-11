package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.internal.di.scopes.product.ProductDetailScope;

import dagger.Module;
import dagger.Provides;

@ProductDetailScope
@Module
public class ProductDetailModule {
    private final String productId;

    public ProductDetailModule(String productId) {
        this.productId = productId;
    }

    @Provides
    String providesProductId() {
        return productId;
    }
}
