package com.rcl.excalibur.utils;

import android.support.v4.util.Pair;

import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.PortModel;

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
        List<EventModel> sailDateEvents = initSailDateEvent_withPortData("04/24/2017");
        String shipLocation = DayInformationUtils.getShipLocation(sailDateEvents, 2);
        assertEquals(shipLocation, "At NASSAU, BAHAMAS");
    }

    @Test
    public void testShipLocationAtSea() throws Exception {
        List<EventModel> sailDateEvents = initSailDateEvent_withPortData("04/25/2017");
        String shipLocation = DayInformationUtils.getShipLocation(sailDateEvents, 3);
        assertEquals(shipLocation, "At Sea");
    }

    @Test
    public void testArrivalDebarkTime_todayAtPort() throws Exception {
        List<EventModel> sailDateEvents = initSailDateEvent_withPortData("04/19/2017");
        Pair<String, Integer> stringIntegerPair = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 2);
        String arrivalDebarkTime = stringIntegerPair.first;
        int shipIcon = stringIntegerPair.second;
        assertEquals(arrivalDebarkTime, "Arriving at 7:00 AM; Departing at 2:00 PM");
        assertEquals(shipIcon, 2130837689);
    }

    @Test
    public void testArrivalDebarkTime_todayAtSea() throws Exception {
        List<EventModel> sailDateEvents = initSailDateEvent_withPortData("04/19/2017");
        Pair<String, Integer> stringIntegerPair = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 3);
        String arrivalDebarkTime = stringIntegerPair.first;
        int shipIcon = stringIntegerPair.second;
        assertEquals(arrivalDebarkTime, "Next Port: FORT LAUDERDALE, FLORIDA");
        assertEquals(shipIcon, 2130837689);
    }

    @Test
    public void testArrivalDebarkTime_lastDay() throws Exception {
        List<EventModel> sailDateEvents = initSailDateEvent_withPortData("04/30/2017");
        Pair<String, Integer> stringIntegerPair = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 4);
        String arrivalDebarkTime = stringIntegerPair.first;
        int shipIcon = stringIntegerPair.second;
        assertEquals(arrivalDebarkTime, "Arriving at 6:15 AM");
        assertEquals(shipIcon, 2130837689);
    }

    @Test
    public void testArrivalDebarkTime_firstDay() throws Exception {
        List<EventModel> sailDateEvents = initSailDateEvent_withPortData("04/23/2017");
        Pair<String, Integer> stringIntegerPair = DayInformationUtils.getArrivalDebarkDescription(sailDateEvents, 1);
        String arrivalDebarkTime = stringIntegerPair.first;
        int shipIcon = stringIntegerPair.second;
        assertEquals(arrivalDebarkTime, "Departing at 4:30 PM");
        assertEquals(shipIcon, 2130837689);
    }

    private List<EventModel> initSailDateEvent_withPortData(String date) {
        List<EventModel> sailDateEvents = new ArrayList<>();

        PortModel sailPort = new PortModel();
        sailPort.setPortCode("FLL");
        sailPort.setPortName("FORT LAUDERDALE, FLORIDA");
        sailPort.setPortType("EMBARK");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/23/2017");
        sailPort.setArrivalTime("0");
        sailPort.setDepartureTime("163000");

        EventModel sailDateEvent = new EventModel();
        sailDateEvent.setDay("1");
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        sailPort = new PortModel();
        sailPort.setPortCode("NAS");
        sailPort.setPortName("NASSAU, BAHAMAS");
        sailPort.setPortType("DOCKED");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/24/2017");
        sailPort.setArrivalTime("70000");
        sailPort.setDepartureTime("140000");

        sailDateEvent = new EventModel();
        sailDateEvent.setDay("2");
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        sailPort = new PortModel();
        sailPort.setPortCode("CRU");
        sailPort.setPortName("CRUISING");
        sailPort.setPortType("CRUISING");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/25/2017");
        sailPort.setArrivalTime("0");
        sailPort.setDepartureTime("0");

        sailDateEvent = new EventModel();
        sailDateEvent.setDay("3");
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        sailPort = new PortModel();
        sailPort.setPortCode("FLL");
        sailPort.setPortName("FORT LAUDERDALE, FLORIDA");
        sailPort.setPortType("DEBARK");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/30/2017");
        sailPort.setArrivalTime("61500");
        sailPort.setDepartureTime("0");

        sailDateEvent = new EventModel();
        sailDateEvent.setDay("4");
        sailDateEvent.setPort(sailPort);
        sailDateEvents.add(sailDateEvent);

        return sailDateEvents;
    }

}