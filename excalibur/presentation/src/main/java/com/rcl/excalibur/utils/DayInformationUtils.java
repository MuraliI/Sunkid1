package com.rcl.excalibur.utils;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.rcl.excalibur.R;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.PortModel;

import java.util.List;

public final class DayInformationUtils {

    private static final String PORT_TYPE_EMBARK = "EMBARK";
    private static final String PORT_TYPE_DOCKED = "DOCKED";
    private static final String PORT_TYPE_DEBARK = "DEBARK";
    private static final String PORT_TYPE_CRUISING = "CRUISING";
    private static final String PORT_TYPE_AT_SEA = "At Sea";
    private static final int DEFAULT_TIME_VALUE = 0;
    private static final int MIDDAY_TIME_VALUE = 12;
    private static final String ARRIVING_AT_UPER = "At ";
    private static final String TIME_FORMAT_AM = " AM";
    private static final String TIME_FORMAT_PM = " PM";
    private static final String TIME_FORMAT_SEPARATOR = ":";
    private static final int MAX_TIME_HOUR = 120000;
    private static final int BEGIN_TIME_INDEX = 0;
    private static final int MIDDLE_TIME_INDEX = 2;
    private static final int END_TIME_INDEX = 4;
    private static final String NEXT_PORT = "Next Port: ";
    private static final String ARRIVING_AT = "Arriving at ";
    private static final String DEPARTING_AT = "Departing at ";
    private static final String ARRIVING_DEPARTING_SEPARATOR = "; ";
    private static final int FIRST_DAY = 1;

    private DayInformationUtils() {
    }

    public static String getShipLocation(@NonNull List<EventModel> events, int day) {
        String shipLocation;
        PortModel sailPort = getSailPortByDay(events, day);

        String modelPortType = sailPort.getPortType();
        if (modelPortType.equalsIgnoreCase(PORT_TYPE_EMBARK)
                || modelPortType.equalsIgnoreCase(PORT_TYPE_DOCKED)
                || modelPortType.equalsIgnoreCase(PORT_TYPE_DEBARK)) {
            shipLocation = ARRIVING_AT_UPER + sailPort.getPortName();
        } else if (modelPortType.equalsIgnoreCase(PORT_TYPE_CRUISING)) {
            shipLocation = PORT_TYPE_AT_SEA;
        } else {
            shipLocation = "";
        }
        return shipLocation;
    }

    public static Pair<String, Integer> getArrivalDebarkDescription(@NonNull List<EventModel> events, int day) {
        StringBuilder arrivalDebarkTime;
        int drawable;
        PortModel sailPort = getSailPortByDay(events, day);

        if (day == FIRST_DAY) {
            arrivalDebarkTime = appendValues(DEPARTING_AT, getTimeFormat(Integer.valueOf(sailPort.getDepartureTime())));
            drawable = R.drawable.ship_icon;
        } else if (day == events.size()) {
            arrivalDebarkTime = appendValues(ARRIVING_AT, getTimeFormat(Integer.valueOf(sailPort.getArrivalTime())));
            drawable = R.drawable.ship_icon;
        } else if ((getShipLocation(events, day)).equalsIgnoreCase(PORT_TYPE_AT_SEA)) {
            if ((day + 1) <= events.size()) {
                sailPort = getSailPortByDay(events, (day + 1));
            }
            arrivalDebarkTime = appendValues(NEXT_PORT, sailPort.getPortName());
            drawable = R.drawable.ship_icon;
        } else {
            arrivalDebarkTime = appendValues(ARRIVING_AT, getTimeFormat(Integer.valueOf(sailPort.getArrivalTime())),
                    ARRIVING_DEPARTING_SEPARATOR, DEPARTING_AT, getTimeFormat(Integer.valueOf(sailPort.getDepartureTime())));
            drawable = R.drawable.ship_icon;
        }

        return new Pair<>(arrivalDebarkTime.toString(), drawable);
    }

    private static PortModel getSailPortByDay(@NonNull List<EventModel> events, int day) {
        PortModel sailPort = new PortModel();
        for (EventModel sailPortEventElement : events) {
            if (Integer.valueOf(sailPortEventElement.getDay()) == day) {
                sailPort = sailPortEventElement.getPort();
            }
        }
        return sailPort;
    }

    private static String getTimeFormat(@NonNull int arrivalTime) {
        StringBuilder time = new StringBuilder();
        if (arrivalTime > 0) {
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

            time = appendValues(timeHour, TIME_FORMAT_SEPARATOR, timeMinute, timeAmPm);
        }

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
            if (values[position].isEmpty()) {
                return new StringBuilder();
            } else {
                stringBuilder.append(values[position]);
            }

        }
        return stringBuilder;
    }

}
