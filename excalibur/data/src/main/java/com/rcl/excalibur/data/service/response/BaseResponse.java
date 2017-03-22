package com.rcl.excalibur.data.service.response;

public class BaseResponse {

    private String responseStatus;

    public BaseResponse() {
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }
}
