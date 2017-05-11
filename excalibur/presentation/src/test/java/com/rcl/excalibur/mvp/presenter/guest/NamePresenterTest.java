package com.rcl.excalibur.mvp.presenter.guest;


import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.data.utils.StringUtils;
import com.rcl.excalibur.domain.interactor.GetGuestPreferencesUseCase;
import com.rcl.excalibur.mvp.view.guest.NameView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import static com.rcl.excalibur.mvp.presenter.guest.NamePresenter.BRAND;
import static com.rcl.excalibur.mvp.presenter.guest.NamePresenter.VERSION;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NamePresenterTest {
    NamePresenter presenter;
    @Mock NameView view;
    @Mock GetGuestPreferencesUseCase getGuestPreferencesUseCase;
    @Mock NameActivity activity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(view.getActivity()).thenReturn(activity);
        presenter = new NamePresenter(view, getGuestPreferencesUseCase);
    }

    @Test
    public void init() throws Exception {
        presenter.init();
        verify(getGuestPreferencesUseCase).putBrand(BRAND);
        verify(getGuestPreferencesUseCase).putVersion(VERSION);
        verify(getGuestPreferencesUseCase).putAcceptTime(anyLong());
        verify(view).setNextButton(false);
    }

    @Test
    public void onArrowBack() throws Exception {
        presenter.onArrowBack();
        verify(view).getActivity();
        verify(activity).finish();
    }

    @Ignore
    //TODO Improve with PowerMockito.
    public void onNameChanged() throws Exception {
        Assert.assertTrue(presenter.canChange);
        when(view.getFullName()).thenReturn("fullname");
        PowerMockito.when(StringUtils.removeBarreled("fullname")).thenReturn("removeBarreled");
        PowerMockito.when(StringUtils.capitalizeAllWords("removeBarreled")).thenReturn("capitalizeAllWords");
        PowerMockito.when(StringUtils.REGEX_FULL_NAME).thenReturn("^[a-zA-Z0-9 - ! # $ % & ' * + - / = ? ^ _ ` { | } ~]*$");
        PowerMockito.when(StringUtils.SPLIT_SEPARATOR).thenReturn(" ");

        presenter.onNameChanged();
        verify(view).getFullName();
        verify(view).changeValue("capitalizeAllWords");
        verify(view).setNextButton(anyBoolean());

        Assert.assertTrue(presenter.canChange);

    }

    @Test
    public void hideKeyboard() throws Exception {
        presenter.hideKeyboard();
        verify(view).hideKeyboard();
    }

    @Ignore
    //TODO improve with ProwerMock
    public void onNextClick() throws Exception {
    }

}