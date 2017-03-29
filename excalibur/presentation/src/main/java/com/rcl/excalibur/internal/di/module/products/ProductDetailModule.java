package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.internal.di.scopes.product.ProductDetailScope;

import dagger.Module;
import dagger.Provides;

@ProductDetailScope
@Module
public class ProductDetailModule {
    private final long productId;

    public ProductDetailModule(long productId) {
        this.productId = productId;
    }

    @Provides
    long providesProductId() {
        return productId;
    }
}
