package com.rcl.excalibur.domain;


public class ChildCategory {
    private Items items;

    public Items getItems() {
        if (items == null) {
            items = new Items();
        }
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }
}
