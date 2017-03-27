package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.module.AppModule;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponentTest extends AppComponent {
    @Override
    ActivityComponentTest plus(ActivityModule activityModuleTest);
}
