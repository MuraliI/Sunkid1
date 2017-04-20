package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailDateItinerary;
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
        if (item != null) {
            sailingInfoModel.setDuration(item.getDuration());
            sailingInfoModel.setShipCode(item.getShipCode());
            setItinerary(sailingInfoModel, item);
        }
        return sailingInfoModel;
    }

    private void setItinerary(SailingInfoModel sailingModel, SailDateInfo sailingDomain) {
        ItineraryModel itineraryModel = new ItineraryModel();
        SailDateItinerary domainItinerary = sailingDomain.getItinerary();
        if (sailingDomain == null || domainItinerary == null) {
            return;
        }
        String description = domainItinerary.getDescription();
        if (!TextUtils.isEmpty(description)) {
            itineraryModel.setDescription(description);
        }
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
            if (eventModel.getPort() != null && eventModel.getPort().getArrivalDate() != null) {
                if (DateUtils.isEqualsToCurrentDate(eventModel.getPort().getArrivalDate())) {
                    itineraryModel.setIndexCurrentDay(i);
                }
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
        String portCode = domainPort.getPortCode();
        String portType = domainPort.getPortType();
        String portName = domainPort.getPortName();
        String arrivalDate = domainPort.getArrivalDate();
        String arrivalTime = domainPort.getArrivalTime();
        String departureDate = domainPort.getDepartureDate();
        String departureTime = domainPort.getDepartureTime();
        PortModel port = new PortModel();
        if (!TextUtils.isEmpty(portCode)) {
            port.setPortCode(portCode);
        }
        if (!TextUtils.isEmpty(portType)) {
            port.setPortType(portType);
        }
        if (!TextUtils.isEmpty(portName)) {
            port.setPortName(portName);
        }
        if (!TextUtils.isEmpty(arrivalDate)) {
            port.setArrivalDate(arrivalDate);
        }
        if (!TextUtils.isEmpty(arrivalTime)) {
            port.setArrivalTime(arrivalTime);
        }
        if (!TextUtils.isEmpty(departureDate)) {
            port.setDepartureDate(departureDate);
        }
        if (!TextUtils.isEmpty(departureTime)) {
            port.setDepartureTime(departureTime);
        }
        eventModel.setPort(port);
    }
}
