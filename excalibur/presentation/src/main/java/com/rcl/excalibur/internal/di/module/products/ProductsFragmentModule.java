package com.rcl.excalibur.internal.di.module.products;

import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.internal.di.scopes.product.ProductsFragmentScope;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import dagger.Module;
import dagger.Provides;

@ProductsFragmentScope
@Module
public class ProductsFragmentModule {
    private BaseFragment fragment;

    public ProductsFragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    BaseFragment providesBaseFragment() {
        return fragment;
    }

    @Provides
    DiscoverTabView providesDiscoverTabView(BaseFragment fragment) {
        return new DiscoverTabView(((DiscoverTabFragment) fragment));
    }

    @Provides
    DiscoverTabPresenter providesDiscoverTabPresenter(DiscoverTabView discoverTabView) {
        return new DiscoverTabPresenter(discoverTabView);
    }
}
