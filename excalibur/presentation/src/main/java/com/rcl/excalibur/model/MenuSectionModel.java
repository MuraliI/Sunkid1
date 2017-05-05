package com.rcl.excalibur.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.rcl.excalibur.domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuSectionModel implements Parcelable {
    private String menuName;
    private List<MenuItem> menuSections;

    public String getMenuName() {
        return menuName;
    }

    public List<MenuItem> getMenuSections() {
        return menuSections;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuSections(List<MenuItem> menuSections) {
        this.menuSections = menuSections;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.menuName);
        dest.writeList(this.menuSections);
    }

    public MenuSectionModel() {
    }

    private MenuSectionModel(Parcel in) {
        this.menuName = in.readString();
        this.menuSections = new ArrayList<>();
        in.readList(this.menuSections, MenuItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<MenuSectionModel> CREATOR = new Parcelable.Creator<MenuSectionModel>() {
        @Override
        public MenuSectionModel createFromParcel(Parcel source) {
            return new MenuSectionModel(source);
        }

        @Override
        public MenuSectionModel[] newArray(int size) {
            return new MenuSectionModel[size];
        }
    };
}
