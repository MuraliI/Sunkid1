package com.rcl.excalibur.mvp.presenter.guest;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.AnswerQuestionActivity;
import com.rcl.excalibur.activity.guest.SecurityQuestionsActivity;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import java.util.List;

import timber.log.Timber;

import static com.rcl.excalibur.utils.ActivityUtils.startActivity;

public class SecurityQuestionsPresenter {
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
        view.setAdapterObserver(new AdapterObserver(this));

        view.init();
        getSecurityQuestionsUseCase.execute(new DefaultObserver<List<String>>() {
            @Override
            public void onNext(List<String> value) {
                view.updateQuestions(value);
            }

            @Override
            public void onError(Throwable exception) {
                Timber.e(exception.getMessage(), exception);
                view.showMessage(R.string.error_message_security_questions);
                onComplete();
            }

            @Override
            public void onComplete() {
                view.hideProgress();
            }
        }, null);
    }

    public static class AdapterObserver extends DefaultPresentObserver<String, SecurityQuestionsPresenter> {

        public AdapterObserver(SecurityQuestionsPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(String value) {
            getPresenter().getGuestPreferencesUseCase.putQuestion(value);
            final SecurityQuestionsActivity activity = getPresenter().view.getActivity();
            if (activity == null) {
                return;
            }
            startActivity(activity, AnswerQuestionActivity.getStartIntent(activity));

        }
    }
}
