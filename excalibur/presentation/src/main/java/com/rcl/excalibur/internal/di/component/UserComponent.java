package com.rcl.excalibur.internal.di.component;


import com.rcl.excalibur.deckmap.mvp.presenter.DiscoverDeckMapPresenter;
import com.rcl.excalibur.internal.di.module.UserModule;
import com.rcl.excalibur.internal.di.scopes.PerActivity;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.presenter.DiscoverItemListPresenter;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;

import dagger.Component;

/**
 * Injects user specific Fragments.
 */
@PerActivity
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(DiscoverItemListPresenter discoverItemListPresenter);

    void inject(DiscoverItemDetailPresenter discoverItemDetailPresenter);

    void inject(HomePresenter presenter);

    void inject(TriptychHomePresenter presenter);

    void inject(DiscoverDeckMapPresenter presenter);
}
