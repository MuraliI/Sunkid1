package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.SecurityQuestionsActivity;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;
import com.rcl.excalibur.mvp.presenter.guest.SecurityQuestionsPresenter;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import dagger.Module;
import dagger.Provides;

@GuestActivityScope
@Module
public class GuestActivityModule {
    private final BaseActivity activity;

    public GuestActivityModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    BaseActivity providesActivity() {
        return activity;
    }

    @Provides
    SecurityQuestionsView providesSecurityQuestionsView(BaseActivity activity) {
        return new SecurityQuestionsView(((SecurityQuestionsActivity) activity));
    }

    @Provides
    SecurityQuestionsPresenter providesSecurityQuestionsPresenter(SecurityQuestionsView view,
                                                                  GetSecurityQuestionsUseCase getSecurityQuestionsUseCase) {
        return new SecurityQuestionsPresenter(view, getSecurityQuestionsUseCase);
    }
}
