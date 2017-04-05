package com.rcl.excalibur.internal.di.component.guest;

import com.rcl.excalibur.internal.di.module.guest.GuestActivityModule;
import com.rcl.excalibur.internal.di.module.guest.GuestModule;
import com.rcl.excalibur.internal.di.module.guest.GuestServicesModule;
import com.rcl.excalibur.internal.di.scopes.guest.GuestScope;

import dagger.Subcomponent;

@GuestScope
@Subcomponent(modules = {GuestServicesModule.class, GuestModule.class})
public interface GuestComponentTest extends GuestComponent {
    @Override
    GuestActivityComponentTest plus(GuestActivityModule guestActivityModule);
}
