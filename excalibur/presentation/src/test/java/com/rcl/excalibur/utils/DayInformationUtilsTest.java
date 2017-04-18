package com.rcl.excalibur.utils;

import com.rcl.excalibur.domain.SailPort;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayInformationUtilsTest {

    private SailPort sailPort;

    @Before
    public void setUp() throws Exception {
        sailPort = new SailPort();
    }

    @Test
    public void testShipLocationNamePort() throws Exception {
        initSailPort_withPortData(sailPort, "04/24/2017");
        String shipLocation = DayInformationUtils.getShipLocation(sailPort);
        assertEquals(shipLocation, "NASSAU, BAHAMAS");
    }

    @Test
    public void testShipLocationAtSea() throws Exception {
        initSailPort_withAtSeaData(sailPort, "04/25/2017");
        String shipLocation = DayInformationUtils.getShipLocation(sailPort);
        assertEquals(shipLocation, "At Sea");
    }

    @Test
    public void testArrivalDebarkTime_today() throws Exception {
        initSailPort_withPortData(sailPort, "04/18/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkTime(sailPort);
        assertEquals(arrivalDebarkTime, "Debarking at 7:00am - Embarking at 2:00pm");
    }

    @Test
    public void testArrivalDebarkTime_otherDay() throws Exception {
        initSailPort_withPortData(sailPort, "04/24/2017");
        String arrivalDebarkTime = DayInformationUtils.getArrivalDebarkTime(sailPort);
        assertEquals(arrivalDebarkTime, "");
    }

    private void initSailPort_withPortData(SailPort sailPort, String date) {
        sailPort.setPortCode("NAS");
        sailPort.setPortName("NASSAU, BAHAMAS");
        sailPort.setPortType("DOCKED");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/24/2017");
        sailPort.setArrivalTime(70000);
        sailPort.setDepartureTime(140000);
    }

    private void initSailPort_withAtSeaData(SailPort sailPort, String date) {
        sailPort.setPortCode("CRU");
        sailPort.setPortName("CRUISING");
        sailPort.setPortType("CRUISING");
        sailPort.setArrivalDate(date);
        sailPort.setDepartureDate("04/25/2017");
        sailPort.setArrivalTime(0);
        sailPort.setDepartureTime(0);
    }

}