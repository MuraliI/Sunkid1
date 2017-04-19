package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.SailingInfoResponse;
import com.rcl.excalibur.domain.SailDateInfo;

public class SailDateInfoDataMapper extends BaseDataMapper<SailDateInfo, SailingInfoResponse> {
    private SailDateItineraryDataMapper sailDateItineraryDataMapper;

    public SailDateInfoDataMapper() {
        sailDateItineraryDataMapper = new SailDateItineraryDataMapper();
    }

    @Nullable
    @Override
    public SailDateInfo transform(SailingInfoResponse infoResponse) {
        if (infoResponse == null)
            return null;

        SailDateInfo sailDateInfoEvent = new SailDateInfo();
        sailDateInfoEvent.setShipCode(infoResponse.getShipCode());
        sailDateInfoEvent.setDuration(infoResponse.getDuration());
        sailDateInfoEvent.setItinerary(sailDateItineraryDataMapper.transform(infoResponse.getItinerary()));
        return sailDateInfoEvent;
    }
}
