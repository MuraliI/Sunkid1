package com.rcl.excalibur.data.service.response;

import com.google.gson.annotations.SerializedName;

public class PromotionMessagesResponse {

    @SerializedName("GetPromotionMessages")
    private GetPromotionMessagesResponse getPromotionMessages;

    public GetPromotionMessagesResponse getGetPromotionMessages() {
        return getPromotionMessages;
    }

    public void setGetPromotionMessages(GetPromotionMessagesResponse getPromotionMessages) {
        this.getPromotionMessages = getPromotionMessages;
    }
}
