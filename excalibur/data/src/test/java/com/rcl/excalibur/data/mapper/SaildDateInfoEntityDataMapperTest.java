package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.EventEntity;
import com.rcl.excalibur.data.entity.ItineraryEntity;
import com.rcl.excalibur.data.entity.PortEntity;
import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.data.service.response.PortResponse;
import com.rcl.excalibur.data.service.response.SailDateEventResponse;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfoEvent;
import com.rcl.excalibur.domain.SailDateItinerary;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;

public class SaildDateInfoEntityDataMapperTest {

    private SaildDateInfoEntityDataMapper sailDateInfoDataMapper;

    @Mock SailDateInfoEntity sailDateInfoEntity;
    @Mock ItineraryEntity itineraryEntity;
    @Mock List<EventEntity> eventEntities;

    @Mock private EventEntity eventEntity1;
    @Mock private EventEntity eventEntity2;

    @Mock private PortEntity portEntity1;
    @Mock private PortEntity portEntity2;

    @Before
    public void setUp() throws Exception {
        sailDateInfoDataMapper = new SaildDateInfoEntityDataMapper();
        MockitoAnnotations.initMocks(this);
        Mockito.when(sailDateInfoEntity.getDuration()).thenReturn(7);
        Mockito.when(sailDateInfoEntity.getShipCode()).thenReturn("AL");

    }

    @Test
    public void transform() throws Exception {
        SailDateInfoEvent sailDateInfoEvent = sailDateInfoDataMapper.transform(sailDateInfoEntity);
        assertNotNull(sailDateInfoEvent);
        assertEquals(sailDateInfoEvent.getDuration(), sailDateInfoEntity.getDuration());
        assertEquals(sailDateInfoEvent.getShipCode(), sailDateInfoEntity.getShipCode());


    }

}