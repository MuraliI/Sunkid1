package com.rcl.excalibur.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

public class DayInformationUtils {

    private static final String PORT_TYPE_EMBARK = "EMBARK";
    private static final String PORT_TYPE_DOCKED = "DOCKED";
    private static final String PORT_TYPE_DEBARK = "DEBARK";
    private static final String PORT_TYPE_CRUISING = "CRUISING";
    private static final String PORT_TYPE_AT_SEA = "AT SEA";
    private static final int DEFAULT_TIME_VALUE = 0;
    private static final String DATE_FORMAT = "MM/dd/yyyy";
    private static final String ARRIVING_AT = " at ";
    private static final String ARRIVING = "Arriving";
    private static final String TIME_FORMAT_AM = "am";
    private static final String TIME_FORMAT_PM = "pm";
    private static final String TIME_FORMAT_SEPARATOR = ":";
    private static final int MAX_TIME_HOUR = 120000;
    private static final int BEGIN_TIME_INDEX = 0;
    private static final int MIDDLE_TIME_INDEX = 2;
    private static final int END_TIME_INDEX = 4;
    public static final String DEBARKING = "Debarking";
    public static final String EMBARKING = "Embarking";
    public static final String DEBARKING_EMBARKING_SEPARATOR = " - ";

    private DayInformationUtils() {
    }

    //dummy class
    private class SailPort {
        private String portCode;//FLL
        private String portName;
        private String portType;
        private String arrivalDate;//04/23/2017//dd/MM/yyyy
        private String departureDate;//04/23/2017//dd/MM/yyyy
        private int arrivalTime;//100000//hhmmss
        private int departureTime;//163000//hhmmss

        public String getPortCode() {
            return portCode;
        }

        public void setPortCode(String portCode) {
            this.portCode = portCode;
        }

        public String getPortName() {
            return portName;
        }

        public void setPortName(String portName) {
            this.portName = portName;
        }

        public String getPortType() {
            return portType;
        }

        public void setPortType(String portType) {
            this.portType = portType;
        }

        public String getArrivalDate() {
            return arrivalDate;
        }

        public void setArrivalDate(String arrivalDate) {
            this.arrivalDate = arrivalDate;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public int getArrivalTime() {
            return arrivalTime;
        }

        public void setArrivalTime(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        public int getDepartureTime() {
            return departureTime;
        }

        public void setDepartureTime(int departureTime) {
            this.departureTime = departureTime;
        }
    }

    @Nullable
    private static String getShipLocation(@NonNull SailPort sailPort) {
        String shipLocation;
        String modelPortType = sailPort.getPortType();
        if (modelPortType.equalsIgnoreCase(PORT_TYPE_EMBARK)
                || modelPortType.equalsIgnoreCase(PORT_TYPE_DOCKED)
                || modelPortType.equalsIgnoreCase(PORT_TYPE_DEBARK)) {
            shipLocation = sailPort.getPortName();
        } else if (modelPortType.equalsIgnoreCase(PORT_TYPE_CRUISING)) {
            shipLocation = PORT_TYPE_AT_SEA;
        } else {
            shipLocation = null;
        }
        return shipLocation;
    }

    @Nullable
    public static String getArrivalDebarkTime(@NonNull SailPort sailPort) {
        StringBuilder arrivalDebarkTime = new StringBuilder();

        Date date = null;
        try {
            date = (new SimpleDateFormat(DATE_FORMAT, Locale.US)).parse(sailPort.getArrivalDate());
        } catch (ParseException e) {
            Timber.d(e.getMessage());
        }

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, DEFAULT_TIME_VALUE);
        today.set(Calendar.MINUTE, DEFAULT_TIME_VALUE);
        today.set(Calendar.SECOND, DEFAULT_TIME_VALUE);
        today.set(Calendar.MILLISECOND, DEFAULT_TIME_VALUE);
        Calendar arrivalDate = Calendar.getInstance();
        arrivalDate.setTime(date);

        if (arrivalDate.equals(today)) {
            if (getShipLocation(sailPort).equalsIgnoreCase(PORT_TYPE_AT_SEA)) {
                arrivalDebarkTime = appendValues(ARRIVING, ARRIVING_AT, sailPort.getPortName(), ARRIVING_AT,
                        getTimeFormat(sailPort.getArrivalTime()));
            } else {
                arrivalDebarkTime = appendValues(DEBARKING, ARRIVING_AT, getTimeFormat(sailPort.getArrivalTime()),
                        DEBARKING_EMBARKING_SEPARATOR, EMBARKING, ARRIVING_AT, getTimeFormat(sailPort.getDepartureTime()));
            }
        }

        return arrivalDebarkTime.toString();
    }

    private static String getTimeFormat(@NonNull int arrivalTime) {
        String arrivalTimeString = String.valueOf(arrivalTime);

        StringBuilder time = appendValues(arrivalTimeString.substring(BEGIN_TIME_INDEX, MIDDLE_TIME_INDEX),
                TIME_FORMAT_SEPARATOR, arrivalTimeString.substring(MIDDLE_TIME_INDEX, END_TIME_INDEX));

        if (arrivalTime >= MAX_TIME_HOUR) {
            time.append(TIME_FORMAT_PM);
        } else {
            time.append(TIME_FORMAT_AM);
        }

        return time.toString();
    }

    private static StringBuilder appendValues(@NonNull String... values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int position = 0; position < values.length; position++) {
            stringBuilder.append(values[position]);
        }
        return stringBuilder;
    }

}
