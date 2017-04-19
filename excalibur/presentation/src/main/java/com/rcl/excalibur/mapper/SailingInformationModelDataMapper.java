package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailPort;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class SailingInformationModelDataMapper extends BaseModelDataMapper<SailDateInfo, SailingInfoModel> {
    @NonNull
    @Override
    public SailingInfoModel transform(SailDateInfo item) {
        Preconditions.notNull(item);
        SailingInfoModel sailingInfoModel = new SailingInfoModel();
        sailingInfoModel.setDuration(item.getDuration());
        sailingInfoModel.setShipCode(item.getShipCode());
        setItinerary(sailingInfoModel, item);
        return sailingInfoModel;
    }

    private void setItinerary(SailingInfoModel sailingModel, SailDateInfo sailingDomain) {
        ItineraryModel itineraryModel = new ItineraryModel();
        itineraryModel.setDescription(sailingDomain.getItinerary().getDescription());
        setEvents(itineraryModel, sailingDomain);
        sailingModel.setItinerary(itineraryModel);
    }

    private void setEvents(ItineraryModel itineraryModel, SailDateInfo domain) {
        List<SailDateEvent> domainEvents = domain.getItinerary().getEvents();
        if (CollectionUtils.isEmpty(domainEvents)) {
            return;
        }
        List<EventModel> modelEvents = new ArrayList<>();
        for (int i = 0; i < domainEvents.size(); i++) {
            EventModel eventModel = new EventModel();
            SailPort port = domainEvents.get(i).getPort();
            setPort(eventModel, port);
            if (DateUtils.isEqualsToCurrentDate(eventModel.getPort().getArrivalDate())) {
                itineraryModel.setIndexCurrentDay(i);
            }
            eventModel.setDay("Day " + domainEvents.get(i).getDay());
            modelEvents.add(eventModel);
        }
        itineraryModel.setEvents(modelEvents);
    }

    private void setPort(EventModel eventModel, SailPort domainPort) {
        if (domainPort == null) {
            return;
        }
        PortModel port = new PortModel();
        port.setPortType(domainPort.getPortType());
        port.setPortName(domainPort.getPortName());
        port.setArrivalDate(domainPort.getArrivalDate());
        port.setArrivalTime(domainPort.getArrivalTime());
        port.setDepartureDate(domainPort.getDepartureDate());
        port.setDepartureTime(domainPort.getDepartureTime());
        eventModel.setPort(port);
    }
}
