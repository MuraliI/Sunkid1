package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.HomeView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

public class HomePresenterTest {

    HomePresenter presenter;
    @Mock HomeView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new HomePresenter(view);
    }

    @Test
    public void initTest() throws Exception {
        verify(view).setAdapterObserver(any(HomePresenter.AdapterObserver.class));
        verify(view).init();
        verify(view).addAll(any(List.class));
    }
}