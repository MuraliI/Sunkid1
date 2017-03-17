
package com.rcl.excalibur.data.service.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class PreferencesResponse {

    @SerializedName("preferenceID")
    private String preferenceId;
    private String preferenceName;
    @SerializedName("PreferenceValueCode")
    private String preferenceValueCode;
    private Boolean mandatoryPreferenceFlag;
    private String preferenceType;
    private List<PreferenceValueResponse> preferenceValue;


    public PreferencesResponse() {
    }

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

    public String getPreferenceValueCode() {
        return preferenceValueCode;
    }

    public void setPreferenceValueCode(String preferenceValueCode) {
        this.preferenceValueCode = preferenceValueCode;
    }

    public Boolean getMandatoryPreferenceFlag() {
        return mandatoryPreferenceFlag;
    }

    public void setMandatoryPreferenceFlag(Boolean mandatoryPreferenceFlag) {
        this.mandatoryPreferenceFlag = mandatoryPreferenceFlag;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(String preferenceType) {
        this.preferenceType = preferenceType;
    }

    public List<PreferenceValueResponse> getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(List<PreferenceValueResponse> preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
}
