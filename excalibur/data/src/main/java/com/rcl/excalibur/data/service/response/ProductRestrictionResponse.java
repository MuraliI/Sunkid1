package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ProductRestrictionResponse {

    @SerializedName("restrictionID")
    private long restrictionId;
    private String restrictionType;
    private boolean isMandatory;
    private String restrictionDisplayText;
    private String restrictionTitle;
    private String restrictionDescription;
    private String restrictionQuestion;
    private List<ProductRestrictionAnswerResponse> restrictionAnswers;
    private MediaResponse restrictionMedia;

    public long getRestrictionId() {
        return restrictionId;
    }

    public void setRestrictionId(long restrictionId) {
        this.restrictionId = restrictionId;
    }

    public String getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(String restrictionType) {
        this.restrictionType = restrictionType;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public String getRestrictionDisplayText() {
        return restrictionDisplayText;
    }

    public void setRestrictionDisplayText(String restrictionDisplayText) {
        this.restrictionDisplayText = restrictionDisplayText;
    }

    public String getRestrictionTitle() {
        return restrictionTitle;
    }

    public void setRestrictionTitle(String restrictionTitle) {
        this.restrictionTitle = restrictionTitle;
    }

    public String getRestrictionDescription() {
        return restrictionDescription;
    }

    public void setRestrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public String getRestrictionQuestion() {
        return restrictionQuestion;
    }

    public void setRestrictionQuestion(String restrictionQuestion) {
        this.restrictionQuestion = restrictionQuestion;
    }

    public List<ProductRestrictionAnswerResponse> getRestrictionAnswers() {
        return restrictionAnswers;
    }

    public void setRestrictionAnswers(List<ProductRestrictionAnswerResponse> restrictionAnswers) {
        this.restrictionAnswers = restrictionAnswers;
    }

    public MediaResponse getRestrictionMedia() {
        return restrictionMedia;
    }

    public void setRestrictionMedia(MediaResponse restrictionMedia) {
        this.restrictionMedia = restrictionMedia;
    }
}
