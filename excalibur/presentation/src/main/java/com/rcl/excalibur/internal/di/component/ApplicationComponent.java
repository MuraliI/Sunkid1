package com.rcl.excalibur.internal.di.component;

import android.content.Context;

import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;
import com.rcl.excalibur.domain.repository.ItemRepository;
import com.rcl.excalibur.domain.service.DiscoveryService;
import com.rcl.excalibur.internal.di.module.ApplicationModule;
import com.rcl.excalibur.mvp.presenter.DiscoverItemDetailPresenter;
import com.rcl.excalibur.mvp.presenter.DiscoverItemListPresenter;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.presenter.LoadFromDBPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(LoadFromDBPresenter presenter);

    void inject(DiscoverItemListPresenter presenter);

    void inject(DiscoverItemDetailPresenter presenter);

    void inject(HomePresenter presenter);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    ItemRepository itemRepository();

    DiscoverItemRepository discoverItemRepository();

    DiscoveryService discoveryService();
}
