package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.DateItineraryResponse;
import com.rcl.excalibur.data.service.response.PortResponse;
import com.rcl.excalibur.data.service.response.SailDateEventResponse;
import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.SailPort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class SailDateInfoDataMapperTest {
    private SailDateInfoDataMapper sailDateInfoDataMapper;
    private SailingInfoResponse sailingInfoResponse;

    private DateItineraryResponse dateItineraryResponse;
    private List<SailDateEventResponse> events;

    private SailDateEventResponse event1;
    private SailDateEventResponse event2;

    private PortResponse portResponse1;
    private PortResponse portResponse2;


    @Before
    public void setUp() throws Exception {
        sailDateInfoDataMapper = new SailDateInfoDataMapper();
        sailingInfoResponse = new SailingInfoResponse();
        dateItineraryResponse = new DateItineraryResponse();
        dateItineraryResponse.setDescription("7 NIGHT EASTERN CARIBBEAN CRUISE");

        events = new ArrayList<>();
        event1 = new SailDateEventResponse();
        event1.setDay("1");
        portResponse1 = new PortResponse();
        portResponse1.setPortCode("FLL");
        portResponse1.setPortName("FORT LAUDERDALE, FLORIDA");
        portResponse1.setPortType("EMBARK");
        portResponse1.setArrivalDate("04/23/2017");
        portResponse1.setDepartureDate("04/23/2017");
        portResponse1.setArrivalTime("0");
        portResponse1.setDepartureTime("163000");
        event1.setPort(portResponse1);

        event2 = new SailDateEventResponse();
        event2.setDay("2");
        portResponse2 = new PortResponse();
        portResponse2.setPortCode("NAS");
        portResponse2.setPortName("NASSAU, BAHAMAS");
        portResponse2.setPortType("DOCKED");
        portResponse2.setArrivalDate("04/24/2017");
        portResponse2.setDepartureDate("04/24/2017");
        portResponse2.setArrivalTime("70000");
        portResponse2.setDepartureTime("140000");
        event2.setPort(portResponse2);

        events.add(event1);
        events.add(event2);

        dateItineraryResponse.setEvents(events);

        sailingInfoResponse.setDuration("7");
        sailingInfoResponse.setShipCode("AL");
        sailingInfoResponse.setItinerary(dateItineraryResponse);


    }

    @After
    public void tearDown() throws Exception {
        sailDateInfoDataMapper = null;
        sailingInfoResponse = null;

        dateItineraryResponse = null;
        events = null;

        event1 = null;
        event2 = null;

        portResponse1 = null;
        portResponse2 = null;
    }

    @Test
    public void transform() throws Exception {
        SailDateInfo sailDateInfoEvent = sailDateInfoDataMapper.transform(sailingInfoResponse, null);
        assertNotNull(sailDateInfoEvent);
        SailDateItinerary dateItinerary = sailDateInfoEvent.getItinerary();
        assertNotNull(dateItinerary);
        List<SailDateEvent> eventsDate = dateItinerary.getEvents();
        assertFalse(events.isEmpty());
        assertEquals(eventsDate.size(), events.size());

        assertEquals(sailingInfoResponse.getDuration(), sailDateInfoEvent.getDuration());
        assertEquals(sailingInfoResponse.getShipCode(), sailDateInfoEvent.getShipCode());
        assertEquals(dateItineraryResponse.getDescription(), dateItinerary.getDescription());

        for (int i = 0; i < events.size(); i++) {
            PortResponse portResponse = events.get(i).getPort();
            SailPort sailPort = eventsDate.get(i).getPort();

            assertEquals(events.get(i).getDay(), eventsDate.get(i).getDay());

            assertEquals(portResponse.getPortName(), sailPort.getPortName());
            assertEquals(portResponse.getPortType(), sailPort.getPortType());
            assertEquals(portResponse.getPortCode(), sailPort.getPortCode());
            assertEquals(portResponse.getArrivalDate(), sailPort.getArrivalDate());
            assertEquals(portResponse.getDepartureDate(), sailPort.getDepartureDate());
            assertEquals(portResponse.getArrivalTime(), sailPort.getArrivalTime());
            assertEquals(portResponse.getDepartureTime(), sailPort.getDepartureTime());
        }
    }

}