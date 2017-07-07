package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.ShipLocationEntity;
import com.rcl.excalibur.data.entity.ShipLocationStatsEntity;
import com.rcl.excalibur.data.entity.ShipStatsEntity;
import com.rcl.excalibur.domain.ShipLocationInfo;
import com.rcl.excalibur.domain.ShipLocationStatsInfo;
import com.rcl.excalibur.domain.ShipStatsInfo;

public class ShipStatsEntityDataMapper extends BaseDataMapper<ShipStatsInfo, ShipStatsEntity, Void> {

    @Nullable
    @Override
    public ShipStatsInfo transform(ShipStatsEntity shipStatsEntity, Void additionalArg) {
        if (shipStatsEntity == null)
            return null;

        ShipStatsInfo shipStatsInfo = new ShipStatsInfo();
        shipStatsInfo.setShipName(shipStatsEntity.getShipName());
        shipStatsInfo.setNextPortName(shipStatsEntity.getNextPortName());
        shipStatsInfo.setNextPortArrivalTime(shipStatsEntity.getNextPortArrivalTime());
        shipStatsInfo.setLastPortName(shipStatsEntity.getLastPortName());
        shipStatsInfo.setLastPortDepartureTime(shipStatsEntity.getLastPortDepartureTime());
        shipStatsInfo.setDistanceToTheNextPort(shipStatsEntity.getDistanceToTheNextPort());
        shipStatsInfo.setCurrentPortName(shipStatsEntity.getCurrentPortName());
        shipStatsInfo.setShipLocation(transform(shipStatsEntity.getShipLocation()));
        shipStatsInfo.setShipLocationStats(transform(shipStatsEntity.getShipLocationStats()));
        return shipStatsInfo;
    }

    private ShipLocationInfo transform(ShipLocationEntity shipLocationEntity) {
        ShipLocationInfo shipLocationInfo = new ShipLocationInfo();
        shipLocationInfo.setLatitude(shipLocationEntity.getLatitude());
        shipLocationInfo.setLongitude(shipLocationEntity.getLongitude());
        shipLocationInfo.setLocationTimestamp(shipLocationEntity.getLocationTimestamp());
        return shipLocationInfo;
    }

    private ShipLocationStatsInfo transform(ShipLocationStatsEntity shipLocationStatsEntity) {
        ShipLocationStatsInfo shipLocationStatsInfo = new ShipLocationStatsInfo();
        shipLocationStatsInfo.setAisNavigationalStatus(shipLocationStatsEntity.getAisNavigationalStatus());
        shipLocationStatsInfo.setSpeed(shipLocationStatsEntity.getSpeed());
        shipLocationStatsInfo.setHeading(shipLocationStatsEntity.getHeading());
        shipLocationStatsInfo.setCourse(shipLocationStatsEntity.getCourse());
        return shipLocationStatsInfo;
    }
}
