package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.domain.SailDateInfoEvent;

public class SailDateInfoDataMapper extends BaseDataMapper<SailDateInfoEvent, SailingInfoResponse, Void> {
    private SailDateItineraryDataMapper sailDateItineraryDataMapper;

    public SailDateInfoDataMapper() {
        sailDateItineraryDataMapper = new SailDateItineraryDataMapper();
    }

    @Nullable
    @Override
    public SailDateInfoEvent transform(SailingInfoResponse infoResponse, Void additionalArg) {
        if (infoResponse == null)
            return null;

        SailDateInfoEvent sailDateInfoEvent = new SailDateInfoEvent();
        sailDateInfoEvent.setShipCode(infoResponse.getShipCode());
        sailDateInfoEvent.setDuration(infoResponse.getDuration());
        sailDateInfoEvent.setItinerary(sailDateItineraryDataMapper.transform(infoResponse.getItinerary(), null));
        return null;
    }
}
