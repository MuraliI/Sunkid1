package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.domain.Product;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class ProductEntityDataMapperTest {

    ProductEntityDataMapper productEntityDataMapper;
    @Mock ProductEntity productEntity;
    @Mock CategoryEntity categoryEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productEntityDataMapper = new ProductEntityDataMapper();


    }

    @Test
    public void transform() throws Exception {
        when(categoryEntity.getName()).thenReturn("categ name 1");

        when(productEntity.getProductId()).thenReturn("prod 1");
        when(productEntity.getCategory()).thenReturn(categoryEntity);
        when(productEntity.getChildCategories()).thenReturn(new String[]{"rest", "land"});
        when(productEntity.getCode()).thenReturn("code 1");
        when(productEntity.getTitle()).thenReturn("title 1");
        when(productEntity.getLongDescription()).thenReturn("long desc 1");
        when(productEntity.getShortDescription()).thenReturn("short desc 1");


        List<ProductEntity> entities = new ArrayList<>();
        entities.add(productEntity);


        List<Product> products = productEntityDataMapper.transform(entities, null);
        assertNotNull(products);
        assertEquals(1, products.size());

        assertEquals(productEntity.getProductId(), products.get(0).getProductId());
        assertEquals(productEntity.getCategory().getName(), products.get(0).getProductCategory().getCategoryName());
        assertArrayEquals(productEntity.getChildCategories(), products.get(0).getChildCategoriesId().toArray());
        assertEquals(productEntity.getCode(), products.get(0).getProductCode());
        assertEquals(productEntity.getTitle(), products.get(0).getProductTitle());
        assertEquals(productEntity.getLongDescription(), products.get(0).getProductLongDescription());
        assertEquals(productEntity.getShortDescription(), products.get(0).getProductShortDescription());
    }

}