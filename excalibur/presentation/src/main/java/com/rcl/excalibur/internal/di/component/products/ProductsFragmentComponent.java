package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.internal.di.module.products.ProductsFragmentModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsFragmentScope;

import dagger.Subcomponent;

@ProductsFragmentScope
@Subcomponent(modules = ProductsFragmentModule.class)
public interface ProductsFragmentComponent {
    void inject(DiscoverTabFragment fragment);
}
