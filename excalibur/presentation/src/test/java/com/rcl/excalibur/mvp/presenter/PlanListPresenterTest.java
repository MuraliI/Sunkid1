package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.ProductsListBaseTest;
import com.rcl.excalibur.internal.di.component.products.ProductsListActivityComponentTest;
import com.rcl.excalibur.internal.di.module.products.ProductsListActivityModuleTest;
import com.rcl.excalibur.mvp.view.PlanListView;

import org.junit.Test;
import org.mockito.Matchers;

import javax.inject.Inject;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PlanListPresenterTest extends ProductsListBaseTest {
    @Inject
    PlanListPresenter presenter;
    private ProductsListActivityComponentTest activityComponentTest;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activityComponentTest = productsListComponentTest.plus(new ProductsListActivityModuleTest());
        activityComponentTest.inject(this);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
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
