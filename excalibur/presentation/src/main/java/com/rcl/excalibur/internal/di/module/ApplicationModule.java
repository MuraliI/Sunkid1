package com.rcl.excalibur.internal.di.module;

import android.content.Context;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.UIThread;
import com.rcl.excalibur.data.executor.JobExecutor;
import com.rcl.excalibur.data.repository.DiscoverItemDataRepository;
import com.rcl.excalibur.data.repository.ItemDataRepository;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;
import com.rcl.excalibur.domain.repository.ItemRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final RCLApp application;

    public ApplicationModule(RCLApp application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }


    @Provides
    @Singleton
    ItemRepository provideItemRepository(ItemDataRepository itemDataRepository) {
        return itemDataRepository;
    }

    @Provides
    @Singleton
    DiscoverItemRepository provideDiscoverRepository(DiscoverItemDataRepository discoverItemDataRepository) {
        return discoverItemDataRepository;
    }
}
