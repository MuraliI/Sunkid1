package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscoverItemResponse {

    @SerializedName("ID")
    public String id;
    @SerializedName("Category")
    public String category;
    @SerializedName("Type")
    public String type;
    @SerializedName("Title")
    public String title;
    @SerializedName("ImageUrl")
    public String imageUrl;
    @SerializedName("Hours")
    public String hours;
    @SerializedName("SubTitle")
    public String subTitle;
    @SerializedName("ReservationRequired")
    public String reservationRequired;
    @SerializedName("Duration")
    public String duration;
    @SerializedName("AgeRestriction")
    public String ageRestriction;
    @SerializedName("HeightRestriction")
    public String heightRestriction;
    @SerializedName("Attire")
    public String attire;
    @SerializedName("Description")
    public String description;
    @SerializedName("Accessibility")
    public List<String> accessibility;
    @SerializedName("Legal")
    public String legal;
    @SerializedName("CuisineTypes")
    public String cuisineTypes;
    @SerializedName("Ages")
    public String ages;
    @SerializedName("ActivityLevel")
    public String activityLevel;
    @SerializedName("Sessions")
    public String sessions;
    @SerializedName("LunchTime")
    public String lunchTime;
    @SerializedName("LunchMenu")
    public String lunchMenu;
    @SerializedName("DinnerTime")
    public String dinnerTime;
    @SerializedName("DinnerMenu")
    public String dinnerMenu;
    @SerializedName("Cuisine")
    public String cuisine;


}
