package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseStatus {

    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("responseInternalMessage")
    @Expose
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
