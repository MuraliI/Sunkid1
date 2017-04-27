package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = MediaEntity.TABLE_NAME)
public class MenuEntity extends Model {

    public static final String TABLE_NAME = "menu";

    public static final String COLUMN_DAY_NUMBER = "day_number";
    public static final String COLUMN_MENU_NAME = "menu_name";
    public static final String COLUMN_MEDIA = "media";

     /*
    private List<MenuSection> menuSections*/


    @Column(name = COLUMN_DAY_NUMBER)
    private String dayNumber;
    @Column(name = COLUMN_MENU_NAME)
    private String menuName;
    @Column(name = COLUMN_MEDIA)
    private MediaEntity menuMedia;

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

    public List<MenuAdvisoryTagEntity> getAdvisements() {
        return getMany(MenuAdvisoryTagEntity.class, MenuAdvisoryTagEntity.COLUMN_MENU);
    }
}
