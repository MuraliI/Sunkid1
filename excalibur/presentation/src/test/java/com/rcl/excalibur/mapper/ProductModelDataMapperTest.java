package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.model.ProductModel;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProductModelDataMapperTest {

    ProductModelDataMapper productModelDataMapper;
    Product entity1;
    Product entity2;

    @Before
    public void setUp() {
        //Todo Add all missing attributes
        productModelDataMapper = new ProductModelDataMapper();
        entity1 = new Product();
        ProductDuration productDuration = new ProductDuration();
        productDuration.setDurationInMinutes(125);
        entity1.setProductDuration(productDuration);
        entity1.setProductShortDescription("Description will be a block of text");

        entity1.setProductReservationInformation("Arrive 15 minutes early, Wear closedtoed shoes");

        entity2 = null;
    }

    @Test
    public void transformToEntity() throws Exception {
        ProductModel productModel = productModelDataMapper.transform(entity1);
        assertNotNull(productModel);
        assertEquals(entity1.getProductDuration().getDurationInMinutes(), productModel.getDuration());
        assertEquals(entity1.getProductReservationInformation(), productModel.getReservationInformation());
        assertEquals(entity1.getProductShortDescription(), productModel.getDescription());

        ProductModel productModelNull = productModelDataMapper.transform(entity2);
        assertNull(productModelNull);
    }

}
