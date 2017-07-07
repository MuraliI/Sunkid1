package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = AdvisementEntity.TABLE_NAME)
public class AdvisementEntity extends Model {

    public static final String TABLE_NAME = "advisement";

    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_ADVISEMENT_ID = "advisement_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEDIA = "media";

    @Column(name = COLUMN_ADVISEMENT_ID)
    private String advisementId;
    @Column(name = COLUMN_NAME)
    private String name;
    @Column(name = COLUMN_TYPE)
    private String type;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_MEDIA)
    private MediaEntity media;
    @Column(name = COLUMN_PRODUCT)
    private ProductEntity product;

    public AdvisementEntity() {
        super();
    }

    public String getAdvisementId() {
        return advisementId;
    }

    public void setAdvisementId(String advisementId) {
        this.advisementId = advisementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaEntity getMedia() {
        return media;
    }

    public void setMedia(MediaEntity media) {
        this.media = media;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}

