package com.rcl.excalibur.data.service.response;

public class BaseResponse {

    private ResponseStatus responseStatus;

    public BaseResponse() {
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
