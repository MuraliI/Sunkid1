package com.rcl.excalibur.internal.di.module.guest;

import com.rcl.excalibur.data.mapper.guest.SecurityQuestionsResponseMapper;
import com.rcl.excalibur.data.service.api.GuestApi;
import com.rcl.excalibur.domain.service.GuestServices;

import static org.mockito.Mockito.mock;

public class GuestServicesModuleTest extends GuestServicesModule {
    @Override
    GuestServices providesGuestServices(GuestApi guestApi, SecurityQuestionsResponseMapper mapper) {
        return mock(GuestServices.class);
    }
}
