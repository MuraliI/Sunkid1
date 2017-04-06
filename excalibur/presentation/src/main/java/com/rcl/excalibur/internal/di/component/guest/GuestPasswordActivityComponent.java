package com.rcl.excalibur.internal.di.component.guest;

import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.internal.di.module.guest.GuestPasswordActivityModule;
import com.rcl.excalibur.internal.di.scopes.guest.GuestActivityScope;

import dagger.Subcomponent;

@GuestActivityScope
@Subcomponent(modules = GuestPasswordActivityModule.class)
public interface GuestPasswordActivityComponent {
    void inject(PasswordActivity activity);
}
