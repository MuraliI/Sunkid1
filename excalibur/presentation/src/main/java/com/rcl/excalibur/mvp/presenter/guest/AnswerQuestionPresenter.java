package com.rcl.excalibur.mvp.presenter.guest;


import android.widget.Toast;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.guest.CreateAccountDoneActivity;
import com.rcl.excalibur.domain.guest.CreateAccountEvent;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.presenter.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.mvp.view.guest.AnswerQuestionView;
import com.rcl.excalibur.utils.ActivityUtils;

import java.util.HashSet;
import java.util.Set;

public class AnswerQuestionPresenter implements ActivityPresenter {
    private static final int MIN_CHARS = 3;
    private AnswerQuestionView view;
    private GuestServices guestServices;
    private CreateAccountServiceObserver serviceObserver;
    private GetGuestPreferencesUseCase getGuestPreferencesUseCase;

    public AnswerQuestionPresenter(AnswerQuestionView view, GuestServices guestServices,
                                   GetGuestPreferencesUseCase getGuestPreferencesUseCase) {
        this.view = view;
        this.guestServices = guestServices;
        this.serviceObserver = new CreateAccountServiceObserver(this);
        this.getGuestPreferencesUseCase = getGuestPreferencesUseCase;
    }

    public void onPressDoneBtn() {
        Set<String> answer = new HashSet<>();
        answer.add(view.getAnswer());
        getGuestPreferencesUseCase.putAnswers(answer);
        guestServices.createAccount(serviceObserver);
    }


    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }

    public void setTitleQuestion(String titleQuestion) {
        view.setTitleQuestion(titleQuestion);
    }

    @Override
    public ActivityView getView() {
        return view;
    }

    public void hideKeyboard() {
        view.hideKeyboard();
    }

    public void setValidateAnswer() {
        view.setEnableDone(view.getAnswer().length() > MIN_CHARS);
    }

    private final class CreateAccountServiceObserver extends DefaultPresentObserver<CreateAccountEvent, AnswerQuestionPresenter> {

        private CreateAccountServiceObserver(AnswerQuestionPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(CreateAccountEvent value) {
            if (value.isSuccessful()) {
                ActivityUtils.startActivity(view.getActivity(), CreateAccountDoneActivity.getStartIntent(view.getActivity()));
            } else {
                Toast.makeText(view.getContext(), value.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
