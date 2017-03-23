package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


public class DiscoverItemDetailPresenterTest {

    DiscoverItemDetailPresenter discoverItemDetailPresenter;
    @Mock DiscoverItemDetailView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        discoverItemDetailPresenter = new DiscoverItemDetailPresenter(view, any(String.class));
    }

    @Test
    public void inisTest() {
        verify(view).showToastAndFinishActivity(any(String.class));
    }

}