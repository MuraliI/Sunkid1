package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.internal.di.scopes.PerActivity;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.view.HomeView;

import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public class ActivityModule {
    private final BaseActivity activity;

    public ActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesActivity() {
        return activity;
    }

    @Provides
    HomeView providesHomeView(BaseActivity activity) {
        return new HomeView(((HomeActivity) activity));
    }

    @Provides
    HomePresenter providesHomePresenter(HomeView activityView) {
        return new HomePresenter(activityView);
    }
}
