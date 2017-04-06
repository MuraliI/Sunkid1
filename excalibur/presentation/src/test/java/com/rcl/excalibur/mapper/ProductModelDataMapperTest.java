package com.rcl.excalibur.mapper;

import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.model.ProductModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductModelDataMapperTest {

    ProductModelDataMapper productModelDataMapper;
    Product entity1;
    Product entity2;
    ProductAdvisement advisementAccessibility, advisementKnowBeforeYouGo;
    ProductRestriction ageRestriction, heigthRestriction;

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
        Media media = new Media();
        List<MediaItem> mediaItemList = new ArrayList<>();
        MediaItem mediaItem = new MediaItem();
        mediaItem.setMediaRefLink("htts://algo.png");
        mediaItem.setMediaType("Type");
        mediaItemList.add(mediaItem);
        media.setMediaItem(mediaItemList);

        advisementAccessibility = new ProductAdvisement();
        advisementAccessibility.setAdvisementId(ProductAdvisement.ACCESSIBILITY);
        advisementAccessibility.setAdvisementDescription("Wheelchair accessible");
        advisementAccessibility.setAdvisementType("ACCESSIBILITY");
        advisementAccessibility.setAdvisementMedia(media);
        productAdvisementList.add(advisementAccessibility);

        advisementKnowBeforeYouGo = new ProductAdvisement();
        advisementKnowBeforeYouGo.setAdvisementId(ProductAdvisement.KNOW_BEFORE_YOU_GO);
        advisementKnowBeforeYouGo.setAdvisementDescription("Wheelchair accessible");
        advisementKnowBeforeYouGo.setAdvisementType("KNOW_BEFORE_YOU_GO");
        advisementKnowBeforeYouGo.setAdvisementMedia(media);
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
        heigthRestriction.setRestrictionTitle("WEIGHT");
        heigthRestriction.setRestrictionDisplayText("Should be less than 200 pounds");
        heigthRestriction.setRestrictionDescription("Should be less than 200 pounds");
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
            if(entity1.getAdvisements().get(i).getAdvisementId().equals(ProductAdvisement.ACCESSIBILITY)){
                assertEquals(entity1.getAdvisements().get(i).getAdvisementDescription(), productModel.getAccessibilities().get(i).getDescription());
                assertEquals(entity1.getAdvisements().get(i).getAdvisementMedia().getMediaItem().get(0).getMediaRefLink(), productModel.getAccessibilities().get(i).getImageUrl());
                assertEquals(entity1.getAdvisements().get(i).getAdvisementTitle(), productModel.getAccessibilities().get(i).getSubtitle());
            }
            else{
                assertEquals(entity1.getAdvisements().get(i).getAdvisementDescription(),
                        productModel.getAdvisementsAndReestrictions().get(entity1.getAdvisements().get(i).getAdvisementId()));

            }
        }
        for (int i = 0; i < entity1.getRestrictions().size(); i++) {
            if(entity1.getRestrictions().get(i).getRestrictionType().equals(ProductRestriction.AGE)) {
                assertEquals(entity1.getRestrictions().get(i).getRestrictionDisplayText(), productModel.getAdvisementsAndReestrictions().get(ProductRestriction.AGE));
            }
            else if (entity1.getRestrictions().get(i).getRestrictionType().equals(ProductRestriction.HEIGHT)){
                assertEquals(entity1.getRestrictions().get(i).getRestrictionDisplayText(), productModel.getAdvisementsAndReestrictions().get(ProductRestriction.HEIGHT));
            }
        }

    }

}
