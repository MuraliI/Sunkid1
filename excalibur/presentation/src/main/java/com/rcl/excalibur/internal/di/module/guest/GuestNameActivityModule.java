package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;
import com.rcl.excalibur.mvp.presenter.guest.NamePresenter;
import com.rcl.excalibur.mvp.view.guest.NameView;

import dagger.Module;
import dagger.Provides;

@GuestActivityScope
@Module
public class GuestNameActivityModule {
    private final BaseActivity activity;

    public GuestNameActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesActivity() {
        return activity;
    }

    @Provides
    NameView providesNameView(BaseActivity activity) {
        return new NameView(((NameActivity) activity));
    }

    @Provides
    NamePresenter providesNamePresenter(NameView nameView, GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        return new NamePresenter(nameView, getGuestPreferencesUseCase);
    }
}
