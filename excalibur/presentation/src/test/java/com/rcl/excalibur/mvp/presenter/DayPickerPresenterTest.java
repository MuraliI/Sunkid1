package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.mvp.view.DayPickerView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DayPickerPresenterTest {

    DayPickerPresenter presenter;
    @Mock DayPickerView view;
    @Mock GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    @Mock GetSaildDateDbUseCase getSaildDateDbUseCase;
    @Mock BaseActivity activity;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new DayPickerPresenter(view,
                getSailingPreferenceUseCase,
                getSaildDateDbUseCase);

        when(view.getActivity()).thenReturn(activity);

    }

    @Test
    public void init() throws Exception {
        presenter.init();
        verify(getSaildDateDbUseCase).get();
    }

    @Test
    public void setFooter() throws Exception {

    }

}