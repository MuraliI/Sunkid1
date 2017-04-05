package com.rcl.excalibur.internal.di.component.guest;

import com.rcl.excalibur.internal.di.module.guest.GuestActivityModule;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;
import com.rcl.excalibur.mvp.presenter.guest.SecurityQuestionsPresenterTest;

import dagger.Subcomponent;

@GuestActivityScope
@Subcomponent(modules = GuestActivityModule.class)
public interface GuestActivityComponentTest extends GuestActivityComponent {
    void inject(SecurityQuestionsPresenterTest test);
}
