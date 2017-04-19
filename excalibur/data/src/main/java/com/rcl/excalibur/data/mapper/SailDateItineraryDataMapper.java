package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.DateItineraryResponse;
import com.rcl.excalibur.data.service.response.PortResponse;
import com.rcl.excalibur.data.service.response.SailDateEventResponse;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.SailPort;

import java.util.ArrayList;
import java.util.List;

public class SailDateItineraryDataMapper extends BaseDataMapper<SailDateItinerary, DateItineraryResponse, Void> {

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
        sailPort.setPortCode(portResponse.getPortCode());
        sailPort.setPortName(portResponse.getPortName());
        sailPort.setPortType(portResponse.getPortType());
        sailPort.setDepartureTime(portResponse.getDepartureTime());
        sailPort.setDepartureDate(portResponse.getDepartureDate());
        sailPort.setArrivalTime(portResponse.getArrivalTime());
        sailPort.setArrivalDate(portResponse.getArrivalDate());
        return sailPort;
    }
}
