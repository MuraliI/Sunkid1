package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.EventEntity;
import com.rcl.excalibur.data.entity.ItineraryEntity;
import com.rcl.excalibur.data.entity.PortEntity;
import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.domain.SailDateInfo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class SaildDateInfoEntityDataMapperTest {

    private SaildDateInfoEntityDataMapper sailDateInfoDataMapper;
    @Mock SailDateInfoEntity sailDateInfoEntity;
    @Mock ItineraryEntity itineraryEntity;
    @Mock EventEntity eventEntity1;
    @Mock EventEntity eventEntity2;
    @Mock PortEntity portEntity1;
    @Mock PortEntity portEntity2;

    @Before
    public void setUp() throws Exception {
        sailDateInfoDataMapper = new SaildDateInfoEntityDataMapper();
        MockitoAnnotations.initMocks(this);
        when(sailDateInfoEntity.getShipCode()).thenReturn("AL");
        when(sailDateInfoEntity.getDuration()).thenReturn("7");
        when(itineraryEntity.getDescription()).thenReturn("7 Days");

        when(eventEntity1.getDay()).thenReturn("1");
        when(portEntity1.getPortCode()).thenReturn("FLL");
        when(portEntity1.getPortName()).thenReturn("FORT LAUDERDALE FLORIDA");
        when(portEntity1.getPortType()).thenReturn("EMBARK");
        when(portEntity1.getArrivalDate()).thenReturn("04/23/2017");
        when(portEntity1.getDepartureDate()).thenReturn("04/23/2017");
        when(portEntity1.getArrivalTime()).thenReturn("0");
        when(portEntity1.getDepartureTime()).thenReturn("163000");
        when(eventEntity1.getPort()).thenReturn(portEntity1);

        when(eventEntity2.getDay()).thenReturn("2");
        when(portEntity2.getPortCode()).thenReturn("NAS");
        when(portEntity2.getPortName()).thenReturn("NASSAU, BAHAMAS");
        when(portEntity2.getPortType()).thenReturn("DOCKED");
        when(portEntity2.getArrivalDate()).thenReturn("04/24/2017");
        when(portEntity2.getDepartureDate()).thenReturn("04/24/2017");
        when(portEntity2.getArrivalTime()).thenReturn("70000");
        when(portEntity2.getDepartureTime()).thenReturn("140000");
        when(eventEntity2.getPort()).thenReturn(portEntity2);

        ArrayList<EventEntity> eventEntities = new ArrayList<>();
        eventEntities.add(eventEntity1);
        eventEntities.add(eventEntity2);

        when(itineraryEntity.getEvents()).thenReturn(eventEntities);
        when(sailDateInfoEntity.getItinerary()).thenReturn(itineraryEntity);


    }

    @Test
    public void transform() throws Exception {

        SailDateInfo sailDateInfoEvent = sailDateInfoDataMapper.transform(sailDateInfoEntity, null);
        assertNotNull(sailDateInfoEvent);
        assertEquals(sailDateInfoEvent.getDuration(), sailDateInfoEntity.getDuration());
        assertEquals(sailDateInfoEvent.getShipCode(), sailDateInfoEntity.getShipCode());
        assertEquals(sailDateInfoEvent.getItinerary().getDescription(), itineraryEntity.getDescription());

        for (int i = 0; i < sailDateInfoEvent.getItinerary().getEvents().size(); i++) {
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getDay(), itineraryEntity.getEvents().get(i).getDay());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getPortName(), itineraryEntity.getEvents().get(i).getPort().getPortName());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getPortCode(), itineraryEntity.getEvents().get(i).getPort().getPortCode());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getPortType(), itineraryEntity.getEvents().get(i).getPort().getPortType());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getArrivalDate(), itineraryEntity.getEvents().get(i).getPort().getArrivalDate());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getArrivalTime(), itineraryEntity.getEvents().get(i).getPort().getArrivalTime());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getDepartureDate(), itineraryEntity.getEvents().get(i).getPort().getDepartureDate());
            assertEquals(sailDateInfoEvent.getItinerary().getEvents().get(i).getPort().getDepartureTime(), itineraryEntity.getEvents().get(i).getPort().getDepartureTime());

        }

    }

}