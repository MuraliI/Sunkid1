package com.rcl.excalibur.utils;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.PortModel;

import java.util.List;

public final class DayInformationUtils {

    private static final int DEFAULT_TIME_VALUE = 0;
    private static final int MIDDAY_TIME_VALUE = 12;
    private static final int MAX_TIME_HOUR = 120000;
    private static final int BEGIN_TIME_INDEX = 0;
    private static final int MIDDLE_TIME_INDEX = 2;
    private static final int END_TIME_INDEX = 4;
    private static final int FIRST_DAY = 1;

    private DayInformationUtils() {
    }

    public static String getShipLocation(@NonNull List<EventModel> events, int day, Resources resources) {
        String shipLocation;
        PortModel sailPort = getSailPortByDay(events, day);

        String modelPortType = sailPort.getPortType();
        if (modelPortType.equalsIgnoreCase(resources.getString(R.string.port_type_embark))
                || modelPortType.equalsIgnoreCase(resources.getString(R.string.port_type_docked))
                || modelPortType.equalsIgnoreCase(resources.getString(R.string.port_type_debark))) {
            shipLocation = sailPort.getPortName();
        } else if (modelPortType.equalsIgnoreCase(resources.getString(R.string.port_type_cruising))) {
            shipLocation = resources.getString(R.string.port_type_at_sea);
        } else {
            shipLocation = ConstantsUtil.EMPTY;
        }
        return shipLocation;
    }

    public static Pair<String, Integer> getArrivalDebarkDescription(@NonNull List<EventModel> events, int day, Resources resources) {
        String arrivalDebarkTime;
        int drawable;
        PortModel sailPort = getSailPortByDay(events, day);

        if (day == FIRST_DAY) {
            arrivalDebarkTime = appendValues(resources.getString(R.string.departing_at),
                    getTimeFormat(Integer.valueOf(sailPort.getDepartureTime()), resources));
            drawable = R.drawable.ic_excursions;
        } else if (day == events.size()) {
            arrivalDebarkTime = appendValues(resources.getString(R.string.arriving_at),
                    getTimeFormat(Integer.valueOf(sailPort.getArrivalTime()), resources));
            drawable = R.drawable.ic_excursions;
        } else if ((getShipLocation(events, day, resources)).equalsIgnoreCase(resources.getString(R.string.port_type_at_sea))) {
            if ((day + 1) <= events.size()) {
                sailPort = getSailPortByDay(events, (day + 1));
            }
            arrivalDebarkTime = appendValues(resources.getString(R.string.next_port), sailPort.getPortName());
            drawable = R.drawable.ic_excursions;
        } else {
            arrivalDebarkTime = appendValues(resources.getString(R.string.arriving_at),
                    getTimeFormat(Integer.valueOf(sailPort.getArrivalTime()), resources),
                    resources.getString(R.string.arriving_departing_separator),
                    resources.getString(R.string.departing_at), getTimeFormat(Integer.valueOf(sailPort.getDepartureTime()), resources));
            drawable = R.drawable.ic_excursions;
        }

        return new Pair<>(arrivalDebarkTime, drawable);
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

    private static String getTimeFormat(@NonNull int arrivalTime, Resources resources) {
        String time = ConstantsUtil.EMPTY;
        if (arrivalTime > 0) {
            String arrivalTimeString = String.valueOf(arrivalTime);
            int endString = arrivalTimeString.length();
            String timeAmPm;
            String timeHour;
            String timeMinute = getStringTimeData(arrivalTimeString, endString, DEFAULT_TIME_VALUE, true);
            if (arrivalTime >= MAX_TIME_HOUR) {
                timeAmPm = resources.getString(R.string.time_format_pm);
                timeHour = getStringTimeData(arrivalTimeString, endString, MIDDAY_TIME_VALUE, false);
            } else {
                timeAmPm = resources.getString(R.string.time_format_am);
                timeHour = getStringTimeData(arrivalTimeString, endString, DEFAULT_TIME_VALUE, false);
            }

            time = appendValues(timeHour, resources.getString(R.string.time_format_separator), timeMinute, timeAmPm);
        }

        return time;
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

    private static String appendValues(@NonNull String... values) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int position = 0; position < values.length; position++) {
            if (values[position].isEmpty()) {
                return ConstantsUtil.EMPTY;
            } else {
                stringBuilder.append(values[position]);
            }

        }
        return stringBuilder.toString();
    }

}
