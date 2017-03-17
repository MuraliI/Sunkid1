
package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class RestrictionResponse {

    @SerializedName("restrictionID")
    private String restrictionId;
    private String restrictionType;
    private Boolean isMandatory;
    private String restrictionDisplayText;
    private String restrictionQuestion;
    private List<RestrictionAnswerResponse> restrictionAnswers;


    @SerializedName("restrictionMedia")
    private RestrictionMediaResponse mRestrictionMedia;

    public RestrictionResponse() {
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public List<RestrictionAnswerResponse> getRestrictionAnswers() {
        return restrictionAnswers;
    }

    public void setRestrictionAnswers(List<RestrictionAnswerResponse> restrictionAnswers) {
        this.restrictionAnswers = restrictionAnswers;
    }

    public String getRestrictionDisplayText() {
        return restrictionDisplayText;
    }

    public void setRestrictionDisplayText(String restrictionDisplayText) {
        this.restrictionDisplayText = restrictionDisplayText;
    }

    public String getRestrictionID() {
        return restrictionId;
    }

    public void setRestrictionID(String restrictionID) {
        restrictionId = restrictionID;
    }

    public RestrictionMediaResponse getRestrictionMedia() {
        return mRestrictionMedia;
    }

    public void setRestrictionMedia(RestrictionMediaResponse restrictionMedia) {
        mRestrictionMedia = restrictionMedia;
    }

    public String getRestrictionQuestion() {
        return restrictionQuestion;
    }

    public void setRestrictionQuestion(String restrictionQuestion) {
        this.restrictionQuestion = restrictionQuestion;
    }

    public String getRestrictionType() {
        return restrictionType;
    }

    public void setRestrictionType(String restrictionType) {
        this.restrictionType = restrictionType;
    }

}
