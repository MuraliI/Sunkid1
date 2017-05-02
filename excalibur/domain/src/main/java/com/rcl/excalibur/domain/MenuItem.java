
package com.rcl.excalibur.domain;

import java.util.List;

public class MenuItem {

    private List<MenuItemAdvisoryTag> menuItemAdvisoryTags;
    private Media menuItemMedia;
    private String menuItemDescription;
    private String menuItemTitle;

    public List<MenuItemAdvisoryTag> getMenuItemAdvisoryTags() {
        return menuItemAdvisoryTags;
    }

    public void setMenuItemAdvisoryTags(List<MenuItemAdvisoryTag> menuItemAdvisoryTags) {
        this.menuItemAdvisoryTags = menuItemAdvisoryTags;
    }

    public String getMenuItemTitle() {
        return menuItemTitle;
    }

    public void setMenuItemTitle(String menuItemTitle) {
        this.menuItemTitle = menuItemTitle;
    }

    public String getMenuItemDescription() {
        return menuItemDescription;
    }

    public void setMenuItemDescription(String menuItemDescription) {
        this.menuItemDescription = menuItemDescription;
    }

    public Media getMenuItemMedia() {
        return menuItemMedia;
    }

    public void setMenuItemMedia(Media menuItemMedia) {
        this.menuItemMedia = menuItemMedia;
    }
}
