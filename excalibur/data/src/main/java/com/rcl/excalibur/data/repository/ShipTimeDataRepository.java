package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.ShipTimeEntity;
import com.rcl.excalibur.data.mapper.ShipTimeEntityDataMapper;
import com.rcl.excalibur.domain.ShipTime;
import com.rcl.excalibur.domain.repository.ShipTimeRepository;

public class ShipTimeDataRepository extends BaseDataRepository<ShipTime, ShipTimeEntity, Void, ShipTimeEntityDataMapper>
        implements ShipTimeRepository {

    public ShipTimeDataRepository() {
        super(new ShipTimeEntityDataMapper(), ShipTimeEntity.class);
    }

    @Override
    public void update(@NonNull String offset) {
        ShipTimeEntity entity = new Select()
                .from(ShipTimeEntity.class)
                .executeSingle();
        if (entity == null) {
            entity = new ShipTimeEntity();
        }
        entity.setOffset(offset);
        entity.save();
    }


    @Override
    public void create(@NonNull ShipTime value) {

    }

    @Override
    public void deleteAll() {

    }
}
