package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.TriptyHomechView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class TriptycHomePresenterTest {


    TriptycHomePresenter triptycHomePresenter;
    @Mock TriptyHomechView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        triptycHomePresenter = new TriptycHomePresenter(view);

    }

    @Test
    public void initTest() {
        verify(view).init();
    }

}