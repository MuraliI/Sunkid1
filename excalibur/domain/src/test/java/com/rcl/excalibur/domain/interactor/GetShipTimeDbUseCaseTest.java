package com.rcl.excalibur.domain.interactor;

import com.rcl.excalibur.domain.repository.ShipTimeRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GetShipTimeDbUseCaseTest {
    GetShipTimeDbUseCase getShipTimeDbUseCase;
    @Mock ShipTimeRepository shipTimeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getShipTimeDbUseCase = new GetShipTimeDbUseCase(shipTimeRepository);
    }

    @Test
    public void getData() throws Exception {
        Assert.assertEquals(shipTimeRepository, getShipTimeDbUseCase.getData());
    }

    @Test
    public void get() throws Exception {
        getShipTimeDbUseCase.get();
        Mockito.verify(shipTimeRepository).get();
    }

}