package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.internal.di.scopes.ActivityScope;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.view.HomeView;

import dagger.Module;
import dagger.Provides;

@ActivityScope
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
    protected HomeView providesHomeView(BaseActivity activity) {
        return new HomeView(((HomeActivity) activity));
    }

    @Provides
    HomePresenter providesHomePresenter(HomeView activityView) {
        return new HomePresenter(activityView);
    }
}
