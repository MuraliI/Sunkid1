package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.data.entity.ShipStatsEntity;
import com.rcl.excalibur.data.mapper.ShipStatsEntityDataMapper;
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
        entity.save();
    }

    @Override
    public ShipStatsInfo get() {
        ShipStatsEntity shipStatsEntityList = new Select()
                .all()
                .from(SailDateInfoEntity.class)
                .executeSingle();
        return getMapper().transform(shipStatsEntityList, null);
    }

    @Override
    public void deleteAll() {
        new Delete().from(ShipStatsEntity.class).execute();
    }
}
