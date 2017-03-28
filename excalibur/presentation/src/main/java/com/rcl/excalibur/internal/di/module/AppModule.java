package com.rcl.excalibur.internal.di.module;

import android.content.Context;
import android.content.res.Resources;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.UIThread;
import com.rcl.excalibur.data.executor.JobExecutor;
import com.rcl.excalibur.data.repository.CategoryDataRepository;
import com.rcl.excalibur.data.repository.DiscoverItemDataRepository;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.executor.ThreadExecutor;
import com.rcl.excalibur.domain.repository.CategoryRepository;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;
import com.rcl.excalibur.internal.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@ApplicationScope
@Module
public class AppModule {
    private final RCLApp application;

    public AppModule(RCLApp application) {
        this.application = application;
    }

    @Provides
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    Resources providesResources(Context context) {
        return context.getResources();
    }

    @Provides
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    DiscoverItemRepository provideDiscoverRepository(DiscoverItemDataRepository discoverItemDataRepository) {
        return discoverItemDataRepository;
    }

    @Provides
    CategoryRepository provideCategoryRepository(CategoryDataRepository categoryDataRepository) {
        return categoryDataRepository;
    }
}
