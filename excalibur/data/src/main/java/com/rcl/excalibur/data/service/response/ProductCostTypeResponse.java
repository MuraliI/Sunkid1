package com.rcl.excalibur.data.service.response;


public class ProductCostTypeResponse {

    private String costTypeCode;
    private String costTypeTitle;
    private String costTypeDescription;
    private MediaResponse costTypeMedia;

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

    public MediaResponse getCostTypeMedia() {
        return costTypeMedia;
    }

    public void setCostTypeMedia(MediaResponse costTypeMedia) {
        this.costTypeMedia = costTypeMedia;
    }
}
