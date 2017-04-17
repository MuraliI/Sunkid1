package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.ChildCategoryResponse;
import com.rcl.excalibur.data.service.response.ItemsResponse;
import com.rcl.excalibur.data.service.response.SubCategoryResponse;
import com.rcl.excalibur.domain.SubCategory;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SubCategoryResponseDataMapperTest {

    SubCategoryResponseDataMapper subCategoryResponseDataMapper;

    SubCategoryResponse subCategoryResponse;
    ChildCategoryResponse childCategoryResponse;
    ItemsResponse itemsResponse;

    @Before
    public void setUp() throws Exception {

        subCategoryResponseDataMapper = new SubCategoryResponseDataMapper();

        itemsResponse = new ItemsResponse();
        itemsResponse.setCategoryName("land");
        itemsResponse.setCategoryName("Land");
        itemsResponse.setCategoryDescription("Description Land");

        childCategoryResponse = new ChildCategoryResponse();
        childCategoryResponse.setItems(itemsResponse);

        subCategoryResponse = new SubCategoryResponse();
        subCategoryResponse.setCategoryId("shorex");
        subCategoryResponse.setCategoryDescription("Description Shorex");
        subCategoryResponse.setCategoryName("Shorex");

        ArrayList<ChildCategoryResponse> items = new ArrayList<ChildCategoryResponse>();
        items.add(childCategoryResponse);
        subCategoryResponse.setChildCategory(items);
    }

    @Test
    public void transform() throws Exception {
        SubCategory subCategory = subCategoryResponseDataMapper.transform(subCategoryResponse);

        assertNotNull(subCategory);

        assertEquals(subCategory.getCategoryDescription(), subCategoryResponse.getCategoryDescription());
        assertEquals(subCategory.getCategoryId(), subCategoryResponse.getCategoryId());
        assertEquals(subCategory.getCategoryName(), subCategoryResponse.getCategoryName());
        for (int i = 0; i < subCategory.getChildCategory().size(); i++) {
            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryDescription(), subCategoryResponse.getChildCategory().get(i).getItems().getCategoryDescription());
            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryId(), subCategoryResponse.getChildCategory().get(i).getItems().getCategoryId());
            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryName(), subCategoryResponse.getChildCategory().get(i).getItems().getCategoryName());
        }

    }

}