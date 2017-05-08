package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.ShipStatsResponse;
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
        return shipStatsInfo;
    }
}
