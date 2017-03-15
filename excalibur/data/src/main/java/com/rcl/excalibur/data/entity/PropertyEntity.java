package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = PropertyEntity.TABLE_NAME)
public class PropertyEntity extends Model {

    public static final String TABLE_NAME = "property";
    public static final String COLUMN_DISCOVER_ITEM = "discover_item";
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_VALUE = "value";

    @Column(name = COLUMN_DISCOVER_ITEM)
    private DiscoverItemEntity discoverItemEntity;
    @Column(name = COLUMN_KEY)
    private String key;
    @Column(name = COLUMN_VALUE)
    private String value;

    public PropertyEntity() {
        super();
    }

    public DiscoverItemEntity getDiscoverItemEntity() {
        return discoverItemEntity;
    }

    public void setDiscoverItemEntity(DiscoverItemEntity discoverItemEntity) {
        this.discoverItemEntity = discoverItemEntity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
