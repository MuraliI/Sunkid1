package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.PromotionMessageResponse;
import com.rcl.excalibur.domain.Category;
import com.rcl.excalibur.domain.PromotionMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class PromotionMessageResponseDataMapperTest {

    PromotionMessageResponseDataMapper promotionMessageResponseDataMapper;
    @Mock PromotionMessageResponse entity1;
    @Mock PromotionMessageResponse entity2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        promotionMessageResponseDataMapper = new PromotionMessageResponseDataMapper();

        when(entity1.getCategoryId()).thenReturn("categoryId");
        when(entity1.getLocationCode()).thenReturn("57850");
        when(entity1.getMessageDescription()).thenReturn("some description");
        when(entity1.getMessageTitle()).thenReturn("My title");
        when(entity1.getProductId()).thenReturn(new String[]{"Id1", "Id2", "Id3"});

        when(entity2.getCategoryId()).thenReturn("IdCategory");
        when(entity2.getLocationCode()).thenReturn("51200");
        when(entity2.getMessageDescription()).thenReturn("other description");
        when(entity2.getMessageTitle()).thenReturn("some title");
        when(entity2.getProductId()).thenReturn(new String[]{"one", "two", "three"});
    }

    @Test
    public void transform() throws Exception {

        PromotionMessage promotionMessage = promotionMessageResponseDataMapper.transform(entity1);
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

        List<PromotionMessage> promotionMessageList = promotionMessageResponseDataMapper.transform(entities);
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