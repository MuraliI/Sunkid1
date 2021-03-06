package com.rcl.excalibur.data.mapper;

import com.rcl.excalibur.data.service.response.ChildCategoryResponse;
import com.rcl.excalibur.data.service.response.DeckInfoResponse;
import com.rcl.excalibur.data.service.response.MediaItemResponse;
import com.rcl.excalibur.data.service.response.MediaResponse;
import com.rcl.excalibur.data.service.response.OperationHourResponse;
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
import com.rcl.excalibur.data.service.response.ProductTypeResponse;
import com.rcl.excalibur.data.service.response.SellingPriceResponse;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.LocationDeckInfo;
import com.rcl.excalibur.domain.LocationOperationHour;
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
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.SellingPrice;

import java.util.ArrayList;
import java.util.List;

//FIXME this class is violating SRP (Single Responsibility Principle) take out all other transforms add them through composition
public class ProductResponseDataMapper extends BaseDataMapper<Product, ProductResponse, Void> {

    protected OfferingResponseMapper offeringResponseMapper;

    public ProductResponseDataMapper(OfferingResponseMapper offeringResponseMapper) {
        this.offeringResponseMapper = offeringResponseMapper;
    }

    @Override
    public Product transform(ProductResponse productResponse, Void additionalArg) {
        Product product = null;
        if (productResponse != null) {

            // TODO: To be removed once the service provides this details
            productResponse.setUpcharge(2);
            if (productResponse.getProductReservationInformation() == null) {
                productResponse.setProductReservationInformation("Please Arrive 15 minutes early, Wear closedtoed shoes");
            }
            if (productResponse.getExperience() == null) {
                productResponse.setExperience("Enjoy the travel!");
            }
            List<ProductAdvisementResponse> productAdvisementResponseList = productResponse.getAdvisements();
            if (productAdvisementResponseList == null || productAdvisementResponseList.isEmpty()) {
                productResponse.setAdvisements(getProductAdvisementResponseAttire());
            }
            product = new Product();
            product.setProductId(productResponse.getProductId());
            product.setProductCode(productResponse.getProductCode());
            product.setProductType(transform(productResponse.getProductType()));
            product.setProductClass(productResponse.getProductClass());
            product.setProductCategory(transform(productResponse.getProductCategory()));
            product.setProductRank(productResponse.getProductRank());
            product.setReservationRequired(productResponse.isReservationRequired());
            product.setScheduable(productResponse.isScheduable());
            product.setProductLocation(transform(productResponse.getProductLocation()));
            product.setProductDuration(transform(productResponse.getProductDuration()));
            product.setCostType(transform(productResponse.getCostType()));
            product.setStartingFromPrice(transform(productResponse.getStartingFromPrice()));
            product.setAdvisements(transformProductAdvisement(productResponse.getAdvisements()));
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
            //Offerings
            product.setOfferings(offeringResponseMapper.transform(productResponse.getOffering(), product));


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
            productLocation.setLocationTitle(productLocationResponse.getLocationTitle());
            productLocation.setLocationType(productLocationResponse.getLocationType());
            productLocation.setLatitude(productLocationResponse.getLatitude());
            productLocation.setLongitude(productLocationResponse.getLongitude());
            productLocation.setDeckInfo(transformDeckInfo(productLocationResponse.getDeckInfo()));
            productLocation.setLocationOperationHours(transformOperationHours(productLocationResponse.getLocationOperatingHours()));
        }
        return productLocation;
    }

    private List<LocationDeckInfo> transformDeckInfo(List<DeckInfoResponse> deckInfoResponseList) {
        List<LocationDeckInfo> locationDeckInfoList = new ArrayList<>();
        if (deckInfoResponseList != null && !deckInfoResponseList.isEmpty()) {
            for (DeckInfoResponse deckInfoResponse : deckInfoResponseList) {
                locationDeckInfoList.add(transform(deckInfoResponse));
            }
        }
        return locationDeckInfoList;
    }

    private LocationDeckInfo transform(DeckInfoResponse deckInfoResponse) {
        LocationDeckInfo locationDeckInfo = null;
        if (deckInfoResponse != null) {
            locationDeckInfo = new LocationDeckInfo();
            locationDeckInfo.setDeckNumber(deckInfoResponse.getDeckNumber());
            locationDeckInfo.setDirection(deckInfoResponse.getDirection());
        }
        return locationDeckInfo;
    }

    private List<LocationOperationHour> transformOperationHours(List<OperationHourResponse> operationHourResponseList) {
        List<LocationOperationHour> locationOperationHourList = new ArrayList<>();
        if (operationHourResponseList != null && !operationHourResponseList.isEmpty()) {
            for (OperationHourResponse operationHourResponse : operationHourResponseList) {
                locationOperationHourList.add(transform(operationHourResponse));
            }
        }
        return locationOperationHourList;
    }

