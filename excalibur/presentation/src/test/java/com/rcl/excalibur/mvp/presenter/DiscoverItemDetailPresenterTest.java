package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.DiscoverItemDetailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by norberto.lopez on 3/23/2017.
 */
public class DiscoverItemDetailPresenterTest {

    DiscoverItemDetailPresenter discoverItemDetailPresenter;
    @Mock DiscoverItemDetailView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        discoverItemDetailPresenter = new DiscoverItemDetailPresenter(view,any(String.class));
    }

    @Test
    public void inisTest() {
        verify(view).showToastAndFinishActivity(any(String.class));
    }

}