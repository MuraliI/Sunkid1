package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.PromotionMessageResponse;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.PromotionMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PromotionMessageResponseDataMapperTest {

    PromotionMessageResponseDataMapper promotionMessageResponseDataMapper;
    PromotionMessageResponse entity1;
    PromotionMessageResponse entity2;

    @Before
    public void setUp() throws Exception {

        promotionMessageResponseDataMapper = new PromotionMessageResponseDataMapper();

        entity1 = new PromotionMessageResponse();
        entity1.setCategoryId("SHOREX");
        entity1.setLocationCode("57850");
        entity1.setMessageDescription("Save on invitica, Movadp, Tissot, Citizen, Bulova, Michael Kors, Ferran, Fossil, Guess, G-SHock, and more. All purchases jabe a 30-day price match gaurantee!. Whethr you'r shopping for yourself o as a gift, we have an amazing selection for you to chose from. All tax and duty free.");
        entity1.setMessageTitle("Designer Watch Sale Up to 75% off");
        entity1.setProductId(new String[]{"100000003007969595", "100000002783668857", "100000000024033275"});

        entity2 = new PromotionMessageResponse();
        entity2.setCategoryId("SHOREX");
        entity2.setLocationCode("57850");
        entity2.setMessageDescription("Save on invitica,Movadp and more. All purchases jabe a 30-day price match gaurantee!. Whethr you'r shopping for yourself o as a gift, we have an amazing selection for you to chose from. All tax and duty free.");
        entity2.setMessageTitle("Designer Watch Sale Up to 5% off");
        entity2.setProductId(new String[]{"100000000024033275", "100000000024033275", "100000000024033275"});
    }

    @Test
    public void transform() throws Exception {

        PromotionMessage promotionMessage = promotionMessageResponseDataMapper.transform(entity1, null);
        assertNotNull(promotionMessage);
        assertEquals(entity1.getCategoryId(), promotionMessage.getCategoryId());
        assertEquals(entity1.getLocationCode(), promotionMessage.getLocationCode());
        assertEquals(entity1.getMessageDescription(), promotionMessage.getDescription());
        assertEquals(entity1.getMessageTitle(), promotionMessage.getTitle());
        assertEquals(entity1.getProductId(), promotionMessage.getProductId());
    }

    @Test
    public void transformToList() throws Exception {
        final List<PromotionMessageResponse> entities = new ArrayList();
        entities.add(entity1);
        entities.add(entity2);

        List<PromotionMessage> promotionMessageList = promotionMessageResponseDataMapper.transform(entities, null);
        assertNotNull(promotionMessageList);
        assertFalse(promotionMessageList.isEmpty());
        assertEquals(entities.size(), promotionMessageList.size());

        for (int i = 0; i < promotionMessageList.size(); i++) {
            assertEquals(entities.get(i).getCategoryId(), promotionMessageList.get(i).getCategoryId());
            assertEquals(entities.get(i).getLocationCode(), promotionMessageList.get(i).getLocationCode());
            assertEquals(entities.get(i).getMessageDescription(), promotionMessageList.get(i).getDescription());
            assertEquals(entities.get(i).getMessageTitle(), promotionMessageList.get(i).getTitle());
            assertEquals(entities.get(i).getProductId(), promotionMessageList.get(i).getProductId());
        }
    }


}