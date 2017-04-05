package com.rcl.excalibur.internal.di.component.guest;

import com.rcl.excalibur.activity.guest.SecurityQuestionsActivity;
import com.rcl.excalibur.internal.di.module.guest.GuestActivityModule;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;

import dagger.Subcomponent;

@GuestActivityScope
@Subcomponent(modules = GuestActivityModule.class)
public interface GuestActivityComponent {
    void inject(SecurityQuestionsActivity activity);
}
