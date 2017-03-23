package com.rcl.excalibur.mvp.presenter;

import android.content.Intent;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.PlanListActivity;
import com.rcl.excalibur.mvp.view.DiscoverTabView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/test/resources/TestAndroidManifest.xml", sdk = 21)
public class DiscoverTabPresenterTest {

    DiscoverTabPresenter discoverTabPresenter;
    @Mock DiscoverTabView view;
    @Mock BaseActivity activity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.when(view.getActivity()).thenReturn(activity);
        discoverTabPresenter = new DiscoverTabPresenter(view);
    }


    @Test
    public void initTest() {

        verify(view, times(2)).getActivity();
    }


    @Test
    public void openListScreen() {
        discoverTabPresenter.openListScreen(0);
        Intent startedActivity = ShadowApplication.getInstance().peekNextStartedActivity();
        assertEquals(PlanListActivity.class.getName(), startedActivity.getComponent().getClassName());
    }

}