package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.ProductsBaseTest;
import com.rcl.excalibur.internal.di.component.products.ProductsFragmentComponentTest;
import com.rcl.excalibur.internal.di.module.products.ProductsFragmentModuleTest;

import org.junit.Test;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;


public class DiscoverTabPresenterTest extends ProductsBaseTest {

    @Inject DiscoverTabPresenter presenter;
    ProductsFragmentComponentTest productsFragmentComponentTest;


    @Override
    public void setUp() throws Exception {
        super.setUp();
        productsFragmentComponentTest = productsComponent.plus(new ProductsFragmentModuleTest());
        productsFragmentComponentTest.inject(this);
    }


    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        productsFragmentComponentTest = null;
    }

    @Test
    public void openListScreen() {

        int fragmentToShow = 0;
        presenter.openListScreen(fragmentToShow);

        verify(presenter.getView()).openListScreen(fragmentToShow);

    }

}