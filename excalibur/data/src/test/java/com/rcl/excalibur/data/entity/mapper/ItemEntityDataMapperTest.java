package com.rcl.excalibur.data.entity.mapper;

import com.rcl.excalibur.data.entity.ItemEntity;
import com.rcl.excalibur.domain.Item;

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

public class ItemEntityDataMapperTest {

    ItemEntityDataMapper itemEntityDataMapper;
    @Mock ItemEntity entity1;
    @Mock ItemEntity entity2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        itemEntityDataMapper = new ItemEntityDataMapper();
        Mockito.when(entity1.getName()).thenReturn("name1");
        Mockito.when(entity1.getImageUrl()).thenReturn("imageUrl1");
        Mockito.when(entity2.getName()).thenReturn("name2");
        Mockito.when(entity2.getImageUrl()).thenReturn("imageUrl2");
    }

    @Test
    public void transformToEntity() throws Exception {
        Item item = itemEntityDataMapper.transform(entity1);
        assertNotNull(item);
        assertEquals(entity1.getName(), item.getName());
        assertEquals(entity1.getImageUrl(), item.getImageUrl());

    }

    @Test
    public void transformToList() throws Exception {
        final List<ItemEntity> entities = new ArrayList();
        entities.add(entity1);
        entities.add(entity2);

        List<Item> items = itemEntityDataMapper.transform(entities);
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(entities.size(), items.size());

        for (int i = 0; i < items.size(); i++) {
            assertEquals(entities.get(i).getName(), items.get(i).getName());
            assertEquals(entities.get(i).getImageUrl(), items.get(i).getImageUrl());
        }
    }

}