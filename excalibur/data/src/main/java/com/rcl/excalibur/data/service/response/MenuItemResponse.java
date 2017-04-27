
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuItemResponse {

    private List<MenuItemAdvisoryTagResponse> menuItemAdvisoryTags;
    private MenuItemMediaResponse menuItemMedia;
    @SerializedName("menuItemdescription")
    private String menuItemDescription;
    @SerializedName("menuItemtitle")
    private String menuItemTitle;

    public List<MenuItemAdvisoryTagResponse> getMenuItemAdvisoryTags() {
        return menuItemAdvisoryTags;
    }

    public void setMenuItemAdvisoryTags(List<MenuItemAdvisoryTagResponse> menuItemAdvisoryTags) {
        this.menuItemAdvisoryTags = menuItemAdvisoryTags;
    }

    public MenuItemMediaResponse getMenuItemMedia() {
        return menuItemMedia;
    }

    public void setMenuItemMedia(MenuItemMediaResponse menuItemMedia) {
        this.menuItemMedia = menuItemMedia;
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
}
