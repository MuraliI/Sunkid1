package com.rcl.excalibur.domain;



public class ProductPreferenceValue {

    private String preferenceValueID;
    private boolean preferenceValueName;
    private boolean PreferenceValueCode;

    public String getPreferenceValueID() {
        return preferenceValueID;
    }

    public void setPreferenceValueID(String preferenceValueID) {
        this.preferenceValueID = preferenceValueID;
    }

    public boolean isPreferenceValueName() {
        return preferenceValueName;
    }

    public void setPreferenceValueName(boolean preferenceValueName) {
        this.preferenceValueName = preferenceValueName;
    }

    public boolean isPreferenceValueCode() {
        return PreferenceValueCode;
    }

    public void setPreferenceValueCode(boolean preferenceValueCode) {
        PreferenceValueCode = preferenceValueCode;
    }
}
