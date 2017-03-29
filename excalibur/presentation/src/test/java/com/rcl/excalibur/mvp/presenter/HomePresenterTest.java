package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.BaseTest;
import com.rcl.excalibur.internal.di.component.ActivityComponentTest;
import com.rcl.excalibur.internal.di.module.ActivityModuleTest;
import com.rcl.excalibur.mvp.view.HomeView;

import org.junit.Test;

import java.util.List;

import javax.inject.Inject;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

public class HomePresenterTest extends BaseTest {
    @Inject HomePresenter presenter;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ActivityComponentTest activityComponent = appComponentTest.plus(new ActivityModuleTest());
        activityComponent.inject(this);
    }

    @Test
    public void initTest() throws Exception {
        HomeView view = presenter.getView();
        verify(view).setAdapterObserver(any(HomePresenter.AdapterObserver.class));
        verify(view).init();
        verify(view).addAll(any(List.class));
    }
}