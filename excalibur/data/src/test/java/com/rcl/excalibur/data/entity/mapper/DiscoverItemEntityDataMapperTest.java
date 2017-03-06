package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.DiscoverItemEntity;
import com.rcl.excalibur.domain.DiscoverItem;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DiscoverItemEntityDataMapperTest {
    DiscoverEntityDataMapper discoverEntityDataMapper;
    @Mock DiscoverItemEntity entity1;
    @Mock DiscoverItemEntity entity2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        discoverEntityDataMapper = new DiscoverEntityDataMapper();
        Mockito.when(entity1.getTitle()).thenReturn("name1");
        Mockito.when(entity1.getImageUrl()).thenReturn("imageUrl1");
        Mockito.when(entity2.getTitle()).thenReturn("name2");
        Mockito.when(entity2.getImageUrl()).thenReturn("imageUrl2");
    }

    @Test
    public void transformToEntity() throws Exception {
        DiscoverItem discoverItem = discoverEntityDataMapper.transform(entity1);
        assertNotNull(discoverItem);
        assertEquals(entity1.getTitle(), discoverItem.getTitle());
        assertEquals(entity1.getImageUrl(), discoverItem.getImageUrl());

    }

    @Test
    public void transformToList() throws Exception {
        final List<DiscoverItemEntity> entities = new ArrayList();
        entities.add(entity1);
        entities.add(entity2);

        List<DiscoverItem> discoverItems = discoverEntityDataMapper.transform(entities);
        assertNotNull(discoverItems);
        assertFalse(discoverItems.isEmpty());
        assertEquals(entities.size(), discoverItems.size());

        for (int i = 0; i < discoverItems.size(); i++) {
            assertEquals(entities.get(i).getTitle(), discoverItems.get(i).getTitle());
            assertEquals(entities.get(i).getImageUrl(), discoverItems.get(i).getImageUrl());
        }
    }
}