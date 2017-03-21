package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;


public class ProductTagsResponse {
    @SerializedName("tagID")
    private String tagId;
    private String description;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
