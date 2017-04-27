
package com.rcl.excalibur.domain;


import java.util.List;

public class MenuSection {

    private List<MenuItem> menuItem;
    private SectionMedia sectionMedia;
    private String sectionName;
    private String sectionDescription;

    public List<MenuItem> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(List<MenuItem> menuItem) {
        this.menuItem = menuItem;
    }

    public SectionMedia getSectionMedia() {
        return sectionMedia;
    }

    public void setSectionMedia(SectionMedia sectionMedia) {
        this.sectionMedia = sectionMedia;
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

}
