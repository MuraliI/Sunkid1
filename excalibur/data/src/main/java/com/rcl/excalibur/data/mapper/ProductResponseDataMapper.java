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
import com.rcl.excalibur.domain.Category;
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

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductResponseDataMapper extends BaseDataMapper<Product, ProductResponse> {

    @Inject
    ProductResponseDataMapper() {
    }

    @Override
    public Product transform(ProductResponse entity) {
        Product product = null;
        if (entity != null) {
            product = new Product();
            product.setProductId(entity.getProductId());
            product.setProductCode(entity.getProductCode());
            product.setProductType(transform(entity.getProductType()));
            product.setProductClass(entity.getProductClass());
            product.setProductCategory(transform(entity.getProductCategory()));
            product.setProductRank(entity.getProductRank());
            product.setReservationRequired(entity.isReservationRequired());
            product.setScheduable(entity.isScheduable());
            product.setActivityLevel( transform(entity.getActivityLevel()));
            product.setProductLocation(transform(entity.getProductLocation()));
            product.setProductDuration(transform(entity.getProductDuration()));
            product.setCostType(transform(entity.getCostType()));
            product.setStartingFromPrice(transform(entity.getStartingFromPrice()));
            product.setAdvisements(transformProductAdvisement(entity.getAdvisements()));
            product.setPreferences(transformProductPreference(entity.getPreferences()));
            product.setRestrictions(transformProductRestriction(entity.getRestrictions()));
            product.setProductTitle(entity.getProductTitle());
            product.setProductShortDescription(entity.getProductShortDescription());
            product.setProductLongDescription(entity.getProductLongDescription());
            product.setProductMedia(transform(entity.getProductMedia()));

        }
        return product;
    }


    private List<ProductRestriction> transformProductRestriction(List<ProductRestrictionResponse> entities) {

        ArrayList<ProductRestriction> items = new ArrayList<ProductRestriction>();

        for (ProductRestrictionResponse entity : entities) {
            ProductRestriction productRestriction= null;
            if (entity != null) {
                productRestriction = new ProductRestriction();
                productRestriction.setRestrictionId(entity.getRestrictionId());
                productRestriction.setRestrictionType(entity.getRestrictionType());
                productRestriction.setMandatory(entity.isMandatory());
                productRestriction.setRestrictionDisplayText(entity.getRestrictionDisplayText());
                productRestriction.setRestrictionTitle(entity.getRestrictionTitle());
                productRestriction.setRestrictionDescription(entity.getRestrictionDescription());
                productRestriction.setRestrictionQuestion(entity.getRestrictionQuestion());
                productRestriction.setRestrictionAnswers(transformProductRestrictionAnswer(entity.getRestrictionAnswers()));
                productRestriction.setRestrictionMedia(transform(entity.getRestrictionMedia()));
                items.add(productRestriction);
            }
        }
        return items;
    }

    private List<ProductRestrictionAnswer> transformProductRestrictionAnswer(List<ProductRestrictionAnswerResponse> entities) {

        ArrayList<ProductRestrictionAnswer> items = new ArrayList<ProductRestrictionAnswer>();

        for (ProductRestrictionAnswerResponse entity: entities) {

            ProductRestrictionAnswer productRestrictionAnswer= null;
            if (entity != null) {
                productRestrictionAnswer = new ProductRestrictionAnswer();
                productRestrictionAnswer.setRestrictionAnswerDisplayText(entity.getRestrictionAnswerDisplayText());
                items.add(productRestrictionAnswer);
            }
        }
        return items;
    }

    private List<ProductPreference> transformProductPreference(List<ProductPreferenceResponse> entities) {

        ArrayList<ProductPreference> items = new ArrayList<ProductPreference>();
        for (ProductPreferenceResponse entity : entities) {

            ProductPreference productPreference= null;
            if (entity != null) {
                productPreference = new ProductPreference();
                productPreference.setMandatoryPreferenceFlag(entity.isMandatoryPreferenceFlag());
                productPreference.setPreferenceID(entity.getPreferenceID());
                productPreference.setPreferenceName(entity.getPreferenceName());
                productPreference.setPreferenceType(entity.getPreferenceType());
                productPreference.setPreferenceValue(transform(entity.getPreferenceValue()));
                items.add(productPreference);
            }
        }
        return items;
    }

    private ProductPreferenceValue transform(ProductPreferenceValueResponse entity) {
        ProductPreferenceValue productPreferenceValue= null;
        if (entity != null) {
            productPreferenceValue = new ProductPreferenceValue();
            productPreferenceValue.setPreferenceValueCode(entity.isPreferenceValueCode());
            productPreferenceValue.setPreferenceValueID(entity.getPreferenceValueID());
            productPreferenceValue.setPreferenceValueName(entity.isPreferenceValueName());
        }
        return productPreferenceValue;
    }

    private List<ProductAdvisement> transformProductAdvisement(List<ProductAdvisementResponse> entities) {

        ArrayList<ProductAdvisement> items = new ArrayList<ProductAdvisement>();

        for (ProductAdvisementResponse entity : entities) {

            ProductAdvisement productAdvisement= null;
            if (entity != null) {
                productAdvisement = new ProductAdvisement();
                productAdvisement.setAdvisementDescription(entity.getAdvisementDescription());
                productAdvisement.setAdvisementID(entity.getAdvisementID());
                productAdvisement.setAdvisementName(entity.getAdvisementName());
                productAdvisement.setAdvisementTitle(entity.getAdvisementTitle());
                productAdvisement.setAdvisementMedia(transform(entity.getAdvisementMedia()));
                productAdvisement.setAdvisementType(entity.getAdvisementType());
                productAdvisement.setAdvisementDescription(entity.getAdvisementDescription());
                items.add(productAdvisement);
            }
        }


        return items;
    }

    private SellingPrice transform(SellingPriceResponse entity) {
        SellingPrice sellingPrice = null;
        if (entity != null) {
            sellingPrice = new SellingPrice();
            sellingPrice.setAdultPrice(entity.getAdultPrice());
            sellingPrice.setChildPrice(entity.getChildPrice());
            sellingPrice.setCurrency(entity.getCurrency());
            sellingPrice.setInfantPrice(entity.getInfantPrice());
        }
        return sellingPrice;
    }

    private ProductCostType transform(ProductCostTypeResponse entity) {
        ProductCostType productCostType= null;
        if (entity != null) {
            productCostType = new ProductCostType();
            productCostType.setCostTypeCode(entity.getCostTypeCode());
            productCostType.setCostTypeDescription(entity.getCostTypeDescription());
            productCostType.setCostTypeMedia(transform(entity.getCostTypeMedia()));
            productCostType.setCostTypeTitle(entity.getCostTypeTitle());
        }
        return productCostType;
    }

    private ProductDuration transform(ProductDurationResponse entity) {
        ProductDuration productDuration= null;
        if (entity != null) {
            productDuration = new ProductDuration();
            productDuration.setAtYourLeisure(entity.isAtYourLeisure());
            productDuration.setDurationInMinutes(entity.getDurationInMinutes());
            productDuration.setLagTimeInMinutes(entity.getLagTimeInMinutes());
            productDuration.setLeadTimeInMinutes(entity.getLeadTimeInMinutes());
        }
        return productDuration;
    }

    private ProductLocation transform(ProductLocationResponse entity) {
        ProductLocation productLocation = null;
        if (entity != null) {
            productLocation = new ProductLocation();
            productLocation.setLocationCode(entity.getLocationCode());
            productLocation.setLocationId(entity.getLocationId());
            productLocation.setLocationType(entity.getLocationType());
            productLocation.setOperatingHoursEnd(entity.getOperatingHoursEnd());
            productLocation.setOperatingHoursStart(entity.getOperatingHoursStart());
        }
        return productLocation;
    }

    private ProductActivityLevel transform(ProductActivityLevelResponse entity) {
        ProductActivityLevel productActivityLevel = null;
        if (entity != null) {
            productActivityLevel = new ProductActivityLevel();
            productActivityLevel.setActivityLevelDescription(entity.getActivityLevelDescription());
            productActivityLevel.setActivityLevelID(entity.getActivityLevelID());
            productActivityLevel.setActivityLevelMedia(transform(entity.getActivityLevelMedia()));
        }
        return productActivityLevel;
    }

    private Media transform(MediaResponse entity) {
        Media media = null;
        if (entity != null) {
            media = new Media();
            media.setMediaItem(transformMediaItem(entity.getMediaItem()));
        }
        return media;
    }

    private List<MediaItem> transformMediaItem(List<MediaItemResponse> entities) {
        ArrayList<MediaItem> items = new ArrayList<MediaItem>();

        for (MediaItemResponse entity: entities) {

            MediaItem mediaItem = null;
            if (entity != null) {
                mediaItem = new MediaItem();
                mediaItem.setMediaRefLink(entity.getMediaRefLink());
                mediaItem.setMediaType(entity.getMediaType());
                items.add(mediaItem);
            }
        }
        return items;
    }

    private ProductType transform(ProductTypeResponse entity) {
        ProductType productType = null;
        if (entity != null) {
            productType = new ProductType();
            productType.setProductTypeID(entity.getProductTypeID());
            productType.setProductTypeName(entity.getProductTypeName());
            productType.setProductType(entity.getProductType());
        }
        return productType;
    }

    private List<ProductCategory> transform(List<ProductCategoryResponse> entities) {

        ArrayList<ProductCategory> productCategories=new ArrayList<ProductCategory>();

        for(ProductCategoryResponse entity : entities) {
            ProductCategory productCategory = null;
            if (entity != null) {
                productCategory = new ProductCategory();
                productCategory.setCategoryDescription(entity.getCategoryDescription());
                productCategory.setCategoryid(entity.getCategoryid());
                productCategory.setProductTags(transformProductTags(entity.getProductTags()));
                productCategories.add(productCategory);
            }
        }

        return productCategories;
    }

    public List<ProductTags> transformProductTags(List<ProductTagsResponse> entities) {

        ArrayList<ProductTags> items = new ArrayList<ProductTags>();

        for (ProductTagsResponse entity : entities) {

            ProductTags productTags = null;
            if (entity != null) {
                productTags = new ProductTags();
                productTags.setDescription(entity.getDescription());
                productTags.setTagID(entity.getTagID());
                items.add(productTags);
            }
        }
        return items;
    }
}
