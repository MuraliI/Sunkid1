package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.activity.guest.AnswerQuestionActivity;
import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.internal.di.scopes.ActivityScope;
import com.rcl.excalibur.mvp.presenter.HomePresenter;
import com.rcl.excalibur.mvp.presenter.TriptychHomePresenter;
import com.rcl.excalibur.mvp.presenter.guest.AnswerQuestionPresenter;
import com.rcl.excalibur.mvp.presenter.guest.EmailPresenter;
import com.rcl.excalibur.mvp.presenter.guest.NamePresenter;
import com.rcl.excalibur.mvp.presenter.guest.PasswordPresenter;
import com.rcl.excalibur.mvp.view.HomeView;
import com.rcl.excalibur.mvp.view.TriptychHomeView;
import com.rcl.excalibur.mvp.view.guest.AnswerQuestionView;
import com.rcl.excalibur.mvp.view.guest.EmailView;
import com.rcl.excalibur.mvp.view.guest.NameView;
import com.rcl.excalibur.mvp.view.guest.PasswordView;

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
    protected EmailView providesEmailView(BaseActivity activity) {
        return new EmailView(((EmailActivity) activity));
    }

    @Provides
    EmailPresenter providesEmailPresenter(EmailView activityView) {
        return new EmailPresenter(activityView);
    }

    @Provides
    protected PasswordView providesPasswordView(BaseActivity activity) {
        return new PasswordView((PasswordActivity) activity);
    }

    @Provides
    PasswordPresenter providesPasswordPresenter(PasswordView passwordView) {
        return new PasswordPresenter(passwordView);
    }

    @Provides
    protected AnswerQuestionView providesAnswerQuestionView(BaseActivity activity) {
        return new AnswerQuestionView((AnswerQuestionActivity) activity);
    }

    @Provides
    AnswerQuestionPresenter provideAnswerQuestionPresenter(AnswerQuestionView answerQuestionView) {
        return new AnswerQuestionPresenter(answerQuestionView);
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
