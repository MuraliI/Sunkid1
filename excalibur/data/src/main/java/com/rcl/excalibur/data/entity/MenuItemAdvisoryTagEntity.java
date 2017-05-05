package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = MenuItemAdvisoryTagEntity.TABLE_NAME)
public class MenuItemAdvisoryTagEntity extends Model {

    public static final String TABLE_NAME = "menu_item_advisory_tag";

    public static final String COLUMN_MENU_ITEM = "menu_item";
    public static final String COLUMN_TITLE = "title";

    @Column(name = COLUMN_TITLE)
    private String title;

    @Column(name = COLUMN_MENU_ITEM, onDelete = Column.ForeignKeyAction.CASCADE)
    private MenuItemEntity menuItemEntity;

    public MenuItemAdvisoryTagEntity() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuItemEntity getMenuItemEntity() {
        return menuItemEntity;
    }

    public void setMenuItemEntity(MenuItemEntity menuItemEntity) {
        this.menuItemEntity = menuItemEntity;
    }
}
