package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.internal.di.scopes.ActivityScope;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.presenter.guest.NamePresenter;
import com.rcl.excalibur.mvp.view.HomeView;
import com.rcl.excalibur.mvp.view.TriptychHomeView;
import com.rcl.excalibur.mvp.view.guest.NameView;

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

    @Provides
    TriptychHomeView providesTriptychHomeView(BaseActivity activity) {
        return new TriptychHomeView(((TriptychHomeActivity) activity));
    }

    @Provides
    TriptychHomePresenter providesTriptychHomePresenter(TriptychHomeView triptychHomeView) {
        return new TriptychHomePresenter(triptychHomeView);
    }

    @Provides
    NameView providesNameView(BaseActivity activity) {
        return new NameView(((NameActivity) activity));
    }

    @Provides
    NamePresenter providesNamePresenter(NameView nameView) {
        return new NamePresenter(nameView);
    }

}
