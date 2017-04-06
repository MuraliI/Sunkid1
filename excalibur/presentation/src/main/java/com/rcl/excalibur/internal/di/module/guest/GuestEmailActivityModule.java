package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;
import com.rcl.excalibur.mvp.presenter.guest.EmailPresenter;
import com.rcl.excalibur.mvp.view.guest.EmailView;

import dagger.Module;
import dagger.Provides;

@GuestActivityScope
@Module
public class GuestEmailActivityModule {
    private final BaseActivity activity;

    public GuestEmailActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesActivity() {
        return activity;
    }

    @Provides
    protected EmailView providesEmailView(BaseActivity activity) {
        return new EmailView(((EmailActivity) activity));
    }

    @Provides
    EmailPresenter providesEmailPresenter(EmailView activityView, GetGuestPreferencesUseCase getGuestPreferencesUseCase,
                                          GuestServices guestServices) {
        return new EmailPresenter(activityView, getGuestPreferencesUseCase, guestServices);
    }


}
