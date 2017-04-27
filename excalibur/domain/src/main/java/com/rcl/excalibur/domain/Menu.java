
package com.rcl.excalibur.domain;

import java.util.List;

public class Menu {

    private String dayNumber;
    private List<MenuAdvisoryTag> menuAdvisoryTag;
    private MenuMedia menuMedia;
    private String menuName;
    private List<MenuSection> menuSections;

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public List<MenuAdvisoryTag> getMenuAdvisoryTag() {
        return menuAdvisoryTag;
    }

    public void setMenuAdvisoryTag(List<MenuAdvisoryTag> menuAdvisoryTag) {
        this.menuAdvisoryTag = menuAdvisoryTag;
    }

    public MenuMedia getMenuMedia() {
        return menuMedia;
    }

    public void setMenuMedia(MenuMedia menuMedia) {
        this.menuMedia = menuMedia;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<MenuSection> getMenuSections() {
        return menuSections;
    }

    public void setMenuSections(List<MenuSection> menuSections) {
        this.menuSections = menuSections;
    }

}
