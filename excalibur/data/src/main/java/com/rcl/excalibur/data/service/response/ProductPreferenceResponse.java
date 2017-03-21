package com.rcl.excalibur.data.service.response;



public class ProductPreferenceResponse {

    private String preferenceID;
    private String preferenceName;
    private boolean mandatoryPreferenceFlag;
    private String preferenceType;
    private ProductPreferenceValueResponse preferenceValue;

    public String getPreferenceID() {
        return preferenceID;
    }

    public void setPreferenceID(String preferenceID) {
        this.preferenceID = preferenceID;
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

    public ProductPreferenceValueResponse getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(ProductPreferenceValueResponse preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
}
