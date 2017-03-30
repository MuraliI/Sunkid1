package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.PlanListView;

import static org.mockito.Mockito.mock;

public class ProductsListActivityModuleTest extends ProductsListActivityModule {

    public ProductsListActivityModuleTest() {
        super(mock(BaseActivity.class));
    }

    @Override
    PlanListView providesPlanListView(BaseActivity baseActivity) {
        return mock(PlanListView.class);
    }
}
