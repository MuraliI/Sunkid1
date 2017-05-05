
package com.rcl.excalibur.domain;


import java.util.List;

public class MenuSection {

    private List<MenuItem> menuItem;
    private Media sectionMedia;
    private String sectionName;
    private String sectionDescription;

    public List<MenuItem> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(List<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectiondescription() {
        return sectionDescription;
    }

    public void setSectiondescription(String sectiondescription) {
        sectionDescription = sectiondescription;
    }

    public Media getSectionMedia() {
        return sectionMedia;
    }

    public void setSectionMedia(Media sectionMedia) {
        this.sectionMedia = sectionMedia;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }
}
