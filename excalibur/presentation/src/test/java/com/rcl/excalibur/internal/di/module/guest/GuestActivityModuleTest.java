package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import static org.mockito.Mockito.mock;

public class GuestActivityModuleTest extends GuestActivityModule {

    public GuestActivityModuleTest() {
        super(mock(BaseActivity.class));
    }

    @Override
    SecurityQuestionsView providesSecurityQuestionsView(BaseActivity activity) {
        return mock(SecurityQuestionsView.class);
    }
}
