package com.rcl.excalibur.mvp.presenter.guest;

import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.mvp.view.guest.AnswerQuestionView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnswerQuestionPresenterTest {

    AnswerQuestionPresenter presenter;

    @Mock AnswerQuestionView view;
    @Mock GuestServices guestServices;
    @Mock GetGuestPreferencesUseCase getGuestPreferencesUseCase;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new AnswerQuestionPresenter(view, guestServices, getGuestPreferencesUseCase);

    }

    @Test
    public void onPressDoneBtn() throws Exception {
        when(view.getAnswer()).thenReturn("answurw");
        presenter.onPressDoneBtn();
        verify(view).getAnswer();
        verify(getGuestPreferencesUseCase).putAnswer("answurw");
        verify(guestServices).createAccount(any(AnswerQuestionPresenter.CreateAccountServiceObserver.class));
    }


    @Test
    public void setTitleQuestion() throws Exception {
        presenter.setTitleQuestion("aaasss");
        verify(view).setTitleQuestion("aaasss");
    }

    @Test
    public void hideKeyBoard() throws Exception {
        presenter.hideKeyBoard();
        verify(view).hideKeyboard();
    }

    @Test
    public void setValidateAnswer() throws Exception {
        when(view.getAnswer()).thenReturn("sasas");
        presenter.setValidateAnswer();
        verify(view).getAnswer();
        view.setEnableDone(anyBoolean());

    }

}