package com.rcl.excalibur.activity.guest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.internal.di.component.ActivityComponent;
import com.rcl.excalibur.internal.di.component.guest.GuestActivityComponent;
import com.rcl.excalibur.internal.di.module.guest.GuestActivityModule;
import com.rcl.excalibur.mvp.presenter.guest.AnswerQuestionPresenter;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AnswerQuestionActivity extends BaseActivity<AnswerQuestionPresenter> {

    GuestActivityComponent guestActivityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        ButterKnife.bind(this);
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_SECURITY_QUESTION_ANSWER);
    }

    @OnClick(R.id.image_back_screen)
    void onClickBackScreen() {
        presenter.onHeaderBackOnClick();
    }

    @OnClick(R.id.button_done_answer)
    void onClick() {
        presenter.onPressDoneBtn();
    }

    @OnTextChanged(R.id.edit_answer)
    void onTextAnswerChange(Editable editable) {
        presenter.setValidateAnswer();
    }

    @Override
    protected void injectActivity(ActivityComponent activityComponent) {
        guestActivityComponent.inject(this);
    }

    @Override
    protected void createComponent() {
        rclApp.createGuestComponent();
        guestActivityComponent = rclApp.getGuestComponent().plus(new GuestActivityModule(this));

    }

    @Override
    protected void destroyComponent() {
        guestActivityComponent = null;
        rclApp.destroyGuestComponent();
    }
}
