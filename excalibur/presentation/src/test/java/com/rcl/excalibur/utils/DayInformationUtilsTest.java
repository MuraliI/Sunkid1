package com.rcl.excalibur.utils;

import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailPort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayInformationUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testShipLocationNamePort() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/24/2017");
        String shipLocation = DayInformationUtils.getShipLocation(sailDateEvents, 2);
        assertEquals(shipLocation, "NASSAU, BAHAMAS");
    }

    @Test
    public void testShipLocationAtSea() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/25/2017");
        String shipLocation = DayInformationUtils.getShipLocation(sailDateEvents, 3);
        assertEquals(shipLocation, "At Sea");
    }

    @Test
    public void testArrivalDebarkTime_todayAtPort() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/19/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkTime(sailDateEvents, 2);
        assertEquals(arrivalDebarkTime, "Debarking at 7:00am - Embarking at 2:00pm");
    }

    @Test
    public void testArrivalDebarkTime_todayAtSea() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/19/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkTime(sailDateEvents, 3);
        assertEquals(arrivalDebarkTime, "Arriving at CHARLOTTE AMALIE, ST. THOMAS at 10:00am");
    }

    @Test
    public void testArrivalDebarkTime_otherDay() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/26/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkTime(sailDateEvents, 4);
        assertEquals(arrivalDebarkTime, "");
    }

    private List<SailDateEvent> initSailDateEvent_withPortData(String date) {
        List<SailDateEvent> sailDateEvents = new ArrayList<>();

        SailPort sailPort = new SailPort();
        sailPort.setPortCode("FLL");
        sailPort.setPortName("FORT LAUDERDALE, FLORIDA");
        sailPort.setPortType("EMBARK");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/23/2017");
        sailPort.setArrivalTime(0);
        sailPort.setDepartureTime(163000);

        SailDateEvent sailDateEvent = new SailDateEvent();
        sailDateEvent.setDay(1);
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        sailPort = new SailPort();
        sailPort.setPortCode("NAS");
        sailPort.setPortName("NASSAU, BAHAMAS");
        sailPort.setPortType("DOCKED");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/24/2017");
        sailPort.setArrivalTime(70000);
        sailPort.setDepartureTime(140000);

        sailDateEvent = new SailDateEvent();
        sailDateEvent.setDay(2);
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        sailPort = new SailPort();
        sailPort.setPortCode("CRU");
        sailPort.setPortName("CRUISING");
        sailPort.setPortType("CRUISING");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/25/2017");
        sailPort.setArrivalTime(0);
        sailPort.setDepartureTime(0);

        sailDateEvent = new SailDateEvent();
        sailDateEvent.setDay(3);
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        sailPort = new SailPort();
        sailPort.setPortCode("STT");
        sailPort.setPortName("CHARLOTTE AMALIE, ST. THOMAS");
        sailPort.setPortType("DOCKED");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/26/2017");
        sailPort.setArrivalTime(100000);
        sailPort.setDepartureTime(190000);

        sailDateEvent = new SailDateEvent();
        sailDateEvent.setDay(4);
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        return sailDateEvents;
    }

}