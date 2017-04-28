package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.rcl.excalibur.domain.MenuItemMedia;

import java.util.List;

@Table(name = MenuItemEntity.TABLE_NAME)
public class MenuItemEntity extends Model {

    public static final String TABLE_NAME = "menu_item";

    public static final String COLUMN_MENU_ITEM_DESCRIPTION = "menu_item_description";
    public static final String COLUMN_MENU_ITEM_TITLE = "menu_item_title";
    public static final String COLUMN_MENU_ITEM_MEDIA = "menu_item_media";

    public static final String COLUMN_MENU_SECTION = "menu_section";

    @Column(name = COLUMN_MENU_ITEM_DESCRIPTION)
    private String menuItemDescription;
    @Column(name = COLUMN_MENU_ITEM_TITLE)
    private String menuItemTitle;
    @Column(name = COLUMN_MENU_ITEM_MEDIA)
    private MenuItemMedia menuItemMedia;
    @Column(name = COLUMN_MENU_SECTION)
    private MenuSectionEntity menuSectionEntity;

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public String getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(String menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
    }

    public MenuItemMedia getMenuItemMedia() {
        return menuItemMedia;
    }

    public void setMenuItemMedia(MenuItemMedia menuItemMedia) {
        this.menuItemMedia = menuItemMedia;
    }
    public List<MenuItemAdvisoryTagEntity> getAdvisements() {
        return getMany(MenuItemAdvisoryTagEntity.class, MenuItemAdvisoryTagEntity.COLUMN_MENU_ITEM);
    }

    public MenuSectionEntity getMenuSectionEntity() {
        return menuSectionEntity;
    }

    public void setMenuSectionEntity(MenuSectionEntity menuSectionEntity) {
        this.menuSectionEntity = menuSectionEntity;
    }
}
