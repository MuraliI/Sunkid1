package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.service.ShipTimeServices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class GetShipTimeUseCaseTest {
    GetShipTimeUseCase getShipTimeUseCase;
    @Mock ShipTimeServices shipTimeServices;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getShipTimeUseCase = new GetShipTimeUseCase(shipTimeServices);

    }

    @Test
    public void buildUseCaseObservable() throws Exception {
        getShipTimeUseCase.buildUseCaseObservable(null, null);
        verify(shipTimeServices).getShipTime();

    }

}