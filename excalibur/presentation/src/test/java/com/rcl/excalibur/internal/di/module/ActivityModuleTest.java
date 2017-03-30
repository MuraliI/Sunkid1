package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.DiscoverTabView;
import com.rcl.excalibur.mvp.view.HomeView;

import static org.mockito.Mockito.mock;

public class ActivityModuleTest extends ActivityModule {

    public ActivityModuleTest() {
        super(mock(BaseActivity.class));
    }

    @Override
    protected HomeView providesHomeView(BaseActivity activity) {
        return mock(HomeView.class);
    }

}
