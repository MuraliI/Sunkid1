package com.rcl.excalibur.internal.di.component.products;


import com.rcl.excalibur.activity.ProductDeckMapActivity;
import com.rcl.excalibur.internal.di.module.products.ProductsDeckMapActivityModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDeckMapActivityScope;

import dagger.Subcomponent;

@ProductDeckMapActivityScope
@Subcomponent(modules = ProductsDeckMapActivityModule.class)
public interface ProductsDeckMapActivityComponent {
    void inject(ProductDeckMapActivity activity);
}
