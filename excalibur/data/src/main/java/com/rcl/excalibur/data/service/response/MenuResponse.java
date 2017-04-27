
package com.rcl.excalibur.data.service.response;

import java.util.List;

public class MenuResponse {

    private String dayNumber;
    private List<MenuAdvisoryTagResponse> menuAdvisoryTag;
    private MenuMediaResponse menuMedia;
    private String menuName;
    private List<MenuSectionResponse> menuSections;

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public List<MenuAdvisoryTagResponse> getMenuAdvisoryTag() {
        return menuAdvisoryTag;
    }

    public void setMenuAdvisoryTag(List<MenuAdvisoryTagResponse> menuAdvisoryTag) {
        this.menuAdvisoryTag = menuAdvisoryTag;
    }

    public MenuMediaResponse getMenuMedia() {
        return menuMedia;
    }

    public void setMenuMedia(MenuMediaResponse menuMedia) {
        this.menuMedia = menuMedia;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<MenuSectionResponse> getMenuSections() {
        return menuSections;
    }

    public void setMenuSections(List<MenuSectionResponse> menuSections) {
        this.menuSections = menuSections;
    }

}
