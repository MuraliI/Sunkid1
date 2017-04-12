package com.rcl.excalibur.mvp.presenter.guest;

import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.mvp.view.guest.SecurityQuestionsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class SecurityQuestionsPresenterTest {
    SecurityQuestionsPresenter presenter;
    @Mock SecurityQuestionsView view;
    @Mock GetSecurityQuestionsUseCase getSecurityQuestionsUseCase;
    @Mock GetGuestPreferencesUseCase getGuestPreferencesUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new SecurityQuestionsPresenter(view, getSecurityQuestionsUseCase, getGuestPreferencesUseCase);
    }

    @Test
    public void testInit() throws Exception {
        presenter.init();
        verify(view).setAdapterObserver(Mockito.any(SecurityQuestionsPresenter.AdapterObserver.class));
        verify(view).init();
        verify(getSecurityQuestionsUseCase).execute(Mockito.any(DefaultObserver.class), Mockito.any());
    }
}
