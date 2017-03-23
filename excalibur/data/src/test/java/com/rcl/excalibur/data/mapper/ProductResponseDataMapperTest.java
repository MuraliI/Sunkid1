package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.data.service.response.MediaResponse;
import com.rcl.excalibur.data.service.response.ProductActivityLevelResponse;
import com.rcl.excalibur.data.service.response.ProductAdvisementResponse;
import com.rcl.excalibur.data.service.response.ProductCategoryResponse;
import com.rcl.excalibur.data.service.response.ProductCostTypeResponse;
import com.rcl.excalibur.data.service.response.ProductDurationResponse;
import com.rcl.excalibur.data.service.response.ProductLocationResponse;
import com.rcl.excalibur.data.service.response.ProductPreferenceResponse;
import com.rcl.excalibur.data.service.response.ProductPreferenceValueResponse;
import com.rcl.excalibur.data.service.response.ProductResponse;
import com.rcl.excalibur.data.service.response.ProductRestrictionAnswerResponse;
import com.rcl.excalibur.data.service.response.ProductRestrictionResponse;
import com.rcl.excalibur.data.service.response.ProductTagsResponse;
import com.rcl.excalibur.data.service.response.ProductTypeResponse;
import com.rcl.excalibur.data.service.response.SellingPriceResponse;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class ProductResponseDataMapperTest {

    ProductResponseDataMapper productResponseDataMapper;

    ProductResponse productResponse1;
    ProductResponse productResponse2;

    ProductTypeResponse productTypeResponse;

    ProductTagsResponse productTagsResponse;
    ProductCategoryResponse productCategoryResponse;

    ProductActivityLevelResponse productActivityLevelResponse;
    MediaItemResponse mediaItemResponse;
    MediaResponse mediaResponse;

    ProductLocationResponse productLocationResponse;

    ProductDurationResponse productDurationResponse;

    ProductCostTypeResponse productCostTypeResponse;

    SellingPriceResponse sellingPriceResponse;

    ProductAdvisementResponse productAdvisementResponse;

    ProductPreferenceValueResponse productPreferenceValueResponse;
    ProductPreferenceResponse productPreferenceResponse;

    ProductRestrictionAnswerResponse productRestrictionAnswerResponse;
    ProductRestrictionResponse productRestrictionResponse;

    @Before
    public void setUp() throws Exception {


        productResponseDataMapper = new ProductResponseDataMapper();

        productResponse1 = new ProductResponse();
        productResponse1.setProductId(100000002814023699L);

        productResponse2 = new ProductResponse();
        productResponse2.setProductId(100000002814023699L);


        productResponse1.setProductCode("2751");

        productResponse2.setProductCode("2751");

        productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setProductTypeId(27L);
        productTypeResponse.setProductTypeName("Show Tickets");
        productTypeResponse.setProductType("ENTERTAINMENT");
        productResponse1.setProductType(productTypeResponse);

        productResponse2.setProductType(productTypeResponse);


        productResponse1.setProductClass("SERVICE");

        productResponse2.setProductClass("SERVICE");

        productTagsResponse = new ProductTagsResponse();
        productTagsResponse.setDescription("Musical");
        productTagsResponse.setTagId("1232");
        productCategoryResponse = new ProductCategoryResponse();
        productCategoryResponse.setCategoryDescription("Entertainment");
        productCategoryResponse.setCategoryId(333333L);
        productCategoryResponse.setProductTags(Arrays.asList(productTagsResponse));
        productResponse1.setProductCategory(Arrays.asList(productCategoryResponse));

        productResponse2.setProductCategory(Arrays.asList(productCategoryResponse));


        productResponse1.setProductRank(1);

        productResponse2.setProductRank(1);


        productResponse1.setReservationRequired(true);

        productResponse2.setReservationRequired(true);


        productResponse1.setScheduable(false);

        productResponse2.setScheduable(false);

        mediaItemResponse = new MediaItemResponse();
        mediaItemResponse.setMediaRefLink("link");
        mediaItemResponse.setMediaType("media type");
        mediaResponse = new MediaResponse();
        mediaResponse.setMediaItem(Arrays.asList(mediaItemResponse));
        productActivityLevelResponse = new ProductActivityLevelResponse();
        productActivityLevelResponse = new ProductActivityLevelResponse();
        productActivityLevelResponse.setActivityLevelDescription("ActivityLevelDescription");
        productActivityLevelResponse.setActivityLevelId(21234L);
        productActivityLevelResponse.setActivityLevelTitle("ActivityLevelTitle");
        productActivityLevelResponse.setActivityLevelMedia(mediaResponse);
        productResponse1.setActivityLevel(productActivityLevelResponse);

        productResponse2.setActivityLevel(productActivityLevelResponse);

        productLocationResponse = new ProductLocationResponse();
        productLocationResponse.setLocationId("RYLTH_QN");
        productLocationResponse.setLocationCode("RYLTH_QN");
        productLocationResponse.setLocationType("VENUE");
        productLocationResponse.setOperatingHoursEnd("1200");
        productLocationResponse.setOperatingHoursStart("1100");
        productResponse1.setProductLocation(productLocationResponse);

        productResponse2.setProductLocation(productLocationResponse);

        productDurationResponse = new ProductDurationResponse();
        productDurationResponse.setDurationInMinutes(4);
        productDurationResponse.setLagTimeInMinutes(6);
        productDurationResponse.setLeadTimeInMinutes(5);
        productDurationResponse.setAtYourLeisure(true);
        productResponse1.setProductDuration(productDurationResponse);

        productResponse2.setProductDuration(productDurationResponse);

        productCostTypeResponse = new ProductCostTypeResponse();
        productCostTypeResponse.setCostTypeCode("costypecode");
        productCostTypeResponse.setCostTypeDescription("costypedescription");
        productCostTypeResponse.setCostTypeTitle("costypetittle");
        productCostTypeResponse.setCostTypeMedia(mediaResponse);
        productResponse1.setCostType(productCostTypeResponse);

        productResponse2.setCostType(productCostTypeResponse);

        sellingPriceResponse = new SellingPriceResponse();
        sellingPriceResponse.setAdultPrice(3f);
        sellingPriceResponse.setChildPrice(4f);
        sellingPriceResponse.setInfantPrice(5f);
        sellingPriceResponse.setCurrency("USD");
        productResponse1.setStartingFromPrice(sellingPriceResponse);

        productResponse2.setStartingFromPrice(sellingPriceResponse);

        productAdvisementResponse = new ProductAdvisementResponse();
        productAdvisementResponse.setAdvisementDescription("AdvisementDescription");
        productAdvisementResponse.setAdvisementId(1234L);
        productAdvisementResponse.setAdvisementName("AdvisementName");
        productAdvisementResponse.setAdvisementTitle("AdvisementTitle");
        productAdvisementResponse.setAdvisementType("AdvisementType");
        productAdvisementResponse.setAdvisementMedia(mediaResponse);
        productResponse1.setAdvisements(Arrays.asList(productAdvisementResponse));

        productResponse2.setAdvisements(Arrays.asList(productAdvisementResponse));


        productPreferenceValueResponse = new ProductPreferenceValueResponse();
        productPreferenceValueResponse.setPreferenceValueId(1234L);
        productPreferenceValueResponse.setPreferenceValueCode("true");
        productPreferenceValueResponse.setPreferenceValueName("false");
        productPreferenceResponse = new ProductPreferenceResponse();
        productPreferenceResponse.setPreferenceId(12345L);
        productPreferenceResponse.setPreferenceName("PreferenceName");
        productPreferenceResponse.setPreferenceType("PreferenceType");
        productPreferenceResponse.setPreferenceValue(Arrays.asList(productPreferenceValueResponse));
        productResponse1.setPreferences(Arrays.asList(productPreferenceResponse));

        productResponse2.setPreferences(Arrays.asList(productPreferenceResponse));


        productRestrictionAnswerResponse = new ProductRestrictionAnswerResponse();
        productRestrictionAnswerResponse.setRestrictionAnswerDisplayText("RestrictionAnswerDisplayText");
        productRestrictionResponse = new ProductRestrictionResponse();
        productRestrictionResponse.setRestrictionAnswers(Arrays.asList(productRestrictionAnswerResponse));
        productRestrictionResponse.setRestrictionMedia(mediaResponse);
        productRestrictionResponse.setRestrictionDescription("RestrictionDescription");
        productRestrictionResponse.setRestrictionDisplayText("RestrictionDisplayText");
        productRestrictionResponse.setRestrictionId(123456L);
        productRestrictionResponse.setRestrictionQuestion("RestrictionQuestion");
        productRestrictionResponse.setRestrictionTitle("RestrictionTitle");
        productRestrictionResponse.setRestrictionType("RestrictionType");
        productResponse1.setRestrictions(Arrays.asList(productRestrictionResponse));

        productResponse2.setRestrictions(Arrays.asList(productRestrictionResponse));


        productResponse1.setProductTitle("ProductTitle");

        productResponse2.setProductTitle("ProductTitle");


        productResponse1.setProductShortDescription("ProductShortDescription");

        productResponse2.setProductShortDescription("ProductShortDescription");


        productResponse1.setProductLongDescription("ProductLongDescription");

        productResponse2.setProductLongDescription("ProductLongDescription");


        productResponse1.setProductMedia(mediaResponse);

        productResponse2.setProductMedia(mediaResponse);

    }

    @Test
    public void transform() throws Exception {

        Product product = productResponseDataMapper.transform(productResponse1);
        assertNotNull(product);
        assertEquals(productResponse1.getProductId(), product.getProductId());

        assertEquals(productResponse1.getProductCode(), product.getProductCode());

        assertEquals(productResponse1.getProductType().getProductType(), product.getProductType().getProductType());
        assertEquals(productResponse1.getProductType().getProductTypeName(), product.getProductType().getProductTypeName());
        assertEquals(productResponse1.getProductType().getProductTypeId(), product.getProductType().getProductTypeId());

        assertEquals(productResponse1.getProductClass(), product.getProductClass());

        for (int i = 0; i < productResponse1.getProductCategory().size(); i++) {
            assertEquals(productResponse1.getProductCategory().get(i).getCategoryDescription(), product.getProductCategory().get(i).getCategoryDescription());
            assertEquals(productResponse1.getProductCategory().get(i).getCategoryId(), product.getProductCategory().get(i).getCategoryId());

            for (int j = 0; j < productResponse1.getProductCategory().get(i).getProductTags().size(); j++) {
                assertEquals(productResponse1.getProductCategory().get(i).getProductTags().get(j).getDescription(), product.getProductCategory().get(i).getProductTags().get(j).getDescription());
                assertEquals(productResponse1.getProductCategory().get(i).getProductTags().get(j).getTagId(), product.getProductCategory().get(i).getProductTags().get(j).getTagId());
            }
        }

        assertEquals(productResponse1.getProductRank(), product.getProductRank());

        assertEquals(productResponse1.isReservationRequired(), product.isReservationRequired());

        assertEquals(productResponse1.isScheduable(), product.isScheduable());

        assertEquals(productResponse1.getActivityLevel().getActivityLevelDescription(), product.getActivityLevel().getActivityLevelDescription());
        assertEquals(productResponse1.getActivityLevel().getActivityLevelId(), product.getActivityLevel().getActivityLevelId());
        assertEquals(productResponse1.getActivityLevel().getActivityLevelTitle(), product.getActivityLevel().getActivityLevelTitle());
        for (int i = 0; i < productResponse1.getActivityLevel().getActivityLevelMedia().getMediaItem().size(); i++) {
            MediaItemResponse item = productResponse1.getActivityLevel().getActivityLevelMedia().getMediaItem().get(i);
            MediaItem itemProduct = product.getActivityLevel().getActivityLevelMedia().getMediaItem().get(i);
            assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
            assertEquals(item.getMediaType(), itemProduct.getMediaType());
        }

        assertEquals(productResponse1.getProductLocation().getLocationId(), product.getProductLocation().getLocationId());
        assertEquals(productResponse1.getProductLocation().getLocationCode(), product.getProductLocation().getLocationCode());
        assertEquals(productResponse1.getProductLocation().getLocationType(), product.getProductLocation().getLocationType());
        assertEquals(productResponse1.getProductLocation().getOperatingHoursEnd(), product.getProductLocation().getOperatingHoursEnd());
        assertEquals(productResponse1.getProductLocation().getOperatingHoursStart(), product.getProductLocation().getOperatingHoursStart());

        assertEquals(productResponse1.getProductDuration().getDurationInMinutes(), product.getProductDuration().getDurationInMinutes());
        assertEquals(productResponse1.getProductDuration().getLagTimeInMinutes(), product.getProductDuration().getLagTimeInMinutes());
        assertEquals(productResponse1.getProductDuration().getLeadTimeInMinutes(), product.getProductDuration().getLeadTimeInMinutes());
        assertEquals(productResponse1.getProductDuration().isAtYourLeisure(), product.getProductDuration().isAtYourLeisure());

        assertEquals(productResponse1.getCostType().getCostTypeCode(), product.getCostType().getCostTypeCode());
        assertEquals(productResponse1.getCostType().getCostTypeDescription(), product.getCostType().getCostTypeDescription());
        assertEquals(productResponse1.getCostType().getCostTypeTitle(), product.getCostType().getCostTypeTitle());
        for (int i = 0; i < productResponse1.getCostType().getCostTypeMedia().getMediaItem().size(); i++) {
            MediaItemResponse item = productResponse1.getCostType().getCostTypeMedia().getMediaItem().get(i);
            MediaItem itemProduct = product.getCostType().getCostTypeMedia().getMediaItem().get(i);
            assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
            assertEquals(item.getMediaType(), itemProduct.getMediaType());
        }

        assertEquals(productResponse1.getStartingFromPrice().getAdultPrice(), product.getStartingFromPrice().getAdultPrice(), 0);
        assertEquals(productResponse1.getStartingFromPrice().getChildPrice(), product.getStartingFromPrice().getChildPrice(), 0);
        assertEquals(productResponse1.getStartingFromPrice().getInfantPrice(), product.getStartingFromPrice().getInfantPrice(), 0);
        assertEquals(productResponse1.getStartingFromPrice().getCurrency(), product.getStartingFromPrice().getCurrency());

        for (int i = 0; i < productResponse1.getAdvisements().size(); i++) {
            assertEquals(productResponse1.getAdvisements().get(i).getAdvisementDescription(), product.getAdvisements().get(i).getAdvisementDescription());
            assertEquals(productResponse1.getAdvisements().get(i).getAdvisementId(), product.getAdvisements().get(i).getAdvisementId());
            assertEquals(productResponse1.getAdvisements().get(i).getAdvisementName(), product.getAdvisements().get(i).getAdvisementName());
            assertEquals(productResponse1.getAdvisements().get(i).getAdvisementTitle(), product.getAdvisements().get(i).getAdvisementTitle());
            assertEquals(productResponse1.getAdvisements().get(i).getAdvisementType(), product.getAdvisements().get(i).getAdvisementType());

            for (int j = 0; j < productResponse1.getAdvisements().get(i).getAdvisementMedia().getMediaItem().size(); j++) {
                MediaItemResponse item = productResponse1.getAdvisements().get(i).getAdvisementMedia().getMediaItem().get(j);
                MediaItem itemProduct = product.getAdvisements().get(i).getAdvisementMedia().getMediaItem().get(j);
                assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                assertEquals(item.getMediaType(), itemProduct.getMediaType());
            }
        }

        for (int i = 0; i < productResponse1.getPreferences().size(); i++) {
            assertEquals(productResponse1.getPreferences().get(i).getPreferenceId(), product.getPreferences().get(i).getPreferenceId());
            assertEquals(productResponse1.getPreferences().get(i).getPreferenceName(), product.getPreferences().get(i).getPreferenceName());
            assertEquals(productResponse1.getPreferences().get(i).getPreferenceType(), product.getPreferences().get(i).getPreferenceType());
            for (int j = 0; j < productResponse1.getPreferences().get(i).getPreferenceValue().size(); j++) {
                assertEquals(productResponse1.getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueId(), product.getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueId());
                assertEquals(productResponse1.getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueCode(), product.getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueCode());
                assertEquals(productResponse1.getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueName(), product.getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueName());
            }
        }

        for (int i = 0; i < productResponse1.getRestrictions().size(); i++) {
            assertEquals(productResponse1.getRestrictions().get(i).getRestrictionType(), product.getRestrictions().get(i).getRestrictionType());
            assertEquals(productResponse1.getRestrictions().get(i).getRestrictionTitle(), product.getRestrictions().get(i).getRestrictionTitle());
            assertEquals(productResponse1.getRestrictions().get(i).getRestrictionQuestion(), product.getRestrictions().get(i).getRestrictionQuestion());
            assertEquals(productResponse1.getRestrictions().get(i).getRestrictionId(), product.getRestrictions().get(i).getRestrictionId());
            assertEquals(productResponse1.getRestrictions().get(i).getRestrictionDisplayText(), product.getRestrictions().get(i).getRestrictionDisplayText());
            assertEquals(productResponse1.getRestrictions().get(i).getRestrictionDescription(), product.getRestrictions().get(i).getRestrictionDescription());

            for (int j = 0; j < productResponse1.getRestrictions().get(i).getRestrictionAnswers().size(); j++) {
                assertEquals(productResponse1.getRestrictions().get(i).getRestrictionAnswers().get(j).getRestrictionAnswerDisplayText(), product.getRestrictions().get(i).getRestrictionAnswers().get(j).getRestrictionAnswerDisplayText());
            }

            for (int j = 0; j < productResponse1.getRestrictions().get(i).getRestrictionMedia().getMediaItem().size(); j++) {
                MediaItemResponse item = productResponse1.getRestrictions().get(i).getRestrictionMedia().getMediaItem().get(j);
                MediaItem itemProduct = product.getRestrictions().get(i).getRestrictionMedia().getMediaItem().get(j);
                assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                assertEquals(item.getMediaType(), itemProduct.getMediaType());
            }
        }

        assertEquals(productResponse1.getProductTitle(), product.getProductTitle());

        assertEquals(productResponse1.getProductShortDescription(), product.getProductShortDescription());

        assertEquals(productResponse1.getProductLongDescription(), product.getProductLongDescription());


        for (int i = 0; i < productResponse1.getProductMedia().getMediaItem().size(); i++) {
            MediaItemResponse item = productResponse1.getProductMedia().getMediaItem().get(i);
            MediaItem itemProduct = product.getProductMedia().getMediaItem().get(i);
            assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
            assertEquals(item.getMediaType(), itemProduct.getMediaType());
        }
    }

    @Test
    public void transformList() throws Exception {
        final List<ProductResponse> productResponses = new ArrayList();
        productResponses.add(productResponse1);
        productResponses.add(productResponse2);

        List<Product> productList = productResponseDataMapper.transform(productResponses);
        assertNotNull(productList);
        assertFalse(productList.isEmpty());
        assertEquals(productResponses.size(), productList.size());

        for (int z = 0; z < productList.size(); z++) {

            assertEquals(productResponses.get(z).getProductId(), productList.get(z).getProductId());


            assertEquals(productResponses.get(z).getProductCode(), productList.get(z).getProductCode());

            assertEquals(productResponses.get(z).getProductType().getProductType(), productList.get(z).getProductType().getProductType());
            assertEquals(productResponses.get(z).getProductType().getProductTypeName(), productList.get(z).getProductType().getProductTypeName());
            assertEquals(productResponses.get(z).getProductType().getProductTypeId(), productList.get(z).getProductType().getProductTypeId());

            assertEquals(productResponses.get(z).getProductClass(), productList.get(z).getProductClass());

            for (int i = 0; i < productResponses.get(z).getProductCategory().size(); i++) {
                assertEquals(productResponses.get(z).getProductCategory().get(i).getCategoryDescription(), productList.get(z).getProductCategory().get(i).getCategoryDescription());
                assertEquals(productResponses.get(z).getProductCategory().get(i).getCategoryId(), productList.get(z).getProductCategory().get(i).getCategoryId());

                for (int j = 0; j < productResponses.get(z).getProductCategory().get(i).getProductTags().size(); j++) {
                    assertEquals(productResponses.get(z).getProductCategory().get(i).getProductTags().get(j).getDescription(), productList.get(z).getProductCategory().get(i).getProductTags().get(j).getDescription());
                    assertEquals(productResponses.get(z).getProductCategory().get(i).getProductTags().get(j).getTagId(), productList.get(z).getProductCategory().get(i).getProductTags().get(j).getTagId());
                }
            }

            assertEquals(productResponses.get(z).getProductRank(), productList.get(z).getProductRank());

            assertEquals(productResponses.get(z).isReservationRequired(), productList.get(z).isReservationRequired());

            assertEquals(productResponses.get(z).isScheduable(), productList.get(z).isScheduable());

            assertEquals(productResponses.get(z).getActivityLevel().getActivityLevelDescription(), productList.get(z).getActivityLevel().getActivityLevelDescription());
            assertEquals(productResponses.get(z).getActivityLevel().getActivityLevelId(), productList.get(z).getActivityLevel().getActivityLevelId());
            assertEquals(productResponses.get(z).getActivityLevel().getActivityLevelTitle(), productList.get(z).getActivityLevel().getActivityLevelTitle());
            for (int i = 0; i < productResponses.get(z).getActivityLevel().getActivityLevelMedia().getMediaItem().size(); i++) {
                MediaItemResponse item = productResponses.get(z).getActivityLevel().getActivityLevelMedia().getMediaItem().get(i);
                MediaItem itemProduct = productList.get(z).getActivityLevel().getActivityLevelMedia().getMediaItem().get(i);
                assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                assertEquals(item.getMediaType(), itemProduct.getMediaType());
            }

            assertEquals(productResponses.get(z).getProductLocation().getLocationId(), productList.get(z).getProductLocation().getLocationId());
            assertEquals(productResponses.get(z).getProductLocation().getLocationCode(), productList.get(z).getProductLocation().getLocationCode());
            assertEquals(productResponses.get(z).getProductLocation().getLocationType(), productList.get(z).getProductLocation().getLocationType());
            assertEquals(productResponses.get(z).getProductLocation().getOperatingHoursEnd(), productList.get(z).getProductLocation().getOperatingHoursEnd());
            assertEquals(productResponses.get(z).getProductLocation().getOperatingHoursStart(), productList.get(z).getProductLocation().getOperatingHoursStart());

            assertEquals(productResponses.get(z).getProductDuration().getDurationInMinutes(), productList.get(z).getProductDuration().getDurationInMinutes());
            assertEquals(productResponses.get(z).getProductDuration().getLagTimeInMinutes(), productList.get(z).getProductDuration().getLagTimeInMinutes());
            assertEquals(productResponses.get(z).getProductDuration().getLeadTimeInMinutes(), productList.get(z).getProductDuration().getLeadTimeInMinutes());
            assertEquals(productResponses.get(z).getProductDuration().isAtYourLeisure(), productList.get(z).getProductDuration().isAtYourLeisure());

            assertEquals(productResponses.get(z).getCostType().getCostTypeCode(), productList.get(z).getCostType().getCostTypeCode());
            assertEquals(productResponses.get(z).getCostType().getCostTypeDescription(), productList.get(z).getCostType().getCostTypeDescription());
            assertEquals(productResponses.get(z).getCostType().getCostTypeTitle(), productList.get(z).getCostType().getCostTypeTitle());
            for (int i = 0; i < productResponses.get(z).getCostType().getCostTypeMedia().getMediaItem().size(); i++) {
                MediaItemResponse item = productResponses.get(z).getCostType().getCostTypeMedia().getMediaItem().get(i);
                MediaItem itemProduct = productList.get(z).getCostType().getCostTypeMedia().getMediaItem().get(i);
                assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                assertEquals(item.getMediaType(), itemProduct.getMediaType());
            }

            assertEquals(productResponses.get(z).getStartingFromPrice().getAdultPrice(), productList.get(z).getStartingFromPrice().getAdultPrice(), 0);
            assertEquals(productResponses.get(z).getStartingFromPrice().getChildPrice(), productList.get(z).getStartingFromPrice().getChildPrice(), 0);
            assertEquals(productResponses.get(z).getStartingFromPrice().getInfantPrice(), productList.get(z).getStartingFromPrice().getInfantPrice(), 0);
            assertEquals(productResponses.get(z).getStartingFromPrice().getCurrency(), productList.get(z).getStartingFromPrice().getCurrency());

            for (int i = 0; i < productResponses.get(z).getAdvisements().size(); i++) {
                assertEquals(productResponses.get(z).getAdvisements().get(i).getAdvisementDescription(), productList.get(z).getAdvisements().get(i).getAdvisementDescription());
                assertEquals(productResponses.get(z).getAdvisements().get(i).getAdvisementId(), productList.get(z).getAdvisements().get(i).getAdvisementId());
                assertEquals(productResponses.get(z).getAdvisements().get(i).getAdvisementName(), productList.get(z).getAdvisements().get(i).getAdvisementName());
                assertEquals(productResponses.get(z).getAdvisements().get(i).getAdvisementTitle(), productList.get(z).getAdvisements().get(i).getAdvisementTitle());
                assertEquals(productResponses.get(z).getAdvisements().get(i).getAdvisementType(), productList.get(z).getAdvisements().get(i).getAdvisementType());

                for (int j = 0; j < productResponses.get(z).getAdvisements().get(i).getAdvisementMedia().getMediaItem().size(); j++) {
                    MediaItemResponse item = productResponses.get(z).getAdvisements().get(i).getAdvisementMedia().getMediaItem().get(j);
                    MediaItem itemProduct = productList.get(z).getAdvisements().get(i).getAdvisementMedia().getMediaItem().get(j);
                    assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                    assertEquals(item.getMediaType(), itemProduct.getMediaType());
                }
            }

            for (int i = 0; i < productResponses.get(z).getPreferences().size(); i++) {
                assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceId(), productList.get(z).getPreferences().get(i).getPreferenceId());
                assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceName(), productList.get(z).getPreferences().get(i).getPreferenceName());
                assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceType(), productList.get(z).getPreferences().get(i).getPreferenceType());

                for (int j = 0; j < productResponses.get(z).getPreferences().get(i).getPreferenceValue().size(); j++) {
                    assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueId(), productList.get(z).getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueId());
                    assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueCode(), productList.get(z).getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueCode());
                    assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueName(), productList.get(z).getPreferences().get(i).getPreferenceValue().get(j).getPreferenceValueName());
                }
            }

            for (int i = 0; i < productResponses.get(z).getRestrictions().size(); i++) {
                assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionType(), productList.get(z).getRestrictions().get(i).getRestrictionType());
                assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionTitle(), productList.get(z).getRestrictions().get(i).getRestrictionTitle());
                assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionQuestion(), productList.get(z).getRestrictions().get(i).getRestrictionQuestion());
                assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionId(), productList.get(z).getRestrictions().get(i).getRestrictionId());
                assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionDisplayText(), productList.get(z).getRestrictions().get(i).getRestrictionDisplayText());
                assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionDescription(), productList.get(z).getRestrictions().get(i).getRestrictionDescription());

                for (int j = 0; j < productResponses.get(z).getRestrictions().get(i).getRestrictionAnswers().size(); j++) {
                    assertEquals(productResponses.get(z).getRestrictions().get(i).getRestrictionAnswers().get(j).getRestrictionAnswerDisplayText(), productList.get(z).getRestrictions().get(i).getRestrictionAnswers().get(j).getRestrictionAnswerDisplayText());
                }

                for (int j = 0; j < productResponses.get(z).getRestrictions().get(i).getRestrictionMedia().getMediaItem().size(); j++) {
                    MediaItemResponse item = productResponses.get(z).getRestrictions().get(i).getRestrictionMedia().getMediaItem().get(j);
                    MediaItem itemProduct = productList.get(z).getRestrictions().get(i).getRestrictionMedia().getMediaItem().get(j);
                    assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                    assertEquals(item.getMediaType(), itemProduct.getMediaType());
                }
            }

            assertEquals(productResponses.get(z).getProductTitle(), productList.get(z).getProductTitle());

            assertEquals(productResponses.get(z).getProductShortDescription(), productList.get(z).getProductShortDescription());

            assertEquals(productResponses.get(z).getProductLongDescription(), productList.get(z).getProductLongDescription());


            for (int i = 0; i < productResponses.get(z).getProductMedia().getMediaItem().size(); i++) {
                MediaItemResponse item = productResponses.get(z).getProductMedia().getMediaItem().get(i);
                MediaItem itemProduct = productList.get(z).getProductMedia().getMediaItem().get(i);
                assertEquals(item.getMediaRefLink(), itemProduct.getMediaRefLink());
                assertEquals(item.getMediaType(), itemProduct.getMediaType());
            }
        }


    }

}