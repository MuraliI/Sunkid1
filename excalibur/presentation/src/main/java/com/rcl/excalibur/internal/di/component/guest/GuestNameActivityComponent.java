package com.rcl.excalibur.internal.di.component.guest;

import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.internal.di.module.guest.GuestNameActivityModule;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;

import dagger.Subcomponent;

@GuestActivityScope
@Subcomponent(modules = GuestNameActivityModule.class)
public interface GuestNameActivityComponent {
    void inject(NameActivity activity);
}
