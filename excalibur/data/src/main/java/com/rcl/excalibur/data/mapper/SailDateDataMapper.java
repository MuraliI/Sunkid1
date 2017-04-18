package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.SailDateEventResponse;
import com.rcl.excalibur.domain.SailDateEvent;

public class SailDateDataMapper extends BaseDataMapper<SailDateEvent, SailDateEventResponse> {
    private final SailPortDateMapper sailPortDateMapper;

    public SailDateDataMapper() {
        sailPortDateMapper = new SailPortDateMapper();
    }

    @Nullable
    @Override
    public SailDateEvent transform(SailDateEventResponse eventResponse) {
        if (eventResponse == null) {
            return null;
        }
        final SailDateEvent sailDateEvent = new SailDateEvent();
        sailDateEvent.setPort(sailPortDateMapper.transform(eventResponse.getPort()));
        sailDateEvent.setDay(eventResponse.getDay());
        return sailDateEvent;
    }
}
