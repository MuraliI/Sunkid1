package com.rcl.excalibur.internal.di.component;


import com.rcl.excalibur.internal.di.PerActivity;
import com.rcl.excalibur.internal.di.module.UserModule;
import com.rcl.excalibur.mvp.presenter.DiscoverPresenter;
import com.rcl.excalibur.mvp.presenter.LoadFromDBPresenter;

import dagger.Component;

/**
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { UserModule.class })
public interface UserComponent {
    void inject(LoadFromDBPresenter loadFromDBPresenter);

    void inject(DiscoverPresenter discoverPresenter);
}
