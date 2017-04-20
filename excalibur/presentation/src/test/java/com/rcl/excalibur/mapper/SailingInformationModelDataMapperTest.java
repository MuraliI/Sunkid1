package com.rcl.excalibur.mapper;


import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.SailPort;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class SailingInformationModelDataMapperTest {

    private SailingInformationModelDataMapper sailingInformationModelDataMapper;
    private SailDateInfo sailDateInfo;

    private SailDateItinerary sailDateItinerary;
    private List<SailDateEvent> sailDateEvents;

    private SailDateEvent event1;
    private SailDateEvent event2;

    private SailPort sailPort1;
    private SailPort sailPort2;

    @Before
    public void setUp() throws Exception {
        sailingInformationModelDataMapper = new SailingInformationModelDataMapper();

        sailDateInfo = new SailDateInfo();
        sailDateItinerary = new SailDateItinerary();
        sailDateItinerary.setDescription("7 NIGHT EASTERN CARIBBEAN CRUISE");

        sailDateEvents = new ArrayList<>();
        event1 = new SailDateEvent();
        event1.setDay("1");
        sailPort1 = new SailPort();
        sailPort1.setPortCode("FLL");
        sailPort1.setPortName("FORT LAUDERDALE, FLORIDA");
        sailPort1.setPortType("EMBARK");
        sailPort1.setArrivalDate("04/23/2017");
        sailPort1.setDepartureDate("04/23/2017");
        sailPort1.setArrivalTime("0");
        sailPort1.setDepartureTime("163000");
        event1.setPort(sailPort1);

        event2 = new SailDateEvent();
        event2.setDay("2");
        sailPort2 = new SailPort();
        sailPort2.setPortCode("NAS");
        sailPort2.setPortName("NASSAU, BAHAMAS");
        sailPort2.setPortType("DOCKED");
        sailPort2.setArrivalDate("04/24/2017");
        sailPort2.setDepartureDate("04/24/2017");
        sailPort2.setArrivalTime("70000");
        sailPort2.setDepartureTime("140000");
        event2.setPort(sailPort2);

        sailDateEvents.add(event1);
        sailDateEvents.add(event2);

        sailDateItinerary.setEvents(sailDateEvents);

        sailDateInfo.setDuration("7");
        sailDateInfo.setShipCode("AL");
        sailDateInfo.setItinerary(sailDateItinerary);
    }

    @After
    public void tearDown() throws Exception {
        sailingInformationModelDataMapper = null;
        sailDateInfo = null;

        sailDateItinerary = null;
        sailDateEvents = null;

        event1 = null;
        event2 = null;

        sailPort1 = null;
        sailPort2 = null;
    }

    @Test
    public void transform() throws Exception {
        SailingInfoModel sailDateInfoEvent = sailingInformationModelDataMapper.transform(sailDateInfo);
        assertNotNull(sailDateInfoEvent);
        ItineraryModel dateItinerary = sailDateInfoEvent.getItinerary();
        assertNotNull(dateItinerary);
        List<EventModel> eventsDate = dateItinerary.getEvents();
        assertFalse(sailDateEvents.isEmpty());
        assertEquals(eventsDate.size(), sailDateEvents.size());

        assertEquals(sailDateInfo.getDuration(), sailDateInfoEvent.getDuration());
        assertEquals(sailDateInfo.getShipCode(), sailDateInfoEvent.getShipCode());
        assertEquals(sailDateItinerary.getDescription(), dateItinerary.getDescription());

        for (int i = 0; i < sailDateEvents.size(); i++) {
            SailPort sailport = sailDateEvents.get(i).getPort();
            PortModel sailPortModel = eventsDate.get(i).getPort();

            assertEquals(sailport.getPortName(), sailPortModel.getPortName());
            assertEquals(sailport.getPortType(), sailPortModel.getPortType());
            assertEquals(sailport.getPortCode(), sailPortModel.getPortCode());
            assertEquals(sailport.getArrivalDate(), sailPortModel.getArrivalDate());
            assertEquals(sailport.getDepartureDate(), sailPortModel.getDepartureDate());
            assertEquals(sailport.getArrivalTime(), sailPortModel.getArrivalTime());
            assertEquals(sailport.getDepartureTime(), sailPortModel.getDepartureTime());
        }

    }

}