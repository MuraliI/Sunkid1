package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.domain.Category;

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

public class CategoryEntityDataMapperTest {

    CategoryEntityDataMapper categoryEntityDataMapper;
    @Mock CategoryEntity entity1;
    @Mock CategoryEntity entity2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryEntityDataMapper = new CategoryEntityDataMapper();
        Mockito.when(entity1.getCategoryId()).thenReturn("1234");
        Mockito.when(entity1.getDescription()).thenReturn("description1");
    //    Mockito.when(entity1.getTags()).thenReturn(new String[]{"tag1", "tag2"});
        Mockito.when(entity2.getCategoryId()).thenReturn("1255");
        Mockito.when(entity2.getDescription()).thenReturn("description2");
      //  Mockito.when(entity2.getTags()).thenReturn(new String[]{"tag3", "tag4"});
    }

    @Test
    public void transformToEntity() throws Exception {
        Category category = categoryEntityDataMapper.transform(entity1);
        assertNotNull(category);
        assertEquals(entity1.getCategoryId(), category.getCategoryId());
        /*assertEquals(entity1.getDescription(), category.getDescription());
        final int tagSize = entity1.getTags().length;
        for (int j = 0; j < tagSize; j++) {
            assertEquals(entity1.getTags()[j], category.getTags().get(j));
        }*/
    }

    @Test
    public void transformToList() throws Exception {
        final List<CategoryEntity> entities = new ArrayList<>();
        entities.add(entity1);
        entities.add(entity2);

        List<Category> categories = categoryEntityDataMapper.transform(entities);
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertEquals(entities.size(), categories.size());

        for (int i = 0; i < categories.size(); i++) {
            assertEquals(entities.get(i).getCategoryId(), categories.get(i).getCategoryId());
            assertEquals(entities.get(i).getDescription(), categories.get(i).getDescription());
           /* final int tagSize = entities.get(i).getTags().length;
            for (int j = 0; j < tagSize; j++) {
                assertEquals(entities.get(i).getTags()[j], categories.get(i).getTags().get(j));
            }*/
        }
    }

}