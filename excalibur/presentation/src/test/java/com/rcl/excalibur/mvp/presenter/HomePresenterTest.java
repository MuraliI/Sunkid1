package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.internal.di.component.ActivityComponentTest;
import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.module.ActivityModuleTest;
import com.rcl.excalibur.internal.di.module.AppModuleTest;
import com.rcl.excalibur.mvp.presenter.guest.HomePresenter;
import com.rcl.excalibur.mvp.view.HomeView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

public class HomePresenterTest {
    @Inject HomePresenter presenter;
    private AppComponentTest appComponentTest;
    private ActivityComponentTest activityComponentTest;

    @Before
    public void setUp() throws Exception {
        appComponentTest = DaggerAppComponentTest.builder()
                .appModule(new AppModuleTest())
                .build();
        activityComponentTest = appComponentTest.plus(new ActivityModuleTest());
        activityComponentTest.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        activityComponentTest = null;
        appComponentTest = null;
    }

    @Test
    public void initTest() throws Exception {
        HomeView view = presenter.getView();
        verify(view).setAdapterObserver(any(HomePresenter.AdapterObserver.class));
        verify(view).init();
        verify(view).addAll(any(List.class));
    }
}