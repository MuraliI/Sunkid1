package com.rcl.excalibur.utils;

import android.content.Intent;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class ActivityUtilsTest {

    @Mock BaseActivity activity;
    @Mock Intent intent;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void startActivity() throws Exception {
        ActivityUtils.startActivity(activity, intent);
        verify(activity).startActivity(intent);
        verify(activity).overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    @Test
    public void onBackActivity() throws Exception {
        ActivityUtils.onBackActivity(activity);
        verify(activity).finish();
        verify(activity).overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

}