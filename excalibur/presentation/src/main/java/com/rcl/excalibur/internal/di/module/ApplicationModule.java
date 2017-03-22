package com.rcl.excalibur.internal.di.module;

import android.content.Context;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.UIThread;
import com.rcl.excalibur.data.executor.JobExecutor;
import com.rcl.excalibur.data.repository.CategoryDataRepository;
import com.rcl.excalibur.data.repository.DiscoverItemDataRepository;
import com.rcl.excalibur.data.service.DiscoverServiceImpl;
import com.rcl.excalibur.data.service.ItineraryServiceImpl;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.CategoryRepository;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;
import com.rcl.excalibur.domain.service.DiscoverService;
import com.rcl.excalibur.domain.service.ItineraryService;

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
    DiscoverItemRepository provideDiscoverRepository(DiscoverItemDataRepository discoverItemDataRepository) {
        return discoverItemDataRepository;
    }

    @Provides
    @Singleton
    CategoryRepository provideCategoryRepository(CategoryDataRepository categoryDataRepository) {
        return categoryDataRepository;
    }

    @Provides
    @Singleton
    DiscoverService provideDiscoverService(DiscoverServiceImpl discoverServiceImpl) {
        return discoverServiceImpl;
    }

    @Provides
    @Singleton
    ItineraryService provideItineraryService(ItineraryServiceImpl itineraryServiceImpl) {
        return itineraryServiceImpl;
    }
}
