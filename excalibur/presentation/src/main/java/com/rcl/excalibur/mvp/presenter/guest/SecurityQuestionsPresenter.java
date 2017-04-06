package com.rcl.excalibur.mvp.presenter.guest;

import android.support.annotation.VisibleForTesting;

import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import java.util.List;

import io.reactivex.Observer;
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
        view.setAdapterObserver((Observer) new AdapterObserver(this));

        view.init();
        getSecurityQuestionsUseCase.execute(new DefaultObserver<List<String>>() {
            @Override
            public void onNext(List<String> value) {
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


    public class AdapterObserver extends DefaultPresentObserver<String, SecurityQuestionsPresenter> {

        public AdapterObserver(SecurityQuestionsPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(String value) {
            getGuestPreferencesUseCase.putQuestion(value);
        }
    }
}
