package com.rcl.excalibur.data.service.response;

public class GetPromotionMessagesResponse {

    private String responseStatus;

    private PromotionMessageResponse promotionMessage;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public PromotionMessageResponse getPromotionMessage() {
        return promotionMessage;
    }

    public void setPromotionMessage(PromotionMessageResponse promotionMessage) {
        this.promotionMessage = promotionMessage;
    }
}
