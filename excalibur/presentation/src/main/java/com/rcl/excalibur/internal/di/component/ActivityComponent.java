package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.internal.di.module.ActivityModule;

import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(HomeActivity activity);
}
