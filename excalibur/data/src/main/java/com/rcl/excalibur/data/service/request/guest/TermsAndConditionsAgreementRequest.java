package com.rcl.excalibur.data.service.request.guest;

public class TermsAndConditionsAgreementRequest {

    private long acceptTime;
    private String version;

    public long getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(long acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

