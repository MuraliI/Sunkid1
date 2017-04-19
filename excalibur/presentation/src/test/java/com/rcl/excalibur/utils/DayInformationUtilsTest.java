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
        assertEquals(shipLocation, "At NASSAU, BAHAMAS");
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
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 2);
        assertEquals(arrivalDebarkTime, "Arriving at 7:00 AM; Departing at 2:00 PM");
    }

    @Test
    public void testArrivalDebarkTime_todayAtSea() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/19/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 3);
        assertEquals(arrivalDebarkTime, "Next Port: FORT LAUDERDALE, FLORIDA");
    }

    @Test
    public void testArrivalDebarkTime_lastDay() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/30/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 4);
        assertEquals(arrivalDebarkTime, "Arriving at 6:15 AM");
    }

    @Test
    public void testArrivalDebarkTime_firstDay() throws Exception {
        List<SailDateEvent> sailDateEvents = initSailDateEvent_withPortData("04/23/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 1);
        assertEquals(arrivalDebarkTime, "Departing at 4:30 PM");
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
        sailPort.setPortCode("FLL");
        sailPort.setPortName("FORT LAUDERDALE, FLORIDA");
        sailPort.setPortType("DEBARK");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/30/2017");
        sailPort.setArrivalTime(61500);
        sailPort.setDepartureTime(0);

        sailDateEvent = new SailDateEvent();
        sailDateEvent.setDay(4);
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        return sailDateEvents;
    }

}