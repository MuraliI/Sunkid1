package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class ProductTagsResponse {
    @SerializedName("tagID")
    private String tagId;
    private String description;

    public String getTagID() {
        return tagId;
    }

    public void setTagID(String tagID) {
        this.tagId = tagId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
