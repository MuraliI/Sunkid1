package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductPreferenceResponse {
    @SerializedName("preferenceID")
    private String preferenceId;
    private String preferenceName;
    private boolean mandatoryPreferenceFlag;
    private String preferenceType;
    private List<ProductPreferenceValueResponse> preferenceValue;

    public String getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(String preferenceID) {
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

    public List<ProductPreferenceValueResponse> getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(List<ProductPreferenceValueResponse> preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
}
