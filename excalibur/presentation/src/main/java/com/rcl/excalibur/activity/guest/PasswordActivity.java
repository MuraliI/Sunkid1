package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.data.preference.GuestPreferenceImpl;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.preference.GuestPreference;
import com.rcl.excalibur.mvp.presenter.guest.PasswordPresenter;
import com.rcl.excalibur.mvp.view.guest.PasswordView;
import com.rcl.excalibur.utils.analytics.AnalyticEvent;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class PasswordActivity extends BaseActivity {
    private PasswordPresenter presenter;

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, PasswordActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_PASSWORD);
        final GuestPreference guestPreference = new GuestPreferenceImpl(this);
        presenter = new PasswordPresenter(new PasswordView(this), new GetGuestPreferencesUseCase(guestPreference));
    }

    @OnClick(R.id.image_back_screen)
    void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @OnClick(R.id.image_next_screen)
    public void onClickImageViewNext() {
        AnalyticsUtils.trackEvent(new AnalyticEvent(AnalyticsConstants.KEY_GUEST_ACCOUNT_SUBMIT_CREDENTIALS));
        presenter.onClickImageViewNext();
    }

    @OnCheckedChanged(R.id.checkbox_show_password)
    void onCheckChange(boolean isChecked) {
        presenter.setVisibilityPassword(isChecked);
    }

    @OnTextChanged(R.id.edit_create_password)
    void onTextPasswordChange(Editable editable) {
        presenter.verifyPassword();
    }

    @OnClick(R.id.container_layout)
    void onClickContainer() {
        presenter.hideKeyBoard();
    }

    @OnEditorAction(R.id.edit_create_password)
    boolean onEditorAction() {
        AnalyticsUtils.trackEvent(new AnalyticEvent(AnalyticsConstants.KEY_GUEST_ACCOUNT_SUBMIT_CREDENTIALS));
        if (presenter.isValidData()) {
            presenter.onClickImageViewNext();
        }
        return true;
    }
}
