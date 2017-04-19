package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.service.response.OfferingResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.data.service.response.SellingPriceResponse;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class OfferingResponseMapperTest {

    @Mock ProductResponseDataMapper productResponseDataMapper;
    @Mock PriceResponseMapper priceResponseMapper;
    @Mock OfferingResponse testOfferingResponse;
    @Mock ProductResponse testProductResponse;
    @Mock SellingPriceResponse testPriceResponse;
    @Mock Product testProduct;
    @Mock SellingPrice testPrice;

    private List<OfferingResponse> offeringResponseList;
    private OfferingResponseMapper mapper;
    private Calendar startDate;

    @Before
    public void setup() throws ParseException {
        MockitoAnnotations.initMocks(this);

        startDate = Calendar.getInstance();
        startDate.setTime(TestUtils.getOfferingSampleDate());

        mapper = new OfferingResponseMapper(productResponseDataMapper, priceResponseMapper);
        offeringResponseList = new ArrayList<>();
        offeringResponseList.add(testOfferingResponse);

        when(testProduct.getProductId()).thenReturn(TestUtils.PRODUCT_ID);

        when(testPrice.getCurrency()).thenReturn(TestUtils.PRICE_CURRENCY);
        when(testPrice.getAdultPrice()).thenReturn(TestUtils.PRICE_ADULT);

        when(testOfferingResponse.getOfferingId()).thenReturn(TestUtils.OFFERING_ID);
        when(testOfferingResponse.getOfferingDate()).thenReturn(TestUtils.OFFERING_START_DATE);
        when(testOfferingResponse.getOfferingTime()).thenReturn(TestUtils.OFFERING_START_TIME);
        when(testOfferingResponse.getOfferingPrice()).thenReturn(testPriceResponse);

        when(productResponseDataMapper.transform(testProductResponse, null)).thenReturn(testProduct);
        when(priceResponseMapper.transform(testPriceResponse, null)).thenReturn(testPrice);
    }

    @Test
    public void transformOfferingResponseToOfferingTest() {
        List<Offering> transformedOfferings = mapper.transform(offeringResponseList, testProductResponse);
        assertNotNull(transformedOfferings);
        assertTrue(transformedOfferings.size() > 0);

        Offering transformedOffering = transformedOfferings.get(0);
        assertNotNull(transformedOffering);
        assertEquals(testOfferingResponse.getOfferingId(), transformedOffering.getId());
        assertEquals(startDate.getTime(), transformedOffering.getDate());

        //Product
        assertEquals(testProduct.getProductId(), transformedOffering.getProduct().getProductId());

        //Price
        assertEquals(testPrice.getCurrency(), transformedOffering.getPrice().getCurrency());
        assertEquals(testPrice.getAdultPrice(), transformedOffering.getPrice().getAdultPrice());
    }

}
