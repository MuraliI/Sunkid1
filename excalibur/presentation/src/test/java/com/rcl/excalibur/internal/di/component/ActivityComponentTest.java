package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.scopes.ActivityScope;
import com.rcl.excalibur.mvp.presenter.HomePresenterTest;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponentTest extends ActivityComponent {
    void inject(HomePresenterTest homePresenterTest);
}