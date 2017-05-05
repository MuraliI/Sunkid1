package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.CategoryResponse;
import com.rcl.excalibur.data.service.response.ChildCategoryResponse;
import com.rcl.excalibur.data.service.response.ItemsResponse;

import org.junit.Before;
import org.junit.Ignore;

import java.util.ArrayList;

public class SubCategoryResponseDataMapperTest {

    CategoryResponseDataMapper subCategoryResponseDataMapper;

    CategoryResponse subCategoryResponse;
    ChildCategoryResponse childCategoryResponse;
    ItemsResponse itemsResponse;

    @Before
    public void setUp() throws Exception {

        subCategoryResponseDataMapper = new CategoryResponseDataMapper();

        itemsResponse = new ItemsResponse();
        itemsResponse.setCategoryName("land");
        itemsResponse.setCategoryName("Land");
        itemsResponse.setCategoryDescription("Description Land");

        childCategoryResponse = new ChildCategoryResponse();
        childCategoryResponse.setItems(itemsResponse);

        subCategoryResponse = new CategoryResponse();
        subCategoryResponse.setCategoryId("shorex");
        subCategoryResponse.setCategoryDescription("Description Shorex");
        subCategoryResponse.setCategoryName("Shorex");

        ArrayList<ChildCategoryResponse> items = new ArrayList<ChildCategoryResponse>();
        items.add(childCategoryResponse);
        subCategoryResponse.setChildCategory(items);
    }

    @Ignore
    public void transform() throws Exception {
//        SubCategory subCategory = subCategoryResponseDataMapper.transform(subCategoryResponse, null);
//
//        assertNotNull(subCategory);
//
//        assertEquals(subCategory.getCategoryDescription(), subCategoryResponse.getCategoryDescription());
//        assertEquals(subCategory.getCategoryId(), subCategoryResponse.getCategoryId());
//        assertEquals(subCategory.getCategoryName(), subCategoryResponse.getCategoryName());
//        for (int i = 0; i < subCategory.getChildCategory().size(); i++) {
//            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryDescription(), subCategoryResponse.getChildCategory().get(i).getItems().getCategoryDescription());
//            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryId(), subCategoryResponse.getChildCategory().get(i).getItems().getCategoryId());
//            assertEquals(subCategory.getChildCategory().get(i).getItems().getCategoryName(), subCategoryResponse.getChildCategory().get(i).getItems().getCategoryName());
//        }

    }

}