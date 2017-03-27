package com.rcl.excalibur;

import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModuleTest;

public class ProductsBaseTest extends BaseTest {
    protected ProductsComponentTest productsComponent;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        productsComponent = appComponentTest.plus(new ProductsServicesModuleTest(), new ProductsDatabaseModuleTest());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        productsComponent = null;
    }
}
