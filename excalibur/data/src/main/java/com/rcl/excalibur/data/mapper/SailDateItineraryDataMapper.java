package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.DateItineraryResponse;
import com.rcl.excalibur.data.service.response.PortResponse;
import com.rcl.excalibur.data.service.response.SailDateEventResponse;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.SailPort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class SailDateItineraryDataMapper extends BaseDataMapper<SailDateItinerary, DateItineraryResponse, Void> {

    private final String formatHourMinute = "HHmm";

    @Nullable
    @Override
    public SailDateItinerary transform(DateItineraryResponse itineraryResponse, Void additionalArg) {
        if (itineraryResponse == null)
            return null;

        SailDateItinerary sailDateItinerary = new SailDateItinerary();
        sailDateItinerary.setDescription(itineraryResponse.getDescription());
        sailDateItinerary.setEvents(transform(itineraryResponse.getEvents()));
        return sailDateItinerary;
    }

    private List<SailDateEvent> transform(List<SailDateEventResponse> events) {
        ArrayList<SailDateEvent> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(events)) {
            return items;
        }

        for (SailDateEventResponse event : events) {
            SailDateEvent sailDateEvent = transform(event);
            items.add(sailDateEvent);
        }

        return items;
    }

    private SailDateEvent transform(SailDateEventResponse eventResponse) {
        SailDateEvent sailDateEvent = new SailDateEvent();
        sailDateEvent.setDay(eventResponse.getDay());
        sailDateEvent.setPort(transform(eventResponse.getPort()));
        return sailDateEvent;
    }

    private SailPort transform(PortResponse portResponse) {
        SailPort sailPort = new SailPort();
        Calendar arrivalDateTime = getFormatedCalendar(portResponse.getArrivalDateTime());
        Calendar departureDateTime = getFormatedCalendar(portResponse.getDepartureDateTime());
        sailPort.setPortCode(portResponse.getPortCode());
        sailPort.setPortName(portResponse.getPortName());
        sailPort.setPortType(portResponse.getPortType());
        sailPort.setDepartureTime(formatedTime(departureDateTime));
        sailPort.setDepartureDate(formatedDate(departureDateTime));
        sailPort.setArrivalTime(formatedTime(arrivalDateTime));
        sailPort.setArrivalDate(formatedDate(arrivalDateTime));
        return sailPort;
    }

    private Calendar getFormatedCalendar(String dateToTransform) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        SimpleDateFormat dateformat = DateUtil.getDateFormatISO();
        try {
            Date date = dateformat.parse(dateToTransform);
            calendar.setTime(date);
        } catch (ParseException e) {
            //Fixme Remove when Service data is fixed
            try {
                Date date = dateformat.parse("20170702T000000");
                calendar.setTime(date);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return calendar;
    }

    private String formatedDate(Calendar calendar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(calendar.getTime());
    }

    private String formatedTime(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat(formatHourMinute);
        return format.format(calendar.getTime());
    }
}
