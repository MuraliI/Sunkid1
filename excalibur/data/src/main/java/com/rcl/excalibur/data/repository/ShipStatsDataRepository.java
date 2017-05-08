package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.ShipLocationEntity;
import com.rcl.excalibur.data.entity.ShipLocationStatsEntity;
import com.rcl.excalibur.data.entity.ShipStatsEntity;
import com.rcl.excalibur.data.mapper.ShipStatsEntityDataMapper;
import com.rcl.excalibur.domain.ShipLocationInfo;
import com.rcl.excalibur.domain.ShipLocationStatsInfo;
import com.rcl.excalibur.domain.ShipStatsInfo;
import com.rcl.excalibur.domain.repository.ShipStatsRepository;

public class ShipStatsDataRepository extends BaseDataRepository<ShipStatsInfo, ShipStatsEntity, Void, ShipStatsEntityDataMapper>
        implements ShipStatsRepository {


    public ShipStatsDataRepository() {
        super(new ShipStatsEntityDataMapper(), ShipStatsEntity.class);
    }

    @Override
    public void create(@NonNull ShipStatsInfo shipStatsInfo) {
        final ShipStatsEntity entity = new ShipStatsEntity();
        entity.setShipName(shipStatsInfo.getShipName());
        entity.setCurrentPortName(shipStatsInfo.getCurrentPortName());
        entity.setNextPortName(shipStatsInfo.getNextPortName());
        entity.setNextPortArrivalTime(shipStatsInfo.getNextPortArrivalTime());
        entity.setLastPortName(shipStatsInfo.getLastPortName());
        entity.setLastPortDepartureTime(shipStatsInfo.getLastPortDepartureTime());
        entity.setDistanceToTheNextPort(shipStatsInfo.getDistanceToTheNextPort());

        //ShipLocation
        create(entity, shipStatsInfo.getShipLocation());
        //ShipLocationStats
        create(entity, shipStatsInfo.getShipLocationStats());

        entity.save();
    }

    @Override
    public ShipStatsInfo get() {
        ShipStatsEntity shipStatsEntityList =
                new Select().from(ShipStatsEntity.class).executeSingle();
        return getMapper().transform(shipStatsEntityList, null);
    }

    @Override
    public void deleteAll() {
        new Delete().from(ShipStatsEntity.class).execute();
    }

    private void create(final ShipStatsEntity entity, final ShipLocationInfo shipLocationInfo) {
        if (shipLocationInfo == null) {
            return;
        }
        ShipLocationEntity shipLocationEntity = new ShipLocationEntity();
        shipLocationEntity.setLongitude(shipLocationInfo.getLongitude());
        shipLocationEntity.setLatitude(shipLocationInfo.getLatitude());
        shipLocationEntity.setLocationTimestamp(shipLocationInfo.getLocationTimestamp());
        shipLocationEntity.save();

        entity.setShipLocation(shipLocationEntity);
    }

    private void create(final ShipStatsEntity entity, final ShipLocationStatsInfo shipLocationStatsInfo) {
        if (shipLocationStatsInfo == null) {
            return;
        }
        ShipLocationStatsEntity shipLocationStatsEntity = new ShipLocationStatsEntity();
        shipLocationStatsEntity.setSpeed(shipLocationStatsInfo.getSpeed());
        shipLocationStatsEntity.setHeading(shipLocationStatsInfo.getHeading());
        shipLocationStatsEntity.setCourse(shipLocationStatsInfo.getCourse());
        shipLocationStatsEntity.setAisNavigationalStatus(shipLocationStatsInfo.getAisNavigationalStatus());
        shipLocationStatsEntity.save();

        entity.setShipLocationStats(shipLocationStatsEntity);
    }
}
