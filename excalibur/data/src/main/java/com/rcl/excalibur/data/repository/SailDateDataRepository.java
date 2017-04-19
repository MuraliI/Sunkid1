package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.EventEntity;
import com.rcl.excalibur.data.entity.ItineraryEntity;
import com.rcl.excalibur.data.entity.PortEntity;
import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.data.mapper.SaildDateInfoEntityDataMapper;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.SailDateEvent;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.SailDateItinerary;
import com.rcl.excalibur.domain.repository.SailDateRepository;

import java.util.List;

public class SailDateDataRepository extends BaseDataRepository<SailDateInfo, SailDateInfoEntity, Void,  SaildDateInfoEntityDataMapper>
        implements SailDateRepository {

    public SailDateDataRepository() {
        super(new SaildDateInfoEntityDataMapper(), SailDateInfoEntity.class);

    }

    @Override
    public void create(@NonNull SailDateInfo sailDateInfoEvent) {

        final SailDateInfoEntity entity = new SailDateInfoEntity();
        entity.setDuration(sailDateInfoEvent.getDuration());
        entity.setShipCode(sailDateInfoEvent.getShipCode());
        //Itinerary
        create(entity, sailDateInfoEvent.getItinerary());
        entity.save();

    }

    @Override
    public SailDateInfo get() {
        SailDateInfoEntity sailDateInfoEntityList = new Select()
                .all()
                .from(SailDateInfoEntity.class)
                .executeSingle();
        return getMapper().transform(sailDateInfoEntityList, null);
    }


    private void create(final SailDateInfoEntity entity, final SailDateItinerary itinerary) {
        if (itinerary == null) {
            return;
        }
        final ItineraryEntity itineraryEntity = new ItineraryEntity();
        itineraryEntity.save();
        itineraryEntity.setDescription(itinerary.getDescription());

        if (!CollectionUtils.isEmpty(itinerary.getEvents())) {
            for (SailDateEvent sailDateEvent : itinerary.getEvents()) {
                final EventEntity eventEntity = new EventEntity();
                eventEntity.setIntinerary(itineraryEntity);
                eventEntity.setDay(sailDateEvent.getDay());
                final PortEntity portEntity = new PortEntity();
                portEntity.setPortName(sailDateEvent.getPort().getPortName());
                portEntity.setPortType(sailDateEvent.getPort().getPortType());
                portEntity.setPortCode(sailDateEvent.getPort().getPortCode());
                portEntity.setArrivalDate(sailDateEvent.getPort().getArrivalDate());
                portEntity.setDepartureDate(sailDateEvent.getPort().getDepartureDate());
                portEntity.setArrivalTime(sailDateEvent.getPort().getArrivalTime());
                portEntity.setDepartureTime(sailDateEvent.getPort().getDepartureTime());
                portEntity.save();
                eventEntity.setPort(portEntity);
                eventEntity.save();

            }
        }
        itineraryEntity.save();
        entity.setItinerary(itineraryEntity);
    }


    @Override
    public List<SailDateInfo> getAll() {
        List<SailDateInfoEntity> sailDateInfoEntityList = new Select()
                .all()
                .from(SailDateInfoEntity.class)
                .execute();
        return getMapper().transform(sailDateInfoEntityList, null);
    }

    @Override
    public void deleteAll() {
        new Delete().from(SailDateInfoEntity.class).execute();

    }

}
