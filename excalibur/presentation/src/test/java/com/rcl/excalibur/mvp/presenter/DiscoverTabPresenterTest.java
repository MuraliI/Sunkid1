package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsFragmentComponentTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsFragmentModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModuleTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

public class DiscoverTabPresenterTest {

    @Inject DiscoverTabPresenter presenter;
    ProductsComponentTest productsComponentTest;
    ProductsFragmentComponentTest productsFragmentComponentTest;
    AppComponentTest appComponentTest;

    @Before
    public void setUp() throws Exception {

        appComponentTest = DaggerAppComponentTest.builder().appModule(new AppModuleTest()).build();
        productsComponentTest = appComponentTest.plus(new ProductsServicesModuleTest(), new ProductsDatabaseModuleTest());
        productsFragmentComponentTest = productsComponentTest.plus(new ProductsFragmentModuleTest());
        productsFragmentComponentTest.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        appComponentTest = null;
        productsComponentTest = null;
        productsFragmentComponentTest = null;
    }

    @Test
    public void openListScreen() {
        int fragmentToShow = 0;
        presenter.openListScreen(fragmentToShow);

        verify(presenter.getView()).openListScreen(fragmentToShow);
    }

}