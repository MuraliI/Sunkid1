package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.internal.di.scopes.FragmentScope;
import com.rcl.excalibur.mvp.presenter.DiscoverTabPresenter;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class FragmentModule {
    private final BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    BaseFragment providesFragment() {
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
