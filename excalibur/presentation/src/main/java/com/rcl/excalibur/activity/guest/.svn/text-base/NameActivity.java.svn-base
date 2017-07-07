package com.rcl.excalibur.activity.guest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.data.preference.GuestPreferenceImpl;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.preference.GuestPreference;
import com.rcl.excalibur.mvp.presenter.guest.NamePresenter;
import com.rcl.excalibur.mvp.view.guest.NameView;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;


public class NameActivity extends BaseActivity {
    private NamePresenter presenter;

    public static Intent getStartIntent(BaseActivity activity) {
        return new Intent(activity, NameActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        ButterKnife.bind(this);
        final GuestPreference guestPreference = new GuestPreferenceImpl(this);
        presenter = new NamePresenter(new NameView(this), new GetGuestPreferencesUseCase(guestPreference));
        presenter.init();
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_NAME);
    }

    @OnClick(R.id.arrow_back)
    public void onArrowBackClick() {
        presenter.onArrowBack();
    }

    @OnClick(R.id.next_button)
    public void onNextButtonClick() {
        presenter.onNextClick();
    }

    @OnTextChanged(R.id.full_name)
    public void onNameChanged() {
        presenter.onNameChanged();
    }

    @OnClick(R.id.container_layout)
    public void onContainerClick() {
        presenter.hideKeyboard();
    }

    @OnEditorAction(R.id.full_name)
    boolean onEditorAction() {
        presenter.onNextClick();
        return true;
    }

}
