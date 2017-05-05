package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = MenuSectionEntity.TABLE_NAME)
public class MenuSectionEntity extends Model {

    public static final String TABLE_NAME = "menu_section";

    public static final String COLUMN_SECTION_NAME = "section_name";
    public static final String COLUMN_SECTION_DESCRIPTION = "section_description";
    public static final String COLUMN_MENU_SECTION_MEDIA = "menu_section_media";
    public static final String COLUMN_MENU = "menu";


    @Column(name = COLUMN_SECTION_NAME)
    private String sectionName;
    @Column(name = COLUMN_SECTION_DESCRIPTION)
    private String sectionDescription;
    @Column(name = COLUMN_MENU_SECTION_MEDIA)
    private MediaEntity sectionMediaEntity;
    @Column(name = COLUMN_MENU, onDelete = Column.ForeignKeyAction.CASCADE)
    private MenuEntity menuEntity;

    public MenuSectionEntity() {
        super();
    }


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

    public MenuEntity getMenuEntity() {
        return menuEntity;
    }

    public void setMenuEntity(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }

    public List<MenuItemEntity> getMenuItemEntity() {
        return getMany(MenuItemEntity.class, MenuItemEntity.COLUMN_MENU_SECTION);
    }

    public MediaEntity getSectionMediaEntity() {
        return sectionMediaEntity;
    }

    public void setSectionMediaEntity(MediaEntity sectionMediaEntity) {
        this.sectionMediaEntity = sectionMediaEntity;
    }
}

