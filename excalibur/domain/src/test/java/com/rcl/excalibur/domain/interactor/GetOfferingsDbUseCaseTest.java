package com.rcl.excalibur.domain.interactor;


import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.SellingPrice;
import com.rcl.excalibur.domain.repository.OfferingRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GetOfferingsDbUseCaseTest {

    @Mock OfferingRepository offeringRepository;

    private GetOfferingsDbUseCase getOfferingsDbUseCase;
    private Date offeringsDate;
    private Product offeringsForProduct;
    private Offering offering;
    private SellingPrice price;

    @Before
    public void setup() throws ParseException {
        MockitoAnnotations.initMocks(this);

        //Offering date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm", Locale.US);
        offeringsDate = dateFormat.parse("201705010600");

        //Offering Product
        offeringsForProduct = new Product();
        offeringsForProduct.setProductId("1");

        //Offering Price
        price = new SellingPrice();
        price.setCurrency("USD");
        price.setAdultPrice(25.0F);

        offering = new Offering();
        offering.setId("2");
        offering.setCompleteDate(offeringsDate);
        offering.setProduct(offeringsForProduct);
        offering.setPrice(price);

        List<Offering> offeringList = new ArrayList<>();
        offeringList.add(offering);

        when(offeringRepository.getForDay(offeringsDate)).thenReturn(offeringList);
        when(offeringRepository.getOfferingsForProduct(offeringsForProduct)).thenReturn(offeringList);

        getOfferingsDbUseCase = new GetOfferingsDbUseCase(offeringRepository);
    }

    @Test
    public void getOfferingsForDayTest() {
        List<Offering> obtainedOfferings = getOfferingsDbUseCase.getAllForDay(offeringsDate);
        verify(offeringRepository, times(1)).getForDay(offeringsDate);
        assertNotNull(obtainedOfferings);
        assertTrue(obtainedOfferings.size() > 0);

        Offering obtainedOffering = obtainedOfferings.get(0);
        assertNotNull(obtainedOffering);

        assertEquals(offering.getId(), obtainedOffering.getId());
        assertEquals(offering.getCompleteDate(), obtainedOffering.getCompleteDate());
        assertEquals(offering.getPrice(), obtainedOffering.getPrice());
        assertEquals(offering.getProduct(), obtainedOffering.getProduct());
    }

    @Test
    public void getOfferingsForProduct() {
        List<Offering> obtainedOfferings = getOfferingsDbUseCase.getOfferingsForProduct(offeringsForProduct);
        verify(offeringRepository, times(1)).getOfferingsForProduct(offeringsForProduct);
        assertNotNull(obtainedOfferings);
        assertTrue(obtainedOfferings.size() > 0);

        Offering obtainedOffering = obtainedOfferings.get(0);
        assertNotNull(obtainedOffering);

        assertEquals(offering.getId(), obtainedOffering.getId());
        assertEquals(offering.getCompleteDate(), obtainedOffering.getCompleteDate());
        assertEquals(offering.getPrice(), obtainedOffering.getPrice());
        assertEquals(offering.getProduct(), obtainedOffering.getProduct());
    }
}
