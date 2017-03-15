package com.rcl.excalibur.data.service.response;


import com.google.gson.annotations.SerializedName;

public class PriceResponse {
    @SerializedName("Adults")
    public String adults;
    @SerializedName("Children")
    public String children;
    @SerializedName("Min")
    public String min;
    @SerializedName("Max")
    public String max;
}
