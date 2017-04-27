package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = MediaEntity.TABLE_NAME)
public class MenuSectionEntity extends Model {

    public static final String TABLE_NAME = "menu_section";

    public static final String COLUMN_SECTION_NAME = "section_name";
    public static final String COLUMN_SECTION_DESCRIPTION = "section_description";

    @Column(name = COLUMN_SECTION_NAME)
    private String sectionName;
    @Column(name = COLUMN_SECTION_DESCRIPTION)
    private String sectionDescription;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }
}

