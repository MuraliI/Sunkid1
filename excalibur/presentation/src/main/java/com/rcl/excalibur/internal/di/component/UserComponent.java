package com.rcl.excalibur.internal.di.component;


import com.rcl.excalibur.internal.di.PerActivity;
import com.rcl.excalibur.internal.di.module.UserModule;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.presenter.DiscoverItemListPresenter;
import com.rcl.excalibur.mvp.presenter.HomePresenter;

import dagger.Component;

/**
 * Injects user specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {UserModule.class})
public interface UserComponent {
    void inject(DiscoverItemListPresenter discoverItemListPresenter);

    void inject(DiscoverItemDetailPresenter discoverItemDetailPresenter);

    void inject(HomePresenter presenter);

}
