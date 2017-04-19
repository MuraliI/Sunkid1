package com.rcl.excalibur.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailPort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import timber.log.Timber;

public final class DayInformationUtils {

    private static final String PORT_TYPE_EMBARK = "EMBARK";
    private static final String PORT_TYPE_DOCKED = "DOCKED";
    private static final String PORT_TYPE_DEBARK = "DEBARK";
    private static final String PORT_TYPE_CRUISING = "CRUISING";
    private static final String PORT_TYPE_AT_SEA = "At Sea";
    private static final int DEFAULT_TIME_VALUE = 0;
    private static final int MIDDAY_TIME_VALUE = 12;
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
    private static final String DEBARKING = "Debarking";
    private static final String EMBARKING = "Embarking";
    private static final String DEBARKING_EMBARKING_SEPARATOR = " - ";

    private DayInformationUtils() {
    }

    @Nullable
    public static String getShipLocation(@NonNull SailPort sailPort) {
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

    @Nullable
    public static String getShipLocation(@NonNull List<SailDateEvent> events, int day) {
        String shipLocation;
        SailPort sailPort = new SailPort();
        for (SailDateEvent sailPortEventElement : events) {
            if (sailPortEventElement.getDay() == day) {
                sailPort = sailPortEventElement.getPort();
            }
        }

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
    public static String getArrivalDebarkTime(@NonNull List<SailDateEvent> events, int day) {
        StringBuilder arrivalDebarkTime = new StringBuilder();
        SailPort sailPort = new SailPort();
        for (SailDateEvent sailPortEventElement : events) {
            if (sailPortEventElement.getDay() == day) {
                sailPort = sailPortEventElement.getPort();
            }
        }

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
            if (getShipLocation(events, day).equalsIgnoreCase(PORT_TYPE_AT_SEA)) {
                if ((day + 1) < events.size()) {
                    for (SailDateEvent sailPortEventElement : events) {
                        if (sailPortEventElement.getDay() == (day + 1)) {
                            sailPort = sailPortEventElement.getPort();
                        }
                    }
                }
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
        int endString = arrivalTimeString.length();
        String timeAmPm;
        String timeHour;
        String timeMinute = getStringTimeData(arrivalTimeString, endString, DEFAULT_TIME_VALUE, true);
        if (arrivalTime >= MAX_TIME_HOUR) {
            timeAmPm = TIME_FORMAT_PM;
            timeHour = getStringTimeData(arrivalTimeString, endString, MIDDAY_TIME_VALUE, false);
        } else {
            timeAmPm = TIME_FORMAT_AM;
            timeHour = getStringTimeData(arrivalTimeString, endString, DEFAULT_TIME_VALUE, false);
        }

        StringBuilder time = appendValues(timeHour, TIME_FORMAT_SEPARATOR, timeMinute, timeAmPm);

        return time.toString();
    }

    private static String getStringTimeData(String arrivalTimeString, int endString, int dif, boolean isMinute) {
        String stringTime;
        if (isMinute) {
            stringTime = arrivalTimeString.substring(endString - END_TIME_INDEX, endString - MIDDLE_TIME_INDEX);
        } else {
            String subString = arrivalTimeString.substring(BEGIN_TIME_INDEX, endString - END_TIME_INDEX);
            int subStringValue = Integer.valueOf(subString) - dif;
            stringTime = String.valueOf(subStringValue);
        }
        return stringTime;
    }

    private static StringBuilder appendValues(@NonNull String... values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int position = 0; position < values.length; position++) {
            stringBuilder.append(values[position]);
        }
        return stringBuilder;
    }

}
