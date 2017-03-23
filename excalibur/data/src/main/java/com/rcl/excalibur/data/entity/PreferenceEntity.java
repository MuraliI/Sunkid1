package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = PreferenceEntity.TABLE_NAME)
public class PreferenceEntity extends Model {

    public static final String TABLE_NAME = "preference";
    public static final String COLUMN_PREFERENCE_ID = "preference_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_MANDATORY = "mandatory";
    public static final String COLUMN_TYPE = "type";

    @Column(name = COLUMN_PREFERENCE_ID)
    private long preferenceId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_MANDATORY)
    private boolean mandatory;

    public PreferenceEntity() {
        super();
    }

    public long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(long preferenceId) {
        this.preferenceId = preferenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<PreferenceValueEntity> getValues() {
        return getMany(PreferenceValueEntity.class, PreferenceValueEntity.COLUMN_PREFERENCE);
    }
}
