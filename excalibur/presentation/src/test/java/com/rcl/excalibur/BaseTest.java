package com.rcl.excalibur;

import android.support.annotation.CallSuper;

import com.rcl.excalibur.internal.di.component.AppComponentTest;
import com.rcl.excalibur.internal.di.component.DaggerAppComponentTest;
import com.rcl.excalibur.internal.di.module.AppModule;

import org.junit.After;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class BaseTest {
    protected AppComponentTest appComponentTest;

    @CallSuper
    @Before
    public void setUp() throws Exception {
        appComponentTest = DaggerAppComponentTest.builder()
                .appModule(new AppModule(mock(RCLApp.class)))
                .build();
    }

    @CallSuper
    @After
    public void tearDown() throws Exception {
        appComponentTest = null;
    }
}
