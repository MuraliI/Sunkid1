package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.rcl.excalibur.data.entity.SailDateInfoEntity;
import com.rcl.excalibur.data.mapper.SaildDateInfoEntityDataMapper;
import com.rcl.excalibur.domain.SailDateInfoEvent;

public class SailDateDataRepository extends BaseDataRepository<SailDateInfoEvent, SailDateInfoEntity, SaildDateInfoEntityDataMapper> {

    protected SailDateDataRepository(SaildDateInfoEntityDataMapper dataMapper, Class<SailDateInfoEntity> claz) {
        super(dataMapper, claz);
    }

    @Override
    public void create(@NonNull SailDateInfoEvent input) {

    }
}
