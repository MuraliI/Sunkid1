package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.PromotionMessageResponse;
import com.rcl.excalibur.domain.PromotionMessage;

import javax.inject.Inject;

public class PromotionMessageResponseDataMapper extends BaseDataMapper<PromotionMessage, PromotionMessageResponse> {

    @Inject
    PromotionMessageResponseDataMapper() {

    }

    @Override
    public PromotionMessage transform(PromotionMessageResponse entity) {
        PromotionMessage promotionMessage = null;
        if (null != entity) {
            promotionMessage = new PromotionMessage();
            promotionMessage.setCategoryId(entity.getCategoryId());
            promotionMessage.setTitle(entity.getMessageTitle());
            promotionMessage.setDescription(entity.getMessageDescription());
            promotionMessage.setLocationCode(entity.getLocationCode());
            promotionMessage.setProductId(entity.getProductId());
        }
        return promotionMessage;
    }
}
