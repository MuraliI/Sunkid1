package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.ProductDeckMapView;

import static org.mockito.Mockito.mock;

public class ProductsDeckMapActivityModuleTest extends ProductsDeckMapActivityModule {
    public ProductsDeckMapActivityModuleTest() {
        super(mock(BaseActivity.class));
    }

    @Override
    ProductDeckMapView providesProductDeckMapView(BaseActivity activity) {
        return mock(ProductDeckMapView.class);
    }
}
