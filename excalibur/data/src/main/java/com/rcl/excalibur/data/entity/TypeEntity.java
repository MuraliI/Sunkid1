package com.rcl.excalibur.data.entity;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = TypeEntity.TABLE_NAME)
public class TypeEntity extends Model {

    public static final String TABLE_NAME = "type";
    public static final String COLUMN_TYPE_ID = "type_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";

    //TODO create unique constraints and indexes when services are defined
    @Column(name = COLUMN_TYPE_ID)
    private String typeId;
    @Column(name = COLUMN_NAME)
    private String name;
    //TODO create unique constraints and indexes when services are defined
    @Column(name = COLUMN_TYPE)
    private String type;

    public TypeEntity() {
        super();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
