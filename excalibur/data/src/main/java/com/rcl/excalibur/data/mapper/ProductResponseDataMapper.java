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
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductActivityLevel;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductCategory;
import com.rcl.excalibur.domain.ProductCostType;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.ProductPreference;
import com.rcl.excalibur.domain.ProductPreferenceValue;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.domain.ProductRestrictionAnswer;
import com.rcl.excalibur.domain.ProductTags;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.SellingPrice;

import java.util.ArrayList;
import java.util.List;

//FIXME this class is violating SRP (Single Responsibility Principle) take out all other transforms add them through composition
public class ProductResponseDataMapper extends BaseDataMapper<Product, ProductResponse> {

    @Override
    public Product transform(ProductResponse productResponse, Object... additionalArgs) {
        Product product = null;
        if (productResponse != null) {
            product = new Product();
            product.setProductId(productResponse.getProductId());
            product.setProductCode(productResponse.getProductCode());
            product.setProductType(transform(productResponse.getProductType()));
            product.setProductClass(productResponse.getProductClass());
            product.setProductCategory(transform(productResponse.getProductCategory()));
            product.setProductRank(productResponse.getProductRank());
            product.setReservationRequired(productResponse.isReservationRequired());
            product.setScheduable(productResponse.isScheduable());
            product.setActivityLevel(transform(productResponse.getActivityLevel()));
            product.setProductLocation(transform(productResponse.getProductLocation()));
            product.setProductDuration(transform(productResponse.getProductDuration()));
            product.setCostType(transform(productResponse.getCostType()));
            product.setStartingFromPrice(transform(productResponse.getStartingFromPrice()));
            product.setAdvisements(transformProductAdvisement(productResponse.getAdvisements()));
            product.setPreferences(transformProductPreference(productResponse.getPreferences()));
            product.setRestrictions(transformProductRestriction(productResponse.getRestrictions()));
            product.setProductTitle(productResponse.getProductTitle());
            product.setProductShortDescription(productResponse.getProductShortDescription());
            product.setProductLongDescription(productResponse.getProductLongDescription());
            product.setProductMedia(transform(productResponse.getProductMedia()));
            product.setExperience(productResponse.getExperience());
            product.setProductReservationInformation(productResponse.getProductReservationInformation());
            product.setProductUpcharge(productResponse.getUpcharge());
            product.setFeatured(productResponse.isFeatured());
            product.setHighlighted(productResponse.isHighlighted());
        }
        return product;
    }


    private List<ProductRestriction> transformProductRestriction(List<ProductRestrictionResponse> productRestrictionResponses) {

        ArrayList<ProductRestriction> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(productRestrictionResponses)) {
            return items;
        }
        for (ProductRestrictionResponse productRestrictionResponse : productRestrictionResponses) {

            if (productRestrictionResponse == null) {
                continue;
            }

            ProductRestriction productRestriction = new ProductRestriction();
            productRestriction.setRestrictionId(productRestrictionResponse.getRestrictionId());
            productRestriction.setRestrictionType(productRestrictionResponse.getRestrictionType());
            productRestriction.setMandatory(productRestrictionResponse.isMandatory());
            productRestriction.setRestrictionDisplayText(productRestrictionResponse.getRestrictionDisplayText());
            productRestriction.setRestrictionTitle(productRestrictionResponse.getRestrictionTitle());
            productRestriction.setRestrictionDescription(productRestrictionResponse.getRestrictionDescription());
            productRestriction.setRestrictionQuestion(productRestrictionResponse.getRestrictionQuestion());
            productRestriction.setRestrictionAnswers(transformProductRestrictionAnswer(productRestrictionResponse.getRestrictionAnswers()));
            productRestriction.setRestrictionMedia(transform(productRestrictionResponse.getRestrictionMedia()));
            items.add(productRestriction);
        }

