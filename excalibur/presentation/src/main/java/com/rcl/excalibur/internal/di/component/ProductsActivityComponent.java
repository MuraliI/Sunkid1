package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.internal.di.module.ProductsActivityModule;
import com.rcl.excalibur.internal.di.scopes.ProductsActivityScope;

import dagger.Subcomponent;

@ProductsActivityScope
@Subcomponent(modules = ProductsActivityModule.class)
public interface ProductsActivityComponent {
    void inject(TriptychHomeActivity activity);
}
