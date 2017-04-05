package com.rcl.excalibur.mvp.presenter.guest;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.presenter.ActivityPresenter;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.mvp.view.guest.AnswerQuestionView;
import com.rcl.excalibur.utils.ActivityUtils;

public class AnswerQuestionPresenter implements ActivityPresenter {
    private static final int MIN_CHARS = 3;
    private AnswerQuestionView view;

    public AnswerQuestionPresenter(AnswerQuestionView view) {
        this.view = view;
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

    public void setValidateAnswer() {
        view.setEnableDone(view.getAnswer().length() > MIN_CHARS);
    }
}
