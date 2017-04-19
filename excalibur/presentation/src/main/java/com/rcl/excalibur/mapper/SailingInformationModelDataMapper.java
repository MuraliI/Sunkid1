package com.rcl.excalibur.mapper;

import android.support.annotation.NonNull;

import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.model.SailingInfoModel;

public class SailingInformationModelDataMapper extends BaseModelDataMapper<SailDateInfo, SailingInfoModel> {
    @NonNull
    @Override
    public SailingInfoModel transform(SailDateInfo item) {
        Preconditions.notNull(item);
        return null;
    }
}
