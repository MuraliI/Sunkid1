package com.rcl.excalibur.internal.di.module.detail;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.module.products.ProductDetailActivityModule;
import com.rcl.excalibur.internal.di.scopes.product.ProductDetailActivityScope;
import com.rcl.excalibur.mvp.view.ProductDetailView;

import org.mockito.Mockito;

import dagger.Module;

public class ProductDetailActivityModuleTest extends ProductDetailActivityModule {
    public ProductDetailActivityModuleTest() {
        super(Mockito.mock(BaseActivity.class));
    }

    @Override
    protected ProductDetailView providesProductDetailView(BaseActivity activity) {
        return Mockito.mock(ProductDetailView.class);
    }


}
