package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.PromotionMessageResponse;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class PromotionMessageResponseDataMapperTest {

    PromotionMessageResponseDataMapper promotionMessageResponseDataMapper;
    @Mock PromotionMessageResponse entity1;
    @Mock PromotionMessageResponse entity2;

    @Before
    public void setUp() {
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

}