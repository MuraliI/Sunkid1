package com.rcl.excalibur.mvp.presenter.guest;

import android.support.annotation.VisibleForTesting;

import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import java.util.HashSet;
import java.util.List;

import timber.log.Timber;

public class SecurityQuestionsPresenter implements ActivityPresenter {
    private SecurityQuestionsView view;
    private GetSecurityQuestionsUseCase getSecurityQuestionsUseCase;
    private GetGuestPreferencesUseCase getGuestPreferencesUseCase;

    public SecurityQuestionsPresenter(SecurityQuestionsView view, GetSecurityQuestionsUseCase getSecurityQuestionsUseCase,
                                      GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        this.view = view;
        this.getSecurityQuestionsUseCase = getSecurityQuestionsUseCase;
        this.getGuestPreferencesUseCase = getGuestPreferencesUseCase;
    }

    public void init() {
        view.init();
        getSecurityQuestionsUseCase.execute(new DefaultObserver<List<String>>() {
            @Override
            public void onNext(List<String> value) {
                getGuestPreferencesUseCase.putQuestions(new HashSet<>(value));

                //TODO getGuestPreferencesUseCase.putAnswers();
                view.updateQuestions(value);
            }

            @Override
            public void onError(Throwable exception) {
                Timber.e(exception.getMessage(), exception);
                view.showError();
                onComplete();
            }

            @Override
            public void onComplete() {
                view.hideProgress();
            }
        }, null);
    }

    @Override
    public SecurityQuestionsView getView() {
        return view;
    }

    @VisibleForTesting
    GetSecurityQuestionsUseCase getGetSecurityQuestionsUseCase() {
        return getSecurityQuestionsUseCase;
    }
}
