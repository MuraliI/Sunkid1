package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.ShipLocationResponse;
import com.rcl.excalibur.data.service.response.ShipLocationStatsResponse;
import com.rcl.excalibur.data.service.response.ShipStatsResponse;
import com.rcl.excalibur.domain.ShipLocationInfo;
import com.rcl.excalibur.domain.ShipLocationStatsInfo;
import com.rcl.excalibur.domain.ShipStatsInfo;

public class ShipStatsDataMapper extends BaseDataMapper<ShipStatsInfo, ShipStatsResponse, Void> {

    @Nullable
    @Override
    public ShipStatsInfo transform(ShipStatsResponse shipStatsResponse, Void additionalArg) {
        if (shipStatsResponse == null)
            return null;

        ShipStatsInfo shipStatsInfo = new ShipStatsInfo();
        shipStatsInfo.setShipName(shipStatsResponse.getShipName());
        shipStatsInfo.setNextPortName(shipStatsResponse.getNextPortName());
        shipStatsInfo.setNextPortArrivalTime(shipStatsResponse.getNextPortArrivalTime());
        shipStatsInfo.setLastPortName(shipStatsResponse.getLastPortName());
        shipStatsInfo.setLastPortDepartureTime(shipStatsResponse.getLastPortDepartureTime());
        shipStatsInfo.setDistanceToTheNextPort(shipStatsResponse.getDistanceToTheNextPort());
        shipStatsInfo.setCurrentPortName(shipStatsResponse.getCurrentPortName());
        shipStatsInfo.setShipLocation(transform(shipStatsResponse.getShipLocation()));
        shipStatsInfo.setShipLocationStats(transform(shipStatsResponse.getShipLocationStats()));
        return shipStatsInfo;
    }

    private ShipLocationInfo transform(ShipLocationResponse shipLocationResponse) {
        ShipLocationInfo shipLocationInfo = new ShipLocationInfo();
        shipLocationInfo.setLatitude(shipLocationResponse.getLatitude());
        shipLocationInfo.setLongitude(shipLocationResponse.getLongitude());
        shipLocationInfo.setLocationTimestamp(shipLocationResponse.getLocationTimestamp());
        return shipLocationInfo;
    }

    private ShipLocationStatsInfo transform(ShipLocationStatsResponse shipLocationStatsResponse) {
        ShipLocationStatsInfo shipLocationStatsInfo = new ShipLocationStatsInfo();
        shipLocationStatsInfo.setAisNavigationalStatus(shipLocationStatsResponse.getAisNavigationalStatus());
        shipLocationStatsInfo.setSpeed(shipLocationStatsResponse.getSpeed());
        shipLocationStatsInfo.setHeading(shipLocationStatsResponse.getHeading());
        shipLocationStatsInfo.setCourse(shipLocationStatsResponse.getCourse());
        return shipLocationStatsInfo;
    }
}
