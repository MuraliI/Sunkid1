package com.rcl.excalibur.internal.di.component;

import android.content.Context;

import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverRepository;
import com.rcl.excalibur.domain.repository.ItemRepository;
import com.rcl.excalibur.internal.di.module.ApplicationModule;
import com.rcl.excalibur.mvp.presenter.DiscoverPresenter;
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

    void inject(DiscoverPresenter presenter);

    //Exposed to sub-graphs.
    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    ItemRepository itemRepository();

    DiscoverRepository discoverRepository();
}
