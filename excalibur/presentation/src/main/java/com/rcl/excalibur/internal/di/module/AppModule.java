package com.rcl.excalibur.internal.di.module;

import android.content.Context;
import android.content.res.Resources;

import com.rcl.excalibur.RCLApp;
import com.rcl.excalibur.UIThread;
import com.rcl.excalibur.data.BuildConfig;
import com.rcl.excalibur.data.repository.CategoryDataRepository;
import com.rcl.excalibur.data.repository.DiscoverItemDataRepository;
import com.rcl.excalibur.domain.executor.PostExecutionThread;
import com.rcl.excalibur.domain.repository.CategoryRepository;
import com.rcl.excalibur.domain.repository.DiscoverItemRepository;
import com.rcl.excalibur.internal.di.scopes.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

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
    OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    @Provides
    Resources providesResources(Context context) {
        return context.getResources();
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
