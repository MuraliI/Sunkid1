package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.module.AppModule;
import com.rcl.excalibur.internal.di.scopes.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule module);
}
