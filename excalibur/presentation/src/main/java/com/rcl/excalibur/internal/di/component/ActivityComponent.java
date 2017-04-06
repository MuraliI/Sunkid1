package com.rcl.excalibur.internal.di.component;

import com.rcl.excalibur.activity.HomeActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.activity.guest.CreateAccountDoneActivity;
import com.rcl.excalibur.activity.guest.EmailActivity;
import com.rcl.excalibur.activity.guest.NameActivity;
import com.rcl.excalibur.activity.guest.PasswordActivity;
import com.rcl.excalibur.internal.di.module.ActivityModule;
import com.rcl.excalibur.internal.di.module.FragmentModule;

import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    FragmentComponent plus(FragmentModule module);

    void inject(HomeActivity activity);

    void inject(TriptychHomeActivity activity);

    void inject(NameActivity activity);

    void inject(EmailActivity activity);

    void inject(PasswordActivity activity);

    void inject(CreateAccountDoneActivity activity);
}
