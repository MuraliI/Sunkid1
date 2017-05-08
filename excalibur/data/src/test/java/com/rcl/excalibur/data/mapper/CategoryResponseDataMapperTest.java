package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.data.service.response.ChildCategoryResponse;
import com.rcl.excalibur.data.service.response.ItemsResponse;
import com.rcl.excalibur.domain.Category;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class CategoryResponseDataMapperTest {

    CategoryResponseDataMapper categoryResponseDataMapper;
    @Mock CategoryResponse categoryResponse;
    @Mock ChildCategoryResponse childCategoryResponse;
    @Mock ItemsResponse itemsResponse;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryResponseDataMapper = new CategoryResponseDataMapper();
    }

    @Test
    public void transform() throws Exception {
        List<ChildCategoryResponse> childs1 = new ArrayList<>();
        when(childCategoryResponse.getItems()).thenReturn(itemsResponse);
        when(itemsResponse.getCategoryName()).thenReturn("child name 1");
        when(itemsResponse.getCategoryDescription()).thenReturn("child desc 1");
        when(itemsResponse.getCategoryId()).thenReturn("child id 1");
        childs1.add(childCategoryResponse);

        when(categoryResponse.getCategoryId()).thenReturn("categ 1");
        when(categoryResponse.getCategoryDescription()).thenReturn("desc 1");
        when(categoryResponse.getCategoryName()).thenReturn("name 1");
        when(categoryResponse.getChildCategory()).thenReturn(childs1);


        List<CategoryResponse> entities = new ArrayList<>();
        entities.add(categoryResponse);
        List<Category> categories = categoryResponseDataMapper.transform(entities, null);
        Assert.assertNotNull(categories);
        Assert.assertEquals(1, categories.size());
        Assert.assertEquals(categoryResponse.getCategoryName(), categories.get(0).getCategoryName());
        Assert.assertEquals(categoryResponse.getCategoryDescription(), categories.get(0).getCategoryDescription());
        Assert.assertEquals(categoryResponse.getCategoryId(), categories.get(0).getCategoryId());


        Assert.assertNotNull(categories.get(0).getChildCategory());
        Assert.assertEquals(1, categories.get(0).getChildCategory().size());
        Assert.assertEquals(childCategoryResponse.getItems().getCategoryName(), categories.get(0).getChildCategory().get(0).getItems().getCategoryName());
        Assert.assertEquals(childCategoryResponse.getItems().getCategoryDescription(), categories.get(0).getChildCategory().get(0).getItems().getCategoryDescription());

    }

}