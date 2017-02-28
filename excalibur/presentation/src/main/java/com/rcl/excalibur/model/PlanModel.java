package com.rcl.excalibur.model;


import android.os.Parcel;
import android.os.Parcelable;

public class PlanModel implements Parcelable {

    public static final Creator<PlanModel> CREATOR = new Creator<PlanModel>() {
        @Override
        public PlanModel createFromParcel(Parcel in) {
            return new PlanModel(in);
        }

        @Override
        public PlanModel[] newArray(int size) {
            return new PlanModel[size];
        }
    };

    public PlanModel() {
    }

    protected PlanModel(Parcel in) {
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
