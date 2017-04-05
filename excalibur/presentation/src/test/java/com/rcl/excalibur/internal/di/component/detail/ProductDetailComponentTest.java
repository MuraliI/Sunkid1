package com.rcl.excalibur.internal.di.component.detail;

import com.rcl.excalibur.internal.di.component.products.ProductDetailComponent;
import com.rcl.excalibur.internal.di.module.products.ProductDetailActivityModule;
import com.rcl.excalibur.internal.di.module.products.ProductDetailModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailActivityScope;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailScope;

import dagger.Subcomponent;

@ProductDetailScope
@Subcomponent(modules = ProductDetailModule.class)
public interface ProductDetailComponentTest extends ProductDetailComponent {
    @Override
    ProductDetailActivityComponentTest plus(ProductDetailActivityModule productDetailPresenterTest);
}
