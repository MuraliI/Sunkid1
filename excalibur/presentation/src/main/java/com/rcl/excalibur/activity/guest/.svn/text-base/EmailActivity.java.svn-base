package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.data.preference.GuestPreferenceImpl;
import com.rcl.excalibur.data.service.GuestServicesImpl;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.preference.GuestPreference;
import com.rcl.excalibur.mvp.presenter.guest.EmailPresenter;
import com.rcl.excalibur.mvp.view.guest.EmailView;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class EmailActivity extends BaseActivity {
    private EmailPresenter presenter;

    public static Intent getStartIntent(final BaseActivity activity) {
        return new Intent(activity, EmailActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_EMAIL);
        final GuestPreference guestPreference = new GuestPreferenceImpl(this);

        presenter = new EmailPresenter(new EmailView(this)
                , new GetGuestPreferencesUseCase(guestPreference)
                , new GuestServicesImpl(guestPreference));
    }

    @OnClick(R.id.image_back_screen)
    public void onHeaderBackOnClick() {
        presenter.onHeaderBackOnClick();
    }

    @OnTextChanged(value = R.id.edit_email, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterEmailInput() {
        presenter.verifyEmail();
    }


    @OnEditorAction(R.id.edit_email)
    boolean onEditorAction() {
        presenter.checkDone();
        return true;
    }

    @OnClick(R.id.image_next_screen)
    public void onClickImageViewNext() {
        presenter.checkDone();
    }
}
