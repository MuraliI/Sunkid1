package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.DayPickerActivityView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class DayPickerActivityPresenterTest {
    DayPickerActivityPresenter presenter;
    @Mock DayPickerActivityView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new DayPickerActivityPresenter(view);
    }

    @Test
    public void init() throws Exception {
        presenter.init();
        verify(view).init();
    }
}