package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.fragments.DiscoverTabFragment;
import com.rcl.excalibur.internal.di.module.FragmentModule;
import com.rcl.excalibur.internal.di.scopes.FragmentScope;

import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(DiscoverTabFragment fragment);
}
