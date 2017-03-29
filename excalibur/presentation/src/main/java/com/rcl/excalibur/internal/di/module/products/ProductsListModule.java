package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.internal.di.scopes.product.ProductsListScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@ProductsListScope
@Module
public class ProductsListModule {
    static final String POSITION = "position";
    private int fragmentToShow;

    public ProductsListModule(int fragmentToShow) {
        this.fragmentToShow = fragmentToShow;
    }

    @Provides
    @Named(POSITION)
    int provideProductsType() {
        return fragmentToShow;
    }
}
