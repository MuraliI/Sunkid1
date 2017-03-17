package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.mapper.CategoryResponseDataMapper;
import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.domain.Category;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CategoryResponseDataMapperTest {

    CategoryResponseDataMapper categotyServiceDataMapper;
    @Mock CategoryResponse entity1;
    @Mock CategoryResponse entity2;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        categotyServiceDataMapper = new CategoryResponseDataMapper();
        Mockito.when(entity1.getCategoryId()).thenReturn((long) 12);
        Mockito.when(entity1.getCategoryDescription()).thenReturn("Some Dectiption");
        Mockito.when(entity1.getProductTags()).thenReturn(Arrays.asList("Tag 1", "Tag 2", "Ta2"));
        Mockito.when(entity2.getCategoryId()).thenReturn((long) 13);
        Mockito.when(entity2.getCategoryDescription()).thenReturn("Some Dectiption 2");
        Mockito.when(entity2.getProductTags()).thenReturn(Arrays.asList("Tag 1", "Tag 2", "Ta2"));
    }

    @Test
    public void transform() throws Exception {
        Category category = categotyServiceDataMapper.transform(entity1);
        assertNotNull(category);
        assertEquals(entity1.getCategoryId(), category.getCategoryId());
        assertEquals(entity1.getCategoryDescription(), category.getDescription());
        assertEquals(entity1.getProductTags(), category.getTags());
    }

    @Test
    public void transformToList() throws Exception {
        final List<CategoryResponse> entities = new ArrayList();
        entities.add(entity1);
        entities.add(entity2);

        List<Category> categoryList = categotyServiceDataMapper.transform(entities);
        assertNotNull(categoryList);
        assertFalse(categoryList.isEmpty());
        assertEquals(entities.size(), categoryList.size());

        for (int i = 0; i < categoryList.size(); i++) {
            assertEquals(entities.get(i).getCategoryId(), categoryList.get(i).getCategoryId());
            assertEquals(entities.get(i).getCategoryDescription(), categoryList.get(i).getDescription());
            assertEquals(entities.get(i).getProductTags(), categoryList.get(i).getTags());
        }
    }

}