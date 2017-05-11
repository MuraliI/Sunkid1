package com.rcl.excalibur.mvp.presenter.guest;

import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.guest.EmailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmailPresenterTest {
    EmailPresenter presenter;
    @Mock EmailView view;
    @Mock GetGuestPreferencesUseCase getGuestPreferencesUseCase;
    @Mock GuestServices guestServices;
    @Mock EmailActivity activity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new EmailPresenter(view, getGuestPreferencesUseCase, guestServices);
    }


    @Test
    public void verifyEmailCaseEmpty() throws Exception {
        when(view.getEmail()).thenReturn("");
        when(view.getActivity()).thenReturn(activity);

        presenter.verifyEmail();
        verify(view).getEmail();
        verify(view).getActivity();

        verify(view).cleanTextViewError();
        verify(view).manageNavigation(false, EmailView.INACTIVE);

    }

    @Test
    public void verifyEmailCaseOK() throws Exception {
        when(view.getEmail()).thenReturn("email@email.com");
        when(view.getActivity()).thenReturn(activity);

        presenter.verifyEmail();
        verify(view).getEmail();
        verify(view).getActivity();


        verify(view).cleanTextViewError();
        verify(view).manageNavigation(true, EmailView.ACTIVE);
    }

    @Test
    public void checkDone() throws Exception {
        when(view.isPossibleNavigate()).thenReturn(true);
        when(view.getEmail()).thenReturn("email@email.com");

        presenter.checkDone();
        verify(view).isPossibleNavigate();
        verify(view).getEmail();
        verify(guestServices).validateEmail(Mockito.any(DefaultPresentObserver.class), eq("email@email.com"));
    }

}