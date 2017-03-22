package com.rcl.excalibur.domain;


public class ProductPreferenceValue {

    private long preferenceValueId;
    private String preferenceValueName;
    private String preferenceValueCode;

    public long getPreferenceValueId() {
        return preferenceValueId;
    }

    public void setPreferenceValueId(long preferenceValueId) {
        this.preferenceValueId = preferenceValueId;
    }

    public String getPreferenceValueName() {
        return preferenceValueName;
    }

    public void setPreferenceValueName(String preferenceValueName) {
        this.preferenceValueName = preferenceValueName;
    }

    public String getPreferenceValueCode() {
        return preferenceValueCode;
    }

    public void setPreferenceValueCode(String preferenceValueCode) {
        this.preferenceValueCode = preferenceValueCode;
    }
}
