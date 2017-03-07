package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.mvp.view.PlanListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class PlanListPresenterTest {
    PlanListPresenter presenter;
    @Mock PlanListView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new PlanListPresenter(view);
    }

    @Test
    public void initTest() throws Exception {
        verify(view).setAdapterObserver(any(PlanListPresenter.AdapterObserver.class));
        verify(view).init();
    }
}
