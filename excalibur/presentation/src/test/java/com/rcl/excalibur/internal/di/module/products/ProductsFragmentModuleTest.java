package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import org.mockito.Mockito;


public class ProductsFragmentModuleTest extends ProductsFragmentModule {

    public ProductsFragmentModuleTest() {

        super(Mockito.mock(BaseFragment.class));
    }

    @Override
    protected DiscoverTabView providesDiscoverTabView(BaseFragment fragment) {
        return Mockito.mock(DiscoverTabView.class);
    }
}
