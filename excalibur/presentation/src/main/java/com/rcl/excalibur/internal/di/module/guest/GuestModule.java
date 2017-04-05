package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.domain.service.GuestServices;
import com.rcl.excalibur.internal.di.scopes.guest.GuestScope;

import dagger.Module;
import dagger.Provides;

@GuestScope
@Module
public class GuestModule {

    @Provides
    GetSecurityQuestionsUseCase providesGetSecurityQuestionsUseCase(GuestServices guestServices) {
        return new GetSecurityQuestionsUseCase(guestServices);
    }
}
