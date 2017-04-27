
package com.rcl.excalibur.data.service.response;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class MenuSectionResponse {

    private List<MenuItemResponse> menuItem;
    private SectionMediaResponse sectionMedia;
    private String sectionName;
    @SerializedName("sectiondescription")
    private String sectionDescription;

    public List<MenuItemResponse> getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(List<MenuItemResponse> menuItem) {
        this.menuItem = menuItem;
    }

    public SectionMediaResponse getSectionMedia() {
        return sectionMedia;
    }

    public void setSectionMedia(SectionMediaResponse sectionMedia) {
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
