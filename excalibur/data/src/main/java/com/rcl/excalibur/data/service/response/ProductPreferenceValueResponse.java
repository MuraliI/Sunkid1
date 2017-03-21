package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class ProductPreferenceValueResponse {
    @SerializedName("preferenceValueID")
    private String preferenceValueId;
    private boolean preferenceValueName;
    private boolean preferenceValueCode;

    public String getPreferenceValueId() {
        return preferenceValueId;
    }

    public void setPreferenceValueId(String preferenceValueId) {
        this.preferenceValueId = preferenceValueId;
    }

    public boolean isPreferenceValueName() {
        return preferenceValueName;
    }

    public void setPreferenceValueName(boolean preferenceValueName) {
        this.preferenceValueName = preferenceValueName;
    }

    public boolean isPreferenceValueCode() {
        return preferenceValueCode;
    }

    public void setPreferenceValueCode(boolean preferenceValueCode) {
        this.preferenceValueCode = preferenceValueCode;
    }
}
