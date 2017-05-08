package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = MenuEntity.TABLE_NAME)
public class MenuEntity extends Model {

    public static final String TABLE_NAME = "menu";

    public static final String COLUMN_VENUE_CODE = "venue_code";
    public static final String COLUMN_DAY_NUMBER = "day_number";
    public static final String COLUMN_MENU_NAME = "menu_name";
    public static final String COLUMN_MEDIA = "menu_media";

    @Column(name = COLUMN_VENUE_CODE)
    private String venueCode;
    @Column(name = COLUMN_DAY_NUMBER)
    private String dayNumber;
    @Column(name = COLUMN_MENU_NAME)
    private String menuName;
    @Column(name = COLUMN_MEDIA)
    private MediaEntity menuMedia;

    public MenuEntity() {
        super();
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MediaEntity getMenuMedia() {
        return menuMedia;
    }

    public void setMenuMedia(MediaEntity menuMedia) {
        this.menuMedia = menuMedia;
    }

    public List<MenuAdvisoryTagEntity> getMenuAdvisoryTag() {
        return getMany(MenuAdvisoryTagEntity.class, MenuAdvisoryTagEntity.COLUMN_MENU);
    }

    public List<MenuSectionEntity> getMenuSection() {
        return getMany(MenuSectionEntity.class, MenuSectionEntity.COLUMN_MENU);
    }

    public String getVenueCode() {
        return venueCode;
    }

    public void setVenueCode(String venueCode) {
        this.venueCode = venueCode;
    }
}
