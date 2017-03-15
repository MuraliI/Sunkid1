package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = PriceEntity.TABLE_NAME)
public class PriceEntity extends Model {

    public static final String TABLE_NAME = "price";
    public static final String COLUMN_DISCOVER_ITEM = "discover_item";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_VALUE = "value";

    @Column(name = COLUMN_DISCOVER_ITEM)
    public DiscoverItemEntity discoverItemEntity;
    @Column(name = COLUMN_TITLE)
    public String title;
    @Column(name = COLUMN_VALUE)
    public String value;

    public PriceEntity() {
        super();
    }

    public DiscoverItemEntity getDiscoverItemEntity() {
        return discoverItemEntity;
    }

    public void setDiscoverItemEntity(DiscoverItemEntity discoverItemEntity) {
        this.discoverItemEntity = discoverItemEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
