package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.fragments.ProductsListFragment;
import com.rcl.excalibur.internal.di.module.products.ProductsListFragmentModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListFragmentScope;

import dagger.Subcomponent;

@ProductsListFragmentScope
@Subcomponent(modules = ProductsListFragmentModule.class)
public interface ProductsListFragmentComponent {
    void inject(ProductsListFragment fragment);
}
