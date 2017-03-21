package com.rcl.excalibur.domain;


public class ProductCostType {

    private String costTypeCode;
    private String costTypeTitle;
    private String costTypeDescription;
    private Media costTypeMedia;

    public String getCostTypeCode() {
        return costTypeCode;
    }

    public void setCostTypeCode(String costTypeCode) {
        this.costTypeCode = costTypeCode;
    }

    public String getCostTypeTitle() {
        return costTypeTitle;
    }

    public void setCostTypeTitle(String costTypeTitle) {
        this.costTypeTitle = costTypeTitle;
    }

    public String getCostTypeDescription() {
        return costTypeDescription;
    }

    public void setCostTypeDescription(String costTypeDescription) {
        this.costTypeDescription = costTypeDescription;
    }

    public Media getCostTypeMedia() {
        return costTypeMedia;
    }

    public void setCostTypeMedia(Media costTypeMedia) {
        this.costTypeMedia = costTypeMedia;
    }
}
