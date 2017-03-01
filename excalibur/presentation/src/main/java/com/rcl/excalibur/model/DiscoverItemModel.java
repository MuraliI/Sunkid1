package com.rcl.excalibur.model;


import android.os.Parcel;
import android.os.Parcelable;

public class DiscoverItemModel implements Parcelable {

    public static final Creator<DiscoverItemModel> CREATOR = new Creator<DiscoverItemModel>() {
        @Override
        public DiscoverItemModel createFromParcel(Parcel in) {
            return new DiscoverItemModel(in);
        }

        @Override
        public DiscoverItemModel[] newArray(int size) {
            return new DiscoverItemModel[size];
        }
    };

    public DiscoverItemModel() {
    }

    protected DiscoverItemModel(Parcel in) {
        //TODO in.readString(); with all the plan fields.
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
