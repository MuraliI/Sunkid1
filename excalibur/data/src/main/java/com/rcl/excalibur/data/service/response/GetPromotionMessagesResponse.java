package com.rcl.excalibur.data.service.response;

import java.util.List;

public class GetPromotionMessagesResponse extends BaseResponse {

    private List<PromotionMessageResponse> promotionMessage;

    public List<PromotionMessageResponse> getPromotionMessage() {
        return promotionMessage;
    }

    public void setPromotionMessage(List<PromotionMessageResponse> promotionMessage) {
        this.promotionMessage = promotionMessage;
    }
}
