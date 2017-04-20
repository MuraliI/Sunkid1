package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.ShipTimeEntity;
import com.rcl.excalibur.domain.ShipTime;

/**
 * Mapper class used to transform {@link ShipTimeEntity} (in the data layer) to {@link ShipTime} in the
 * domain layer.
 */
public class ShipTimeEntityDataMapper extends BaseDataMapper<ShipTime, ShipTimeEntity, Void> {

    @Override
    public ShipTime transform(final ShipTimeEntity entity, Void additionalArg) {
        if (entity == null) {
            return null;
        }
        final ShipTime shipTime = new ShipTime();
        shipTime.setOffset(entity.getOffset());
        return shipTime;
    }
}
