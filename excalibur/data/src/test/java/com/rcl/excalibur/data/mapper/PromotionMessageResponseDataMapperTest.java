package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.PromotionMessageResponse;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PromotionMessageResponseDataMapperTest {

    PromotionMessageResponseDataMapper promotionMessageResponseDataMapper;
    @Mock PromotionMessageResponse entity1;
    @Mock PromotionMessageResponse entity2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        promotionMessageResponseDataMapper = new PromotionMessageResponseDataMapper();

        Mockito.when(entity1.getCategoryId()).thenReturn("categoryId");
        Mockito.when(entity1.getLocationCode()).thenReturn("57850");
        Mockito.when(entity1.getMessageDescription()).thenReturn("some description");
        Mockito.when(entity1.getMessageTitle()).thenReturn("My title");
        Mockito.when(entity1.getProductId()).thenReturn(new String[]{"Id1", "Id2", "Id3"});

        Mockito.when(entity2.getCategoryId()).thenReturn("IdCategory");
        Mockito.when(entity2.getLocationCode()).thenReturn("51200");
        Mockito.when(entity2.getMessageDescription()).thenReturn("other description");
        Mockito.when(entity2.getMessageTitle()).thenReturn("some title");
        Mockito.when(entity2.getProductId()).thenReturn(new String[]{"one", "two", "three"});

    }

}