        return items;
    }

    private List<ProductRestrictionAnswer> transformProductRestrictionAnswer(List<ProductRestrictionAnswerResponse>
                                                                                     transformProductRestrictionAnswersResponses) {

        ArrayList<ProductRestrictionAnswer> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(transformProductRestrictionAnswersResponses)) {
            return items;
        }
        for (ProductRestrictionAnswerResponse transformProductRestrictionAnswerResponse : transformProductRestrictionAnswersResponses) {

            if (transformProductRestrictionAnswerResponse == null) {
                continue;
            }
            ProductRestrictionAnswer productRestrictionAnswer = new ProductRestrictionAnswer();
            productRestrictionAnswer.setRestrictionAnswerDisplayText(transformProductRestrictionAnswerResponse
                    .getRestrictionAnswerDisplayText());
            items.add(productRestrictionAnswer);
        }

        return items;
    }

    private List<ProductPreference> transformProductPreference(List<ProductPreferenceResponse> productPreferenceResponses) {

        ArrayList<ProductPreference> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(productPreferenceResponses)) {
            return items;
        }
        for (ProductPreferenceResponse productPreferenceResponse : productPreferenceResponses) {

            if (productPreferenceResponse == null) {
                continue;
            }
            ProductPreference productPreference = new ProductPreference();
            productPreference.setMandatoryPreferenceFlag(productPreferenceResponse.isMandatoryPreferenceFlag());
            productPreference.setPreferenceId(productPreferenceResponse.getPreferenceId());
            productPreference.setPreferenceName(productPreferenceResponse.getPreferenceName());
            productPreference.setPreferenceType(productPreferenceResponse.getPreferenceType());
            productPreference.setPreferenceValue(transformPreferencesValues(productPreferenceResponse.getPreferenceValue()));
            items.add(productPreference);
        }

        return items;
    }

    private List<ProductPreferenceValue> transformPreferencesValues(List<ProductPreferenceValueResponse> productPreferenceValueResponses) {
        final List<ProductPreferenceValue> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(productPreferenceValueResponses)) {
            return items;
        }
        for (ProductPreferenceValueResponse productPreferenceValueResponse : productPreferenceValueResponses) {
            if (productPreferenceValueResponse == null) {
                continue;
            }

            ProductPreferenceValue productPreferenceValue = new ProductPreferenceValue();
            productPreferenceValue.setPreferenceValueCode(productPreferenceValueResponse.getPreferenceValueCode());
            productPreferenceValue.setPreferenceValueId(productPreferenceValueResponse.getPreferenceValueId());
            productPreferenceValue.setPreferenceValueName(productPreferenceValueResponse.getPreferenceValueName());
            items.add(productPreferenceValue);
        }
        return items;
    }

    private List<ProductAdvisement> transformProductAdvisement(List<ProductAdvisementResponse> productAdvisementResponses) {

        ArrayList<ProductAdvisement> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(productAdvisementResponses)) {
            return items;
        }
        for (ProductAdvisementResponse productAdvisementResponse : productAdvisementResponses) {

            if (productAdvisementResponse == null) {
                continue;
            }
            ProductAdvisement productAdvisement = new ProductAdvisement();
            productAdvisement.setAdvisementDescription(productAdvisementResponse.getAdvisementDescription());
            productAdvisement.setAdvisementId(productAdvisementResponse.getAdvisementId());
            productAdvisement.setAdvisementName(productAdvisementResponse.getAdvisementName());
            productAdvisement.setAdvisementTitle(productAdvisementResponse.getAdvisementTitle());
            productAdvisement.setAdvisementMedia(transform(productAdvisementResponse.getAdvisementMedia()));
            productAdvisement.setAdvisementType(productAdvisementResponse.getAdvisementType());
            productAdvisement.setAdvisementDescription(productAdvisementResponse.getAdvisementDescription());
            items.add(productAdvisement);
        }

        return items;
    }

    private SellingPrice transform(SellingPriceResponse sellingPriceResponse) {
        SellingPrice sellingPrice = null;
        if (sellingPriceResponse != null) {
            sellingPrice = new SellingPrice();
            sellingPrice.setAdultPrice(sellingPriceResponse.getAdultPrice());
            sellingPrice.setChildPrice(sellingPriceResponse.getChildPrice());
            sellingPrice.setCurrency(sellingPriceResponse.getCurrency());
            sellingPrice.setInfantPrice(sellingPriceResponse.getInfantPrice());
        }
        return sellingPrice;
    }

    private ProductCostType transform(ProductCostTypeResponse productCostTypeResponse) {
        ProductCostType productCostType = null;
        if (productCostTypeResponse != null) {
            productCostType = new ProductCostType();
            productCostType.setCostTypeCode(productCostTypeResponse.getCostTypeCode());
            productCostType.setCostTypeDescription(productCostTypeResponse.getCostTypeDescription());
            productCostType.setCostTypeMedia(transform(productCostTypeResponse.getCostTypeMedia()));
            productCostType.setCostTypeTitle(productCostTypeResponse.getCostTypeTitle());
        }
        return productCostType;
    }

    private ProductDuration transform(ProductDurationResponse productDurationResponse) {
        ProductDuration productDuration = null;
        if (productDurationResponse != null) {
            productDuration = new ProductDuration();
            productDuration.setAtYourLeisure(productDurationResponse.isAtYourLeisure());
            productDuration.setDurationInMinutes(productDurationResponse.getDurationInMinutes());
            productDuration.setLagTimeInMinutes(productDurationResponse.getLagTimeInMinutes());
            productDuration.setLeadTimeInMinutes(productDurationResponse.getLeadTimeInMinutes());
        }
        return productDuration;
    }

    private ProductLocation transform(ProductLocationResponse productLocationResponse) {
        ProductLocation productLocation = null;
        if (productLocationResponse != null) {
            productLocation = new ProductLocation();
            productLocation.setLocationCode(productLocationResponse.getLocationCode());
            productLocation.setLocationId(productLocationResponse.getLocationId());
            productLocation.setLocationType(productLocationResponse.getLocationType());
            productLocation.setOperatingHoursEnd(productLocationResponse.getOperatingHoursEnd());
            productLocation.setOperatingHoursStart(productLocationResponse.getOperatingHoursStart());
            productLocation.setLocationVenue(productLocationResponse.getLocationVenue());
            productLocation.setLocationDeckNumber(productLocationResponse.getLocationDeckNumber());
            productLocation.setLocationPort(productLocationResponse.getLocationPort());
            productLocation.setLocationDirection(productLocationResponse.getLocationDirection());
        }
        return productLocation;
    }

    private ProductActivityLevel transform(ProductActivityLevelResponse productActivityLevelResponse) {
        ProductActivityLevel productActivityLevel = null;
        if (productActivityLevelResponse != null) {
            productActivityLevel = new ProductActivityLevel();
            productActivityLevel.setActivityLevelDescription(productActivityLevelResponse.getActivityLevelDescription());
            productActivityLevel.setActivityLevelId(productActivityLevelResponse.getActivityLevelId());
            productActivityLevel.setActivityLevelTitle(productActivityLevelResponse.getActivityLevelTitle());
            productActivityLevel.setActivityLevelMedia(transform(productActivityLevelResponse.getActivityLevelMedia()));
        }
        return productActivityLevel;
    }

    private Media transform(MediaResponse mediaResponse) {
        Media media = null;
        if (mediaResponse != null) {
            media = new Media();
            media.setMediaItem(transformMediaItem(mediaResponse.getMediaItem()));
        }
        return media;
    }

    private List<MediaItem> transformMediaItem(List<MediaItemResponse> mediaItemsResponses) {
        ArrayList<MediaItem> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(mediaItemsResponses)) {
            return items;
        }
        for (MediaItemResponse mediaItemResponse : mediaItemsResponses) {

            if (mediaItemResponse == null) {
                continue;
            }
            MediaItem mediaItem = new MediaItem();
            mediaItem.setMediaRefLink(mediaItemResponse.getMediaRefLink());
            mediaItem.setMediaType(mediaItemResponse.getMediaType());
            items.add(mediaItem);

        }

        return items;
    }

    private ProductType transform(ProductTypeResponse productTypeResponse) {
        ProductType productType = null;
        if (productTypeResponse != null) {
            productType = new ProductType();
            productType.setProductTypeId(productTypeResponse.getProductTypeId());
            productType.setProductTypeName(productTypeResponse.getProductTypeName());
            productType.setProductType(productTypeResponse.getProductType());
        }
        return productType;
    }

    private List<ProductCategory> transform(List<ProductCategoryResponse> productCategoryResponses) {

        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        if (CollectionUtils.isEmpty(productCategoryResponses)) {
            return productCategories;
        }
        for (ProductCategoryResponse productCategoryResponse : productCategoryResponses) {

            if (productCategoryResponse == null) {
                continue;
            }
            ProductCategory productCategory = new ProductCategory();
            productCategory.setCategoryDescription(productCategoryResponse.getCategoryDescription());
            productCategory.setCategoryId(productCategoryResponse.getCategoryId());
            productCategory.setProductTags(transformProductTags(productCategoryResponse.getProductTags()));
            productCategories.add(productCategory);
        }

        return productCategories;
    }

    private List<ProductTags> transformProductTags(List<ProductTagsResponse> productTagsResponses) {

        ArrayList<ProductTags> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(productTagsResponses)) {
            return items;
        }

        for (ProductTagsResponse productTagsResponse : productTagsResponses) {

            if (productTagsResponse == null) {
                continue;
            }
            ProductTags productTags = new ProductTags();
            productTags.setDescription(productTagsResponse.getDescription());
            productTags.setTagId(productTagsResponse.getTagId());
            items.add(productTags);
        }
        return items;
    }
}
