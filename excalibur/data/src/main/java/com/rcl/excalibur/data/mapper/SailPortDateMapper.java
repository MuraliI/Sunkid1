package com.rcl.excalibur.data.mapper;


import android.support.annotation.Nullable;

import com.rcl.excalibur.data.service.response.PortResponse;
import com.rcl.excalibur.domain.SailPort;

public class SailPortDateMapper extends BaseDataMapper<SailPort, PortResponse> {
    @Nullable
    @Override
    public SailPort transform(PortResponse portResponse) {
        SailPort sailPort = new SailPort();
        sailPort.setPortCode(portResponse.getPortCode());
        sailPort.setPortName(portResponse.getPortName());
        sailPort.setPortType(portResponse.getPortType());
        sailPort.setDepartureTime(portResponse.getDepartureTime());
        sailPort.setDepartureDate(portResponse.getDepartureDate());
        sailPort.setArrivalTime(portResponse.getArrivalTime());
        sailPort.setArrivalDate(portResponse.getArrivalDate());
        return sailPort;
    }
}
