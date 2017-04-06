package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.AnswerQuestionActivity;
import com.rcl.excalibur.activity.guest.SecurityQuestionsActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;
import com.rcl.excalibur.mvp.presenter.guest.AnswerQuestionPresenter;
import com.rcl.excalibur.mvp.presenter.guest.SecurityQuestionsPresenter;
import com.rcl.excalibur.mvp.view.guest.AnswerQuestionView;
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
                                                                  GetSecurityQuestionsUseCase getSecurityQuestionsUseCase,
                                                                  GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        return new SecurityQuestionsPresenter(view, getSecurityQuestionsUseCase, getGuestPreferencesUseCase);
    }

    @Provides
    protected AnswerQuestionView providesAnswerQuestionView(BaseActivity activity) {
        return new AnswerQuestionView(((AnswerQuestionActivity) activity));
    }

    @Provides
    AnswerQuestionPresenter providesAnswerQuestionPresenter(AnswerQuestionView activityView, GuestServices guestService,
                                                            GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        return new AnswerQuestionPresenter(activityView, guestService, getGuestPreferencesUseCase);
    }
}
