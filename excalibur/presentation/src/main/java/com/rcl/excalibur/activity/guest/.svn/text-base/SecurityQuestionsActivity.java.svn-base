package com.rcl.excalibur.activity.guest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.data.preference.GuestPreferenceImpl;
import com.rcl.excalibur.data.service.GuestServicesImpl;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.domain.preference.GuestPreference;
import com.rcl.excalibur.mvp.presenter.guest.SecurityQuestionsPresenter;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;
import com.rcl.excalibur.utils.analytics.AnalyticsConstants;
import com.rcl.excalibur.utils.analytics.AnalyticsUtils;

public class SecurityQuestionsActivity extends BaseActivity {
    private SecurityQuestionsPresenter presenter;

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, SecurityQuestionsActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_questions);
        final GuestPreference guestPreference = new GuestPreferenceImpl(this);

        presenter = new SecurityQuestionsPresenter(new SecurityQuestionsView(this)
                , new GetSecurityQuestionsUseCase(new GuestServicesImpl(guestPreference))
                , new GetGuestPreferencesUseCase(guestPreference));
        presenter.init();
        AnalyticsUtils.trackState(AnalyticsConstants.KEY_GUEST_ACCOUNT_SECURITY_QUESTION);
    }
}
