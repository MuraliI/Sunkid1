package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = AccessibilityEntity.TABLE_NAME)
public class AccessibilityEntity extends Model {

    public static final String TABLE_NAME = "accessibility";
    public static final String COLUMN_DISCOVER_ITEM = "discover_item";
    public static final String COLUMN_TEXT = "text";

    @Column(name = COLUMN_DISCOVER_ITEM)
    private DiscoverItemEntity discoverItemEntity;
    @Column(name = COLUMN_TEXT)
    private String text;

    public AccessibilityEntity() {
        super();
    }

    public DiscoverItemEntity getDiscoverItemEntity() {
        return discoverItemEntity;
    }

    public String getText() {
        return text;
    }
}
