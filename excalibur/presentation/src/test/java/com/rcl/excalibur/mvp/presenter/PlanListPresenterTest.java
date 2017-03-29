package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsListActivityComponentTest;
import com.rcl.excalibur.internal.di.component.products.ProductsListComponentTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsDatabaseModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsListModuleTest;
import com.rcl.excalibur.internal.di.module.products.ProductsServicesModuleTest;
import com.rcl.excalibur.mvp.view.PlanListView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import javax.inject.Inject;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PlanListPresenterTest {
    @Inject PlanListPresenter presenter;
    private AppComponentTest appComponentTest;
    private ProductsComponentTest productsComponentTest;
    private ProductsListComponentTest productsListComponentTest;
    private ProductsListActivityComponentTest productsListActivityComponentTest;

    @Before
    public void setUp() throws Exception {
        appComponentTest = DaggerAppComponentTest.builder()
                .appModule(new AppModuleTest())
                .build();
        productsComponentTest = appComponentTest.plus(new ProductsServicesModuleTest(),
                new ProductsDatabaseModuleTest());
        productsListComponentTest = productsComponentTest.plus(new ProductsListModuleTest());
        productsListActivityComponentTest = productsListComponentTest.plus(new ProductsListActivityModuleTest());
        productsListActivityComponentTest.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        productsListActivityComponentTest = null;
        productsListComponentTest = null;
        productsComponentTest = null;
        appComponentTest = null;
    }

    @Test
    public void initTest() throws Exception {
        presenter.init();
        PlanListView view = presenter.getView();
        verify(view).setAdapterObserver(Matchers.any(PlanListPresenter.AdapterObserver.class));
        verify(view).init(anyInt());
        verifyNoMoreInteractions(view);
    }
}
