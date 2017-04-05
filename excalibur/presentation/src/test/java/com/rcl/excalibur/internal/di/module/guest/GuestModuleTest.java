package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.domain.interactor.GetSecurityQuestionsUseCase;
import com.rcl.excalibur.domain.service.GuestServices;

import static org.mockito.Mockito.mock;

public class GuestModuleTest extends GuestModule {
    @Override
    GetSecurityQuestionsUseCase providesGetSecurityQuestionsUseCase(GuestServices guestServices) {
        return mock(GetSecurityQuestionsUseCase.class);
    }
}
