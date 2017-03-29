package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = PreferenceValueEntity.TABLE_NAME)
public class PreferenceValueEntity extends Model {

    public static final String TABLE_NAME = "preference_value";
    public static final String COLUMN_PREFERENCE_VALUE_ID = "preference_value_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_PREFERENCE = "preference";

    @Column(name = COLUMN_PREFERENCE_VALUE_ID)
    private long preferenceValueId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_CODE)
    private String code;
    @Column(name = COLUMN_PREFERENCE)
    private PreferenceEntity preference;

    public PreferenceValueEntity() {
        super();
    }

    public long getPreferenceValueId() {
        return preferenceValueId;
    }

    public void setPreferenceValueId(long preferenceValueId) {
        this.preferenceValueId = preferenceValueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PreferenceEntity getPreference() {
        return preference;
    }

    public void setPreference(PreferenceEntity preference) {
        this.preference = preference;
    }
}