    private LocationOperationHour transform(OperationHourResponse operationHourResponse) {
        LocationOperationHour operationHour = null;
        if (operationHourResponse != null) {
            operationHour = new LocationOperationHour();
            operationHour.setDayNumber(operationHourResponse.getDayNumber());
            operationHour.setTimeOfDay(operationHourResponse.getTimeOfDay());
            operationHour.setStartTime(operationHourResponse.getStartTime());
            operationHour.setEndTime(operationHourResponse.getEndTime());
        }
        return operationHour;
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

    private ProductCategory transform(List<ProductCategoryResponse> productCategoryResponses) {

        if (CollectionUtils.isEmpty(productCategoryResponses)) {
            return new ProductCategory();
        }

        ProductCategoryResponse productCategoryResponse = productCategoryResponses.get(0);

        if (productCategoryResponse == null) {
            return new ProductCategory();
        }
        if (productCategoryResponse == null) {
            //FIXME. Arrive as null from service. Is it possible?
            return null;
        }
        ProductCategory productCategory = new ProductCategory();
        if (productCategoryResponse != null) {
            productCategory.setCategoryDescription(productCategoryResponse.getCategoryDescription());
            productCategory.setCategoryId(productCategoryResponse.getCategoryId());
            productCategory.setCategoryName(productCategoryResponse.getCategoryName());
            productCategory.setChildCategory(transformChildCategories(productCategoryResponse.getChildCategory()));
        }
        return productCategory;
    }

    private List<ChildCategory> transformChildCategories(List<ChildCategoryResponse> childCategoryResponses) {

        ArrayList<ChildCategory> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(childCategoryResponses)) {
            return items;
        }
        for (ChildCategoryResponse childCategoryResponse : childCategoryResponses) {

            if (childCategoryResponse == null) {
                continue;
            }

            ChildCategory childCategory = new ChildCategory();
            childCategory.getItems().setCategoryId(childCategoryResponse.getItems().getCategoryId());
            childCategory.getItems().setCategoryName(childCategoryResponse.getItems().getCategoryName());
            childCategory.getItems().setCategoryDescription(childCategoryResponse.getItems().getCategoryDescription());

            items.add(childCategory);
        }

        return items;
    }

    // TODO: Hardcoded method to be removed once the service provides this details
    private List<ProductAdvisementResponse> getProductAdvisementResponseAttire() {
        List<ProductAdvisementResponse> productAdvisementResponses = new ArrayList<>();

        MediaItemResponse mediaItemResponse = new MediaItemResponse();
        mediaItemResponse.setMediaType("icon");
        mediaItemResponse.setMediaRefLink("/royal/shared_assets/icons/advisement_meals_r.png");

        MediaResponse mediaResponse = new MediaResponse();
        List<MediaItemResponse> mediaItemResponseList = new ArrayList<>();
        mediaItemResponseList.add(mediaItemResponse);
        mediaResponse.setMediaItem(mediaItemResponseList);

        ProductAdvisementResponse advisementAttire = new ProductAdvisementResponse();
        advisementAttire.setAdvisementId(ProductAdvisement.ATTIRE);
        advisementAttire.setAdvisementDescription("Casual");
        advisementAttire.setAdvisementTitle("Attire");
        advisementAttire.setAdvisementName("Attire");
        advisementAttire.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAttire);

        ProductAdvisementResponse advisementKnowBeforeYouGo = new ProductAdvisementResponse();
        advisementKnowBeforeYouGo.setAdvisementId(ProductAdvisement.KNOW_BEFORE_YOU_GO);
        advisementKnowBeforeYouGo.setAdvisementDescription("Arrive 15 minutes early, Wear closedtoed shoes");
        advisementKnowBeforeYouGo.setAdvisementTitle("Know Before You Go");
        advisementKnowBeforeYouGo.setAdvisementName("Know Before You Go");
        advisementKnowBeforeYouGo.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementKnowBeforeYouGo);

        ProductAdvisementResponse advisementAccessibility = new ProductAdvisementResponse();
        advisementAccessibility.setAdvisementId(ProductAdvisement.ACCESSIBILITY);
        advisementAccessibility.setAdvisementDescription("");
        advisementAccessibility.setAdvisementTitle("Wheelchair Accessible");
        advisementAccessibility.setAdvisementName("Wheelchair Accessible");
        advisementAccessibility.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAccessibility);
        ProductAdvisementResponse advisementAccessibility2 = new ProductAdvisementResponse();
        advisementAccessibility2.setAdvisementId(ProductAdvisement.ACCESSIBILITY);
        advisementAccessibility2.setAdvisementDescription("This description is short enough to whet one's apetite "
                + "but long enough to be meaningful.");
        advisementAccessibility2.setAdvisementTitle("Closed Caption");
        advisementAccessibility2.setAdvisementName("Closed Caption");
        advisementAccessibility2.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementAccessibility2);


        ProductAdvisementResponse advisementLegal = new ProductAdvisementResponse();
        advisementLegal.setAdvisementId(ProductAdvisement.LEGAL);
        advisementLegal.setAdvisementDescription("This legal information is short enough to comfort you but long enough  to be meaninful.");
        advisementLegal.setAdvisementTitle("Legal");
        advisementLegal.setAdvisementName("Legal");
        advisementLegal.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementLegal);

        ProductAdvisementResponse advisementCuisine = new ProductAdvisementResponse();
        advisementCuisine.setAdvisementId(ProductAdvisement.CUISINE);
        advisementCuisine.setAdvisementDescription("Latin American");
        advisementCuisine.setAdvisementTitle("Cuisine");
        advisementCuisine.setAdvisementName("Cuisine");
        advisementCuisine.setAdvisementMedia(mediaResponse);

        productAdvisementResponses.add(advisementCuisine);

        return productAdvisementResponses;
    }

}
