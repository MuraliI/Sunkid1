package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = SectionMediaEntity.TABLE_NAME)
public class SectionMediaEntity extends Model {

    public static final String TABLE_NAME = "section_media";

    public static final String COLUMN_MENU_SECTION = "menu_section";
    public static final String COLUMN_MENU_MEDIA_ITEM = "media_item";

    @Column(name = COLUMN_MENU_SECTION)
    private SectionMediaEntity sectionMediaEntity;
    @Column(name = COLUMN_MENU_MEDIA_ITEM)
    private MediaEntity menuMedia;

    public SectionMediaEntity getSectionMediaEntity() {
        return sectionMediaEntity;
    }

    public void setSectionMediaEntity(SectionMediaEntity sectionMediaEntity) {
        this.sectionMediaEntity = sectionMediaEntity;
    }

    public MediaEntity getMenuMedia() {
        return menuMedia;
    }

    public void setMenuMedia(MediaEntity menuMedia) {
        this.menuMedia = menuMedia;
    }
}
