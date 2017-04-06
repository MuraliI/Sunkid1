package com.rcl.excalibur.data.service.request.guest;

public class TermsAndConditionsAgreementRequest {

    private Integer acceptTime;
    private String version;

    public Integer getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Integer acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

