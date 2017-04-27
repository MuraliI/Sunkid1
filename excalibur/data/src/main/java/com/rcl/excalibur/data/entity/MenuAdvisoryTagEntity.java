package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = MediaEntity.TABLE_NAME)
public class MenuAdvisoryTagEntity extends Model {

    public static final String TABLE_NAME = "menu_advisory_tag";

    public static final String COLUMN_MENU = "menu";
    public static final String COLUMN_TITLE = "title";

    @Column(name = COLUMN_TITLE)
    private String title;

    @Column(name = COLUMN_MENU)
    private MenuEntity menuEntity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
