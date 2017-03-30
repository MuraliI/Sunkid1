package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.fragments.BaseFragment;
import com.rcl.excalibur.internal.di.scopes.FragmentScope;

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
}
