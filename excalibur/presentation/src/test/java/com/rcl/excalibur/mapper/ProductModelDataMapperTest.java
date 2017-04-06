package com.rcl.excalibur.mapper;

import com.rcl.excalibur.data.service.response.ProductRestrictionResponse;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.model.ProductModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ProductModelDataMapperTest {

    ProductModelDataMapper productModelDataMapper;
    Product entity1;
    Product entity2;
    ProductAdvisement advisementAttire,advisementKnowBeforeYouGo;
    ProductRestriction  ageRestriction,heigthRestriction;

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
        entity1.setExperience("Enjoy the travel!");

        List<ProductAdvisement> productAdvisementList = new ArrayList<>();
        advisementAttire = new ProductAdvisement();
        advisementAttire.setAdvisementDescription("Casual");
        advisementAttire.setAdvisementType(ProductAdvisement.ATTIRE);
        productAdvisementList.add(advisementAttire);

        advisementKnowBeforeYouGo = new ProductAdvisement();
        advisementKnowBeforeYouGo.setAdvisementDescription("Arrive 15 minutes early, Wear closedtoed shoes");
        advisementKnowBeforeYouGo.setAdvisementType(ProductAdvisement.KNOW_BEFORE_YOU_GO);
        productAdvisementList.add(advisementKnowBeforeYouGo);
        entity1.setAdvisements(productAdvisementList);

        List<ProductRestriction> productRestrictionList = new ArrayList<>();
        ageRestriction = new ProductRestriction();
        ageRestriction.setRestrictionId(1L);
        ageRestriction.setRestrictionType(ProductRestriction.AGE);
        ageRestriction.setRestrictionTitle("Age Restritions");
        ageRestriction.setRestrictionDisplayText("12+");
        ageRestriction.setRestrictionDescription("12+");
        productRestrictionList.add(ageRestriction);

        heigthRestriction = new ProductRestriction();
        heigthRestriction.setRestrictionId(1L);
        heigthRestriction.setRestrictionType(ProductRestriction.HEIGHT);
        heigthRestriction.setRestrictionTitle("Height Restrictions");
        heigthRestriction.setRestrictionDisplayText("None");
        heigthRestriction.setRestrictionDescription("None");
        productRestrictionList.add(heigthRestriction);

        entity1.setRestrictions(productRestrictionList);



        entity2 = null;
    }

    @Test
    public void transformToEntity() throws Exception {
        ProductModel productModel = productModelDataMapper.transform(entity1);
        assertNotNull(productModel);
        assertEquals(entity1.getProductDuration().getDurationInMinutes(), productModel.getDuration());
        assertEquals(entity1.getProductReservationInformation(), productModel.getReservationInformation());
        assertEquals(entity1.getProductShortDescription(), productModel.getDescription());
        assertEquals(entity1.getExperience(), productModel.getExperience());

        for (int i = 0; i < entity1.getAdvisements().size(); i++) {
            assertEquals(entity1.getAdvisements().get(i).getAdvisementType(), productModel.getAdvisementsAndReestrictions().keySet().toArray()[i]);
            assertEquals(entity1.getAdvisements().get(i).getAdvisementDescription(), productModel.getAdvisementsAndReestrictions().values().toArray()[i]);
        }

    }

}
