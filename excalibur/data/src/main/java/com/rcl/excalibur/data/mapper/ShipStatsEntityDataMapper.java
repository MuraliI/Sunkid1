package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.entity.ShipStatsEntity;
import com.rcl.excalibur.domain.ShipStatsInfo;

public class ShipStatsEntityDataMapper extends BaseDataMapper<ShipStatsInfo, ShipStatsEntity, Void> {

    @Nullable
    @Override
    public ShipStatsInfo transform(ShipStatsEntity shipStatsEntity, Void additionalArg) {
        if (shipStatsEntity == null)
            return null;

        ShipStatsInfo shipStatsInfo = new ShipStatsInfo();
        shipStatsInfo.setShipName(shipStatsEntity.getShipName());
        return shipStatsInfo;
    }
}
