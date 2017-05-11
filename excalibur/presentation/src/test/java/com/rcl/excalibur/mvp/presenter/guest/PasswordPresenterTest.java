package com.rcl.excalibur.mvp.presenter.guest;

import android.content.Intent;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.mvp.view.guest.PasswordView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PasswordPresenterTest {
    PasswordPresenter presenter;
    @Mock PasswordView view;
    @Mock GetGuestPreferencesUseCase getGuestPreferencesUseCase;
    @Mock PasswordActivity activity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(view.getActivity()).thenReturn(activity);
        presenter = new PasswordPresenter(view, getGuestPreferencesUseCase);
    }

    @Test
    public void onHeaderBackOnClick() throws Exception {
        presenter.onHeaderBackOnClick();
        verify(view).getActivity();
        verify(activity).finish();
        verify(activity).overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    @Test
    public void setVisibilityPassword() throws Exception {
        presenter.setVisibilityPassword(true);
        verify(view).setVisiblePassword();
        presenter.setVisibilityPassword(false);
        verify(view).setInvisiblePassword();
    }

    @Test
    public void hideKeyBoard() throws Exception {
        presenter.hideKeyBoard();
        verify(view).hideKeyboard();
    }

    @Test
    public void isValidData() throws Exception {
        presenter.isValidData();
        verify(view).isValidData();
    }

    @Test
    public void onClickImageViewNext() throws Exception {
        when(view.getPassword()).thenReturn("pass1233");
        presenter.onClickImageViewNext();
        verify(view).getActivity();
        verify(view).getPassword();
        verify(getGuestPreferencesUseCase).putPassword("pass1233");
        verify(activity).startActivity(Mockito.any(Intent.class));
        verify(activity).overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

}