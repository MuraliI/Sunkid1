package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.DiscoverTabView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class DiscoverTabPresenterTest {

    DiscoverTabPresenter discoverTabPresenter;
    @Mock DiscoverTabView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        discoverTabPresenter = new DiscoverTabPresenter(view);
    }


    @Test
    public void initTest() {

        verify(view, times(2)).getActivity();
    }

}