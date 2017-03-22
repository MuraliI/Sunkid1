package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductType;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ProductResponseDataMapperTest {

    ProductResponseDataMapper productResponseDataMapper;
    @Mock
    ProductResponse productResponse1;
    @Mock
    ProductType producType;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        productResponseDataMapper = new ProductResponseDataMapper();
        Mockito.when(productResponse1.getProductId()).thenReturn("100000002903890058");
        Mockito.when(productResponse1.getProductCode()).thenReturn("1002414");
        Mockito.when(productResponse1.getProductClass()).thenReturn("SERVICE");
        Mockito.when(productResponse1.getProductRank()).thenReturn(1);
        Mockito.when(productResponse1.isReservationRequired()).thenReturn(false);
        Mockito.when(productResponse1.isScheduable()).thenReturn(false);
        Mockito.when(productResponse1.getProductTitle()).thenReturn("Pathway to Yoga");
        Mockito.when(productResponse1.getProductShortDescription()).thenReturn("Excellent for increasing levels of concentration and focus. This class will benefit all levels of students.");

        Mockito.when(producType.getProductTypeId()).thenReturn("27");
        ProductType myObjectMock = mock(ProductType.class);




    }

    @Test
    public void transform() throws Exception {
        Product productResponse = productResponseDataMapper.transform(productResponse1);
        assertNotNull(productResponse);
        assertEquals(productResponse1.getProductId(), productResponse.getProductId());
        assertEquals(productResponse1.getProductCode(), productResponse.getProductCode());
        assertEquals(productResponse1.getProductClass(), productResponse.getProductClass());
        assertEquals(productResponse1.getProductRank(), productResponse.getProductRank());
        assertEquals(productResponse1.isReservationRequired(), productResponse.isReservationRequired());
        assertEquals(productResponse1.isScheduable(), productResponse.isScheduable());
        assertEquals(productResponse1.getProductTitle(), productResponse.getProductTitle());
        assertEquals(productResponse1.getProductShortDescription(), productResponse.getProductShortDescription());
    }

}