package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.utils.DateUtil;
import com.rcl.excalibur.data.utils.TestUtils;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.SellingPrice;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OfferingDataMapperTest {

    @Mock PriceDataMapper priceEntityDataMapper;
    @Mock ProductEntityDataMapper productEntityDataMapper;

    @Mock ProductEntity productEntity;
    @Mock PriceEntity priceEntity;
    @Mock OfferingEntity offeringEntity;

    @Mock Product product;
    @Mock SellingPrice sellingPrice;

    private List<OfferingEntity> offeringEntities;

    private OfferingDataMapper mapper;

    @Before
    public void setup() throws ParseException {
        MockitoAnnotations.initMocks(this);

        offeringEntities = new ArrayList<>();
        offeringEntities.add(offeringEntity);

        when(offeringEntity.getOfferingId()).thenReturn(TestUtils.OFFERING_ID);
        when(offeringEntity.getDate()).thenReturn(TestUtils.OFFERING_START_DATE);
        when(offeringEntity.getTime()).thenReturn(TestUtils.OFFERING_START_TIME);
        when(offeringEntity.getPrice()).thenReturn(priceEntity);
        when(offeringEntity.getProductEntity()).thenReturn(productEntity);

        when(product.getProductId()).thenReturn(TestUtils.PRODUCT_ID);

        when(sellingPrice.getCurrency()).thenReturn(TestUtils.PRICE_CURRENCY);
        when(sellingPrice.getAdultPrice()).thenReturn(TestUtils.PRICE_ADULT);

        when(productEntityDataMapper.transform(productEntity, null)).thenReturn(product);
        when(priceEntityDataMapper.transform(priceEntity, null)).thenReturn(sellingPrice);

        mapper = new OfferingDataMapper(priceEntityDataMapper, productEntityDataMapper);
    }

    @Test
    public void transformOfferingToOfferingEntity() throws ParseException {
        List<Offering> offeringEntityList = mapper.transform(offeringEntities, null);
        verify(priceEntityDataMapper, times(1)).transform(priceEntity, null);
        verify(productEntityDataMapper, times(1)).transform(productEntity, null);
        assertNotNull(offeringEntityList);
        assertTrue(offeringEntityList.size() > 0);

        Offering transformedOffering = offeringEntityList.get(0);
        assertNotNull(transformedOffering);

        assertEquals(offeringEntity.getOfferingId(), transformedOffering.getId());
        assertEquals(DateUtil.parseDateResponse(offeringEntity.getDate(), offeringEntity.getTime())
                , transformedOffering.getDate());

        //Product
        assertEquals(product.getProductId(), transformedOffering.getProduct().getProductId());

        //Price
        assertEquals(sellingPrice.getAdultPrice(), transformedOffering.getPrice().getAdultPrice());
        assertEquals(sellingPrice.getCurrency(), transformedOffering.getPrice().getCurrency());

    }
}
