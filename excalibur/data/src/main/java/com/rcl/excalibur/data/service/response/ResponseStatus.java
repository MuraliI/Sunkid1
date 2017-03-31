package com.rcl.excalibur.data.service.response;

public class ResponseStatus {
    private String responseStatus;
    private String responseCode;
    private String responseMessage;
    private String responseInternalMessage;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseInternalMessage() {
        return responseInternalMessage;
    }

    public void setResponseInternalMessage(String responseInternalMessage) {
        this.responseInternalMessage = responseInternalMessage;
    }

}
