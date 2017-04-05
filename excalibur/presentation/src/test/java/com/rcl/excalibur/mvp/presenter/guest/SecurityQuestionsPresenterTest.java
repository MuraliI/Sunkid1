package com.rcl.excalibur.mvp.presenter.guest;

import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.component.guest.GuestActivityComponentTest;
import com.rcl.excalibur.internal.di.component.guest.GuestComponentTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.internal.di.module.guest.GuestActivityModuleTest;
import com.rcl.excalibur.internal.di.module.guest.GuestModuleTest;
import com.rcl.excalibur.internal.di.module.guest.GuestServicesModuleTest;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import javax.inject.Inject;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class SecurityQuestionsPresenterTest {
    @Inject SecurityQuestionsPresenter presenter;
    private AppComponentTest appComponentTest;
    private GuestComponentTest guestComponentTest;
    private GuestActivityComponentTest guestActivityComponentTest;

    @Before
    public void setUp() throws Exception {
        appComponentTest = DaggerAppComponentTest.builder()
                .appModule(new AppModuleTest())
                .build();
        guestComponentTest = appComponentTest.plus(new GuestServicesModuleTest(), new GuestModuleTest());
        guestActivityComponentTest = guestComponentTest.plus(new GuestActivityModuleTest());
        guestActivityComponentTest.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        guestActivityComponentTest = null;
        guestComponentTest = null;
        appComponentTest = null;
    }

    @Test
    public void testInit_viewCalledInitOnceAndUseCaseWasExecutedOnce() throws Exception {
        SecurityQuestionsView view = presenter.getView();
        GetSecurityQuestionsUseCase useCase = presenter.getGetSecurityQuestionsUseCase();
        ArgumentCaptor<DefaultObserver> argumentCaptor = ArgumentCaptor.forClass(DefaultObserver.class);

        presenter.init();
        verify(view, times(1)).init();
        verify(useCase, times(1)).execute(argumentCaptor.capture(), eq(null));

        DefaultObserver<List<String>> defaultObserver = argumentCaptor.getValue();
        assertNotNull(defaultObserver);
    }
}