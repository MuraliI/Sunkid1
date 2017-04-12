package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.PlanListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class PlanListPresenterTest {
    PlanListPresenter presenter;
    @Mock PlanListView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PlanListPresenter(view);
    }

    @Test
    public void initTest() throws Exception {
        int fragmentShow = 1;
        presenter.init(fragmentShow);
        verify(view).setAdapterObserver(any(PlanListPresenter.AdapterObserver.class));
        verify(view).init(fragmentShow);
        verifyNoMoreInteractions(view);
    }
}
