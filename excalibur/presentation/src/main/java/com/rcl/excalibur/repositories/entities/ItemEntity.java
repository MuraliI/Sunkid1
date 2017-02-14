package com.rcl.excalibur.repositories.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "item")
public class ItemEntity extends Model {

    @Column(name = "Name")
    public String name;

    public ItemEntity() {
        super();
    }

    public ItemEntity(String name) {
        this.name = name;
    }
}
