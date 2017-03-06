package com.rcl.excalibur.data.entity.mapper;


import com.rcl.excalibur.data.entity.DiscoverEntity;
import com.rcl.excalibur.domain.Discover;

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

public class DiscoverEntityDataMapperTest {
    DiscoverEntityDataMapper discoverEntityDataMapper;
    @Mock DiscoverEntity entity1;
    @Mock DiscoverEntity entity2;

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
        Discover discover = discoverEntityDataMapper.transform(entity1);
        assertNotNull(discover);
        assertEquals(entity1.getTitle(), discover.getTitle());
        assertEquals(entity1.getImageUrl(), discover.getImageUrl());

    }

    @Test
    public void transformToList() throws Exception {
        final List<DiscoverEntity> entities = new ArrayList();
        entities.add(entity1);
        entities.add(entity2);

        List<Discover> discovers = discoverEntityDataMapper.transform(entities);
        assertNotNull(discovers);
        assertFalse(discovers.isEmpty());
        assertEquals(entities.size(), discovers.size());

        for (int i = 0; i < discovers.size(); i++) {
            assertEquals(entities.get(i).getTitle(), discovers.get(i).getTitle());
            assertEquals(entities.get(i).getImageUrl(), discovers.get(i).getImageUrl());
        }
    }
}