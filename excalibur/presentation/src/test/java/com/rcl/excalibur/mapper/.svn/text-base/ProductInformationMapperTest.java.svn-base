package com.rcl.excalibur.mapper;


import com.rcl.excalibur.adapters.viewtype.ProductInformationViewType;
import com.rcl.excalibur.data.utils.Preconditions;
import com.rcl.excalibur.domain.LocationDeckInfo;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.utils.ConstantsUtil;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductInformationMapperTest {

    private static final String PRODUCT_ID = "10001";
    private static final String PRODUCT_NAME = "Excursion with dolphins";
    private static final int PRODUCT_DECK_NUMBER = 12;
    private static final String PRODUCT_DIRECTION = "AFT";

    private ProductInformationMapper mapper;

    private Product entity1;
    private Product entity2;

    @Before
    public void setUp() {
        mapper = new ProductInformationMapper();

        entity1 = new Product();
        entity1.setProductId(PRODUCT_ID);
        entity1.setProductTitle(PRODUCT_NAME);

        LocationDeckInfo locationDeckInfo = new LocationDeckInfo();
        locationDeckInfo.setDeckNumber(String.valueOf(PRODUCT_DECK_NUMBER));
        locationDeckInfo.setDirection(PRODUCT_DIRECTION);

        List<LocationDeckInfo> locationDeckInfoList = new ArrayList<>();
        locationDeckInfoList.add(locationDeckInfo);

        ProductLocation location = new ProductLocation();
        location.setDeckInfo(locationDeckInfoList);

        entity1.setProductLocation(location);

        entity2 = null;
    }

    @Test
    public void successfullyMapProductToProductInformationViewTypeTest() throws Exception {
        ProductInformationViewType viewType = mapper.transform(entity1);
        assertEquals("Id was not equal", PRODUCT_ID, viewType.getProductId());
        assertEquals("Name was not equal", PRODUCT_NAME, viewType.getProductName());
        assertEquals("Deck was not equal", String.valueOf(PRODUCT_DECK_NUMBER)
                + ConstantsUtil.WHITE_SPACE
                + PRODUCT_DIRECTION, viewType.getLocation());
    }

    @Test
    public void productWasNullTest() throws Exception {
        try {
            ProductInformationViewType viewType = mapper.transform(entity2);
        } catch (RuntimeException e) {
            assertEquals("Exception Message is wrong", e.getMessage(), Preconditions.MESSAGE_NOT_NULL);
        }
    }
}
