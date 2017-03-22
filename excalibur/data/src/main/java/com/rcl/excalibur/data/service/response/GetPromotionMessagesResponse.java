package com.rcl.excalibur.data.service.response;

import java.util.List;

public class GetPromotionMessagesResponse {

    private String responseStatus;

    private List<PromotionMessageResponse> promotionMessage;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }


    public List<PromotionMessageResponse> getPromotionMessage() {
        return promotionMessage;
    }

    public void setPromotionMessage(List<PromotionMessageResponse> promotionMessage) {
        this.promotionMessage = promotionMessage;
    }
}
