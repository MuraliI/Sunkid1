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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ProductResponseDataMapperTest {

    ProductResponseDataMapper productResponseDataMapper;
    @Mock ProductResponse productResponse1;
    @Mock ProductResponse productResponse2;

    @Mock ProductTypeResponse productTypeResponse;

    @Mock ProductTagsResponse productTagsResponse;
    @Mock ProductCategoryResponse productCategoryResponse;

    @Mock ProductActivityLevelResponse productActivityLevelResponse;
    @Mock MediaItemResponse mediaItemResponse;
    @Mock MediaResponse mediaResponse;

    @Mock ProductLocationResponse productLocationResponse;

    @Mock ProductDurationResponse productDurationResponse;

    @Mock ProductCostTypeResponse productCostTypeResponse;

    @Mock SellingPriceResponse sellingPriceResponse;

    @Mock ProductAdvisementResponse productAdvisementResponse;

    @Mock ProductPreferenceValueResponse productPreferenceValueResponse;
    @Mock ProductPreferenceResponse productPreferenceResponse;

    @Mock ProductRestrictionAnswerResponse productRestrictionAnswerResponse;
    @Mock ProductRestrictionResponse productRestrictionResponse;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        productResponseDataMapper = new ProductResponseDataMapper();

        Mockito.when(productResponse1.getProductId()).thenReturn("100000002814023699");

        Mockito.when(productResponse2.getProductId()).thenReturn("100000002814023699");


        Mockito.when(productResponse1.getProductCode()).thenReturn("2751");

        Mockito.when(productResponse2.getProductCode()).thenReturn("2751");


        Mockito.when(productTypeResponse.getProductTypeId()).thenReturn("27");
        Mockito.when(productTypeResponse.getProductTypeName()).thenReturn("Show Tickets");
        Mockito.when(productTypeResponse.getProductType()).thenReturn("ENTERTAINMENT");
        Mockito.when(productResponse1.getProductType()).thenReturn(productTypeResponse);

        Mockito.when(productResponse2.getProductType()).thenReturn(productTypeResponse);


        Mockito.when(productResponse1.getProductClass()).thenReturn("SERVICE");

        Mockito.when(productResponse2.getProductClass()).thenReturn("SERVICE");


        Mockito.when(productTagsResponse.getDescription()).thenReturn("Musical");
        Mockito.when(productTagsResponse.getTagId()).thenReturn("1232");
        Mockito.when(productCategoryResponse.getCategoryDescription()).thenReturn("Entertainment");
        Mockito.when(productCategoryResponse.getCategoryId()).thenReturn("333333");
        Mockito.when(productCategoryResponse.getProductTags()).thenReturn(Arrays.asList(productTagsResponse));
        Mockito.when(productResponse1.getProductCategory()).thenReturn(Arrays.asList(productCategoryResponse));

        Mockito.when(productResponse2.getProductCategory()).thenReturn(Arrays.asList(productCategoryResponse));


        Mockito.when(productResponse1.getProductRank()).thenReturn(1);

        Mockito.when(productResponse2.getProductRank()).thenReturn(1);


        Mockito.when(productResponse1.isReservationRequired()).thenReturn(true);

        Mockito.when(productResponse2.isReservationRequired()).thenReturn(true);


        Mockito.when(productResponse1.isScheduable()).thenReturn(false);

        Mockito.when(productResponse2.isScheduable()).thenReturn(false);


        Mockito.when(mediaItemResponse.getMediaRefLink()).thenReturn("link");
        Mockito.when(mediaItemResponse.getMediaType()).thenReturn("media type");
        Mockito.when(mediaResponse.getMediaItem()).thenReturn(Arrays.asList(mediaItemResponse));
        Mockito.when(productActivityLevelResponse.getActivityLevelDescription()).thenReturn("ActivityLevelDescription");
        Mockito.when(productActivityLevelResponse.getActivityLevelId()).thenReturn("ActivityLevelId");
        Mockito.when(productActivityLevelResponse.getActivityLevelTitle()).thenReturn("ActivityLevelTitle");
        Mockito.when(productActivityLevelResponse.getActivityLevelMedia()).thenReturn(mediaResponse);
        Mockito.when(productResponse1.getActivityLevel()).thenReturn(productActivityLevelResponse);

        Mockito.when(productResponse2.getActivityLevel()).thenReturn(productActivityLevelResponse);


        Mockito.when(productLocationResponse.getLocationId()).thenReturn("RYLTH_QN");
        Mockito.when(productLocationResponse.getLocationCode()).thenReturn("RYLTH_QN");
        Mockito.when(productLocationResponse.getLocationType()).thenReturn("VENUE");
        Mockito.when(productLocationResponse.getOperatingHoursEnd()).thenReturn("1200");
        Mockito.when(productLocationResponse.getOperatingHoursStart()).thenReturn("1100");
        Mockito.when(productResponse1.getProductLocation()).thenReturn(productLocationResponse);

        Mockito.when(productResponse2.getProductLocation()).thenReturn(productLocationResponse);


        Mockito.when(productDurationResponse.getDurationInMinutes()).thenReturn(4);
        Mockito.when(productDurationResponse.getLagTimeInMinutes()).thenReturn(6);
        Mockito.when(productDurationResponse.getLeadTimeInMinutes()).thenReturn(5);
        Mockito.when(productDurationResponse.isAtYourLeisure()).thenReturn(true);
        Mockito.when(productResponse1.getProductDuration()).thenReturn(productDurationResponse);

        Mockito.when(productResponse2.getProductDuration()).thenReturn(productDurationResponse);


        Mockito.when(productCostTypeResponse.getCostTypeCode()).thenReturn("costypecode");
        Mockito.when(productCostTypeResponse.getCostTypeDescription()).thenReturn("costypedescription");
        Mockito.when(productCostTypeResponse.getCostTypeTitle()).thenReturn("costypetittle");
        Mockito.when(productCostTypeResponse.getCostTypeMedia()).thenReturn(mediaResponse);
        Mockito.when(productResponse1.getCostType()).thenReturn(productCostTypeResponse);

        Mockito.when(productResponse2.getCostType()).thenReturn(productCostTypeResponse);


        Mockito.when(sellingPriceResponse.getAdultPrice()).thenReturn(3f);
        Mockito.when(sellingPriceResponse.getChildPrice()).thenReturn(4f);
        Mockito.when(sellingPriceResponse.getInfantPrice()).thenReturn(5f);
        Mockito.when(sellingPriceResponse.getCurrency()).thenReturn("USD");
        Mockito.when(productResponse1.getStartingFromPrice()).thenReturn(sellingPriceResponse);

        Mockito.when(productResponse2.getStartingFromPrice()).thenReturn(sellingPriceResponse);


        Mockito.when(productAdvisementResponse.getAdvisementDescription()).thenReturn("AdvisementDescription");
        Mockito.when(productAdvisementResponse.getAdvisementId()).thenReturn("AdvisementId");
        Mockito.when(productAdvisementResponse.getAdvisementName()).thenReturn("AdvisementName");
        Mockito.when(productAdvisementResponse.getAdvisementTitle()).thenReturn("AdvisementTitle");
        Mockito.when(productAdvisementResponse.getAdvisementType()).thenReturn("AdvisementType");
        Mockito.when(productAdvisementResponse.getAdvisementMedia()).thenReturn(mediaResponse);
        Mockito.when(productResponse1.getAdvisements()).thenReturn(Arrays.asList(productAdvisementResponse));

        Mockito.when(productResponse2.getAdvisements()).thenReturn(Arrays.asList(productAdvisementResponse));


        Mockito.when(productPreferenceValueResponse.getPreferenceValueId()).thenReturn("PreferenceValueId");
        Mockito.when(productPreferenceValueResponse.isPreferenceValueCode()).thenReturn(true);
        Mockito.when(productPreferenceValueResponse.isPreferenceValueName()).thenReturn(false);
        Mockito.when(productPreferenceResponse.getPreferenceId()).thenReturn("PreferenceId");
        Mockito.when(productPreferenceResponse.getPreferenceName()).thenReturn("PreferenceName");
        Mockito.when(productPreferenceResponse.getPreferenceType()).thenReturn("PreferenceType");
        Mockito.when(productPreferenceResponse.getPreferenceValue()).thenReturn(productPreferenceValueResponse);
        Mockito.when(productResponse1.getPreferences()).thenReturn(Arrays.asList(productPreferenceResponse));

        Mockito.when(productResponse2.getPreferences()).thenReturn(Arrays.asList(productPreferenceResponse));


        Mockito.when(productRestrictionAnswerResponse.getRestrictionAnswerDisplayText()).thenReturn("RestrictionAnswerDisplayText");
        Mockito.when(productRestrictionResponse.getRestrictionAnswers()).thenReturn(Arrays.asList(productRestrictionAnswerResponse));
        Mockito.when(productRestrictionResponse.getRestrictionMedia()).thenReturn(mediaResponse);
        Mockito.when(productRestrictionResponse.getRestrictionDescription()).thenReturn("RestrictionDescription");
        Mockito.when(productRestrictionResponse.getRestrictionDisplayText()).thenReturn("RestrictionDisplayText");
        Mockito.when(productRestrictionResponse.getRestrictionId()).thenReturn("RestrictionId");
        Mockito.when(productRestrictionResponse.getRestrictionQuestion()).thenReturn("RestrictionQuestion");
        Mockito.when(productRestrictionResponse.getRestrictionTitle()).thenReturn("RestrictionTitle");
        Mockito.when(productRestrictionResponse.getRestrictionType()).thenReturn("RestrictionType");
        Mockito.when(productResponse1.getRestrictions()).thenReturn(Arrays.asList(productRestrictionResponse));

        Mockito.when(productResponse2.getRestrictions()).thenReturn(Arrays.asList(productRestrictionResponse));


        Mockito.when(productResponse1.getProductTitle()).thenReturn("ProductTitle");

        Mockito.when(productResponse2.getProductTitle()).thenReturn("ProductTitle");


        Mockito.when(productResponse1.getProductShortDescription()).thenReturn("ProductShortDescription");

        Mockito.when(productResponse2.getProductShortDescription()).thenReturn("ProductShortDescription");


        Mockito.when(productResponse1.getProductLongDescription()).thenReturn("ProductLongDescription");

        Mockito.when(productResponse2.getProductLongDescription()).thenReturn("ProductLongDescription");


        Mockito.when(productResponse1.getProductMedia()).thenReturn(mediaResponse);

        Mockito.when(productResponse2.getProductMedia()).thenReturn(mediaResponse);

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
            assertEquals(productResponse1.getPreferences().get(i).getPreferenceValue().getPreferenceValueId(), product.getPreferences().get(i).getPreferenceValue().getPreferenceValueId());
            assertEquals(productResponse1.getPreferences().get(i).getPreferenceValue().isPreferenceValueCode(), product.getPreferences().get(i).getPreferenceValue().isPreferenceValueCode());
            assertEquals(productResponse1.getPreferences().get(i).getPreferenceValue().isPreferenceValueName(), product.getPreferences().get(i).getPreferenceValue().isPreferenceValueName());
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
                assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceValue().getPreferenceValueId(), productList.get(z).getPreferences().get(i).getPreferenceValue().getPreferenceValueId());
                assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceValue().isPreferenceValueCode(), productList.get(z).getPreferences().get(i).getPreferenceValue().isPreferenceValueCode());
                assertEquals(productResponses.get(z).getPreferences().get(i).getPreferenceValue().isPreferenceValueName(), productList.get(z).getPreferences().get(i).getPreferenceValue().isPreferenceValueName());
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