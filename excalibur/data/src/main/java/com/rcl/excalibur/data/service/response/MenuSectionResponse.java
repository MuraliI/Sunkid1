
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
public class MenuSectionResponse {

    private List<MenuItemResponse> menuItem;
    private MediaResponse sectionMedia;
    private String sectionName;
    @SerializedName("sectiondescription")
    private String sectionDescription;

    public List<MenuItemResponse> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(List<MenuItemResponse> menuItem) {
        this.menuItem = menuItem;
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

    public MediaResponse getSectionMedia() {
        return sectionMedia;
    }

    public void setSectionMedia(MediaResponse sectionMedia) {
        this.sectionMedia = sectionMedia;
    }
}
