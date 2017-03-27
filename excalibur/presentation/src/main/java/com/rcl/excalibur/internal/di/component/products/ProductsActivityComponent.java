package com.rcl.excalibur.internal.di.component.products;

import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.internal.di.module.products.ProductsActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductsFragmentModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductsActivityScope;

import dagger.Subcomponent;

@ProductsActivityScope
@Subcomponent(modules = ProductsActivityModule.class)
public interface ProductsActivityComponent {
    ProductsFragmentComponent plus(ProductsFragmentModule module);

    void inject(TriptychHomeActivity activity);
}
