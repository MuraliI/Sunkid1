package com.rcl.excalibur.internal.di.module;

import com.rcl.excalibur.RCLApp;

import static org.mockito.Mockito.mock;

public class AppModuleTest extends AppModule {

    public AppModuleTest() {
        super(mock(RCLApp.class));
    }
}
