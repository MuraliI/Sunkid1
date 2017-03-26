package com.rcl.excalibur.internal.di.component;


import com.rcl.excalibur.deckmap.mvp.presenter.DiscoverDeckMapPresenter;
import com.rcl.excalibur.internal.di.module.UserModule;
import com.rcl.excalibur.internal.di.scopes.ActivityScope;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.presenter.ProductsListPresenter;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;

import dagger.Component;

/**
 * Injects user specific Fragments.
 */
@ActivityScope
@Component(modules = {UserModule.class})
public interface UserComponent {
    void inject(ProductsListPresenter productsListPresenter);

    void inject(DiscoverItemDetailPresenter discoverItemDetailPresenter);

    void inject(HomePresenter presenter);

    void inject(TriptychHomePresenter presenter);

    void inject(DiscoverDeckMapPresenter presenter);
}
