package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.data.entity.SubCategoryEntity;
import com.rcl.excalibur.domain.SubCategory;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class SubCategoryEntityDataMapperTest {

    SubCategoryEntityDataMapper subCategoryEntityDataMapper;
    @Mock SubCategoryEntity subCategoryEntity;
    @Mock ChildCategoryEntity childCategoryEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subCategoryEntityDataMapper = new SubCategoryEntityDataMapper();

        Mockito.when(subCategoryEntity.getCategoryId()).thenReturn("shorex");
        Mockito.when(subCategoryEntity.getDescription()).thenReturn("Description Shorex");
        Mockito.when(subCategoryEntity.getName()).thenReturn("Shorex");

        Mockito.when(childCategoryEntity.getCategoryId()).thenReturn("land");
        Mockito.when(childCategoryEntity.getDescription()).thenReturn("Description Land");
        Mockito.when(childCategoryEntity.getName()).thenReturn("Land");
        Mockito.when(childCategoryEntity.getSubCategory()).thenReturn(subCategoryEntity);

        ArrayList<ChildCategoryEntity> items = new ArrayList<>();
        items.add(childCategoryEntity);

        Mockito.when(subCategoryEntity.getChildCategories()).thenReturn(items);
    }

    @Test
    public void transform() {
        SubCategory subCategory = subCategoryEntityDataMapper.transform(subCategoryEntity);

        assertNotNull(subCategory);
        assertEquals(subCategory.getCategoryDescription(), subCategoryEntity.getDescription());
        assertEquals(subCategory.getCategoryName(), subCategoryEntity.getName());
        assertEquals(subCategory.getCategoryId(), subCategoryEntity.getCategoryId());

        for (int i = 0; i < subCategory.getChildCategory().size(); i++) {
            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryDescription(), subCategoryEntity.getChildCategories().get(i).getDescription());
            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryName(), subCategoryEntity.getChildCategories().get(i).getName());
            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryId(), subCategoryEntity.getChildCategories().get(i).getCategoryId());
        }
    }

}