package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;
import com.rcl.excalibur.mvp.presenter.guest.PasswordPresenter;
import com.rcl.excalibur.mvp.view.guest.PasswordView;

import dagger.Module;
import dagger.Provides;

@GuestActivityScope
@Module
public class GuestPasswordActivityModule {
    private final BaseActivity activity;

    public GuestPasswordActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesActivity() {
        return activity;
    }


    @Provides
    protected PasswordView providesPasswordView(BaseActivity activity) {
        return new PasswordView((PasswordActivity) activity);
    }

    @Provides
    PasswordPresenter providesPasswordPresenter(PasswordView passwordView, GetGuestPreferencesUseCase getGuestPreferencesUseCas) {
        return new PasswordPresenter(passwordView, getGuestPreferencesUseCas);
    }


}
