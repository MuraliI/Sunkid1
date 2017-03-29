package com.rcl.excalibur;

import com.rcl.excalibur.internal.di.component.products.ProductsListComponentTest;
import com.rcl.excalibur.internal.di.module.products.ProductsListModuleTest;

public class ProductsListBaseTest extends ProductsBaseTest {
    protected ProductsListComponentTest productsListComponentTest;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        productsListComponentTest = productsComponent.plus(new ProductsListModuleTest());
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        productsListComponentTest = null;
    }
}
