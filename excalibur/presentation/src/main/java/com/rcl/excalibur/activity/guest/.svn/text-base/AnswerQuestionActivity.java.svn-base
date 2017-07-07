package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.data.preference.GuestPreferenceImpl;
import com.rcl.excalibur.data.service.GuestServicesImpl;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.preference.GuestPreference;
import com.rcl.excalibur.mvp.presenter.guest.AnswerQuestionPresenter;
import com.rcl.excalibur.mvp.view.guest.AnswerQuestionView;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AnswerQuestionActivity extends BaseActivity {

    protected AnswerQuestionPresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, AnswerQuestionActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        ButterKnife.bind(this);
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_SECURITY_QUESTION_ANSWER);
        final GuestPreference guestPreference = new GuestPreferenceImpl(this);
        presenter = new AnswerQuestionPresenter(new AnswerQuestionView(this)
                , new GuestServicesImpl(guestPreference)
                , new GetGuestPreferencesUseCase(guestPreference));
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


    @OnClick(R.id.container_layout)
    void onClickContainer() {
        presenter.hideKeyBoard();
    }
}
