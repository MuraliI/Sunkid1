package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsListFragmentModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsListActivityScope;

import dagger.Subcomponent;

@ProductsListActivityScope
@Subcomponent(modules = ProductsListActivityModule.class)
public interface ProductsListActivityComponent {
    void inject(PlanListActivity activity);

    ProductsListFragmentComponent plus(ProductsListFragmentModule module);
}
