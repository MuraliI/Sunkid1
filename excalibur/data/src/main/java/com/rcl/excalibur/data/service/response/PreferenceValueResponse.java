
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PreferenceValueResponse {

    @SerializedName("PreferenceValueCode")
    private String preferenceValueCode;
    @SerializedName("preferenceValueID")
    private String preferenceValueId;
    private String preferenceValueName;

    public PreferenceValueResponse() {
    }

    public String getPreferenceValueCode() {
        return preferenceValueCode;
    }

    public void setPreferenceValueCode(String preferenceValueCode) {
        this.preferenceValueCode = preferenceValueCode;
    }

    public String getPreferenceValueId() {
        return preferenceValueId;
    }

    public void setPreferenceValueId(String preferenceValueId) {
        this.preferenceValueId = preferenceValueId;
    }

    public String getPreferenceValueName() {
        return preferenceValueName;
    }

    public void setPreferenceValueName(String preferenceValueName) {
        this.preferenceValueName = preferenceValueName;
    }
}
