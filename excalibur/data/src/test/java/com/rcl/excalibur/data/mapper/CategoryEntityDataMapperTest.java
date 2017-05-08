package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.domain.Category;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class CategoryEntityDataMapperTest {
    CategoryEntityDataMapper categoryEntityDataMapper;
    @Mock CategoryEntity categoryEntity;
    @Mock ChildCategoryEntity childEntity;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryEntityDataMapper = new CategoryEntityDataMapper();

    }

    @Test
    public void transform() throws Exception {
        List<ChildCategoryEntity> childs1 = new ArrayList<>();
        when(childEntity.getName()).thenReturn("child name 1");
        when(childEntity.getDescription()).thenReturn("child desc 1");
        when(childEntity.getCategory()).thenReturn(categoryEntity);
        when(childEntity.getName()).thenReturn("child name 1");
        childs1.add(childEntity);

        when(categoryEntity.getCategoryId()).thenReturn("categ 1");
        when(categoryEntity.getDescription()).thenReturn("desc 1");
        when(categoryEntity.getName()).thenReturn("name 1");
        when(categoryEntity.getChildCategory()).thenReturn(childs1);


        List<CategoryEntity> entities = new ArrayList<>();
        entities.add(categoryEntity);
        List<Category> categories = categoryEntityDataMapper.transform(entities, null);
        Assert.assertNotNull(categories);
        Assert.assertEquals(1, categories.size());
        Assert.assertEquals(categoryEntity.getName(), categories.get(0).getCategoryName());
        Assert.assertEquals(categoryEntity.getDescription(), categories.get(0).getCategoryDescription());
        Assert.assertEquals(categoryEntity.getCategoryId(), categories.get(0).getCategoryId());

        Assert.assertNotNull(categories.get(0).getChildCategory());
        Assert.assertEquals(1, categories.get(0).getChildCategory().size());
        Assert.assertEquals(childEntity.getName(), categories.get(0).getChildCategory().get(0).getItems().getCategoryName());
        Assert.assertEquals(childEntity.getDescription(), categories.get(0).getChildCategory().get(0).getItems().getCategoryDescription());
        Assert.assertEquals(childEntity.getCategory(), categoryEntity);
        Assert.assertEquals(childEntity.getChildCategoryId(), categories.get(0).getChildCategory().get(0).getItems().getCategoryId());
    }

}