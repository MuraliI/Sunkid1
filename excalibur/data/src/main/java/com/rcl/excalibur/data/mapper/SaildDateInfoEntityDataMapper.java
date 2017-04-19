package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.EventEntity;
import com.rcl.excalibur.data.entity.ItineraryEntity;
import com.rcl.excalibur.data.entity.PortEntity;
import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.SailPort;

import java.util.ArrayList;
import java.util.List;

public class SaildDateInfoEntityDataMapper extends BaseDataMapper<SailDateInfo, SailDateInfoEntity> {

    @Nullable
    @Override
    public SailDateInfo transform(SailDateInfoEntity input) {

        final SailDateInfo sailDateInfo = new SailDateInfo();
        sailDateInfo.setDuration(input.getDuration());
        sailDateInfo.setShipCode(input.getShipCode());
        sailDateInfo.setItinerary(transform(input.getItinerary()));

        return sailDateInfo;
    }
    private SailDateItinerary transform(ItineraryEntity itineraryEntity) {
        SailDateItinerary sailDateItinerary = null;
        if (itineraryEntity != null) {
            sailDateItinerary.setDescription(itineraryEntity.getDescription());
            sailDateItinerary.setEvents(transformEnents(itineraryEntity.getEvents()));

        }
        return sailDateItinerary;
    }

    private List<SailDateEvent> transformEnents(List<EventEntity> entities) {
        ArrayList<SailDateEvent> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(entities)) {
            return items;
        }

        for (EventEntity entity : entities) {
            if (entity == null) {
                continue;
            }
            SailDateEvent sailDateEvent = transform(entity);
            items.add(sailDateEvent);
        }
        return items;
    }

    private SailDateEvent transform(EventEntity eventEntity) {
        SailDateEvent sailDateEvent = null;
        if (eventEntity != null) {
            sailDateEvent.setDay(eventEntity.getDay());
            //sailDateEvent.setPort(transform(eventEntity.getPort()));

        }
        return sailDateEvent;
    }
    private SailPort transform(PortEntity portEntity) {
        SailPort sailPort = null;
        if (portEntity != null) {
            sailPort.setPortCode(portEntity.getPortCode());
            sailPort.setPortType(portEntity.getPortType());
            sailPort.setPortName(portEntity.getPortName());
            sailPort.setArrivalDate(portEntity.getArrivalDate());
            sailPort.setDepartureDate(portEntity.getDepartureDate());
            sailPort.setArrivalTime(portEntity.getArrivalTime());
            sailPort.setDepartureTime(portEntity.getDepartureTime());
        }
        return sailPort;
    }
}
