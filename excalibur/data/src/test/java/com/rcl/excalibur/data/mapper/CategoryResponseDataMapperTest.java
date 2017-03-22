package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.domain.Category;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CategoryResponseDataMapperTest {

    CategoryResponseDataMapper categotyServiceDataMapper;
    CategoryResponse entity1;
    CategoryResponse entity2;

    @Before
    public void setUp() throws Exception {


        categotyServiceDataMapper = new CategoryResponseDataMapper();
        entity1 = new CategoryResponse();
        entity1.setCategoryId((long) 12);
        entity1.setCategoryDescription("Some Dectiption");
        entity1.setProductTags(Arrays.asList("Tag 1", "Tag 2", "Ta2"));
        entity2 = new CategoryResponse();
        entity2.setCategoryId((long) 13);
        entity2.setCategoryDescription("Some Dectiption 2");
        entity2.setProductTags(Arrays.asList("Tag 1", "Tag 2", "Ta2"));
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