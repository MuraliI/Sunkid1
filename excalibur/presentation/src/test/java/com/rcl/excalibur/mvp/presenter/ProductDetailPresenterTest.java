package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.component.detail.ProductDetailActivityComponentTest;
import com.rcl.excalibur.internal.di.component.detail.ProductDetailComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsListComponentTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.internal.di.module.detail.ProductDetailActivityModuleTest;
import com.rcl.excalibur.internal.di.module.detail.ProductDetailModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsListModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModuleTest;

import org.junit.Before;

import javax.inject.Inject;

public class ProductDetailPresenterTest {

    @Inject ProductDetailPresenter presenter;
    AppComponentTest appComponentTest;
    ProductsComponentTest productsComponentTest;
    ProductsListComponentTest productsListComponentTest;
    ProductDetailComponentTest productDetailComponentTest;
    ProductDetailActivityComponentTest productDetailActivityComponentTest;

    private static final Long PRODUCT_ID = 1L;

    @Before
    public void setUp() {
        appComponentTest = DaggerAppComponentTest.builder().appModule(new AppModuleTest()).build();
        productsComponentTest = appComponentTest.plus(new ProductsServicesModuleTest(), new ProductsDatabaseModuleTest());
        productsListComponentTest = productsComponentTest.plus(new ProductsListModuleTest());
        productDetailComponentTest = productsListComponentTest.plus(new ProductDetailModuleTest(PRODUCT_ID));
        productDetailActivityComponentTest = productDetailComponentTest.plus(new ProductDetailActivityModuleTest());
        productDetailActivityComponentTest.inject(this);
    }
}
