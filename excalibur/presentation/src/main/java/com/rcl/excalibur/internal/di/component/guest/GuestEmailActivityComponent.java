package com.rcl.excalibur.internal.di.component.guest;

import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.internal.di.module.guest.GuestEmailActivityModule;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;

import dagger.Subcomponent;

@GuestActivityScope
@Subcomponent(modules = GuestEmailActivityModule.class)
public interface GuestEmailActivityComponent {
    void inject(EmailActivity activity);
}
