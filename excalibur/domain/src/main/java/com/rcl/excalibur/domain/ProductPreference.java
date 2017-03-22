package com.rcl.excalibur.domain;


import java.util.List;

public class ProductPreference {

    private String preferenceId;
    private String preferenceName;
    private boolean mandatoryPreferenceFlag;
    private String preferenceType;
    private List<ProductPreferenceValue> preferenceValue;


    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getPreferenceName() {
        return preferenceName;
    }

    public void setPreferenceName(String preferenceName) {
        this.preferenceName = preferenceName;
    }

    public boolean isMandatoryPreferenceFlag() {
        return mandatoryPreferenceFlag;
    }

    public void setMandatoryPreferenceFlag(boolean mandatoryPreferenceFlag) {
        this.mandatoryPreferenceFlag = mandatoryPreferenceFlag;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(String preferenceType) {
        this.preferenceType = preferenceType;
    }

    public List<ProductPreferenceValue> getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(List<ProductPreferenceValue> preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
}
