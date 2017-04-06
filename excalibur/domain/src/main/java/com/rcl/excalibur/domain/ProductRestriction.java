package com.rcl.excalibur.domain;


import java.util.List;


public class ProductRestriction {
    public static final String AGE = "AGE";
    public static final String HEIGHT = "HEIGHT";

    private long restrictionId;
    private String restrictionType;
    private boolean isMandatory;
    private String restrictionDisplayText;
    private String restrictionTitle;
    private String restrictionDescription;
    private String restrictionQuestion;
    private List<ProductRestrictionAnswer> restrictionAnswers;
    private Media restrictionMedia;

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

    public List<ProductRestrictionAnswer> getRestrictionAnswers() {
        return restrictionAnswers;
    }

    public void setRestrictionAnswers(List<ProductRestrictionAnswer> restrictionAnswers) {
        this.restrictionAnswers = restrictionAnswers;
    }

    public Media getRestrictionMedia() {
        return restrictionMedia;
    }

    public void setRestrictionMedia(Media restrictionMedia) {
        this.restrictionMedia = restrictionMedia;
    }
}
