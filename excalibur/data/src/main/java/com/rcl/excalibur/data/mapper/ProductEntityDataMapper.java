package com.rcl.excalibur.data.mapper;


import com.rcl.excalibur.data.entity.ActivityLevelEntity;
import com.rcl.excalibur.data.entity.AdvisementEntity;
import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.CostTypeEntity;
import com.rcl.excalibur.data.entity.DurationEntity;
import com.rcl.excalibur.data.entity.LocationEntity;
import com.rcl.excalibur.data.entity.MediaEntity;
import com.rcl.excalibur.data.entity.MediaValueEntity;
import com.rcl.excalibur.data.entity.PreferenceEntity;
import com.rcl.excalibur.data.entity.PreferenceValueEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.entity.RestrictionEntity;
import com.rcl.excalibur.data.entity.StartingFromPriceEntity;
import com.rcl.excalibur.data.entity.TypeEntity;
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

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link ProductEntity} (in the data layer) to {@link Product} in the
 * domain layer.
 */
@Singleton
public class ProductEntityDataMapper extends BaseDataMapper<Product, ProductEntity> {

    @Inject
    ProductEntityDataMapper() {
    }

    @Override
    public Product transform(final ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        final Product product = new Product();

        product.setProductId(entity.getProductId());
        product.setProductCode(entity.getCode());
        product.setProductType(transform(entity.getType()));
        product.setProductClass(entity.getProductClass());
        product.setProductCategory(transform(entity.getCategory()));
        product.setProductRank(entity.getRank());
        product.setReservationRequired(entity.isReservationRequired());
        product.setScheduable(entity.isSchedulable());
        product.setActivityLevel(transform(entity.getActivityLevel()));
        product.setProductLocation(transform(entity.getLocation()));
        product.setProductDuration(transform(entity.getDuration()));
        product.setCostType(transform(entity.getCostType()));
        product.setStartingFromPrice(transform(entity.getStartingFromPrice()));
        product.setAdvisements(transformProductAdvisement(entity.getAdvisements()));
        product.setPreferences(transformProductPreference(entity.getPreference()));
        product.setRestrictions(transformProductRestriction(entity.getRestrictions()));
        product.setProductTitle(entity.getTitle());
        product.setProductShortDescription(entity.getShortDescription());
        product.setProductLongDescription(entity.getLongDescription());
        product.setProductMedia(transform(entity.getProductMedia()));
        return product;
    }

    private List<ProductRestriction> transformProductRestriction(List<RestrictionEntity> entities) {

        ArrayList<ProductRestriction> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(entities)) {
            return items;
        }
        for (RestrictionEntity productRestrictionResponse : entities) {

            if (productRestrictionResponse == null) {
                continue;
            }

            ProductRestriction productRestriction = new ProductRestriction();
            productRestriction.setRestrictionId(productRestrictionResponse.getRestrictionId());
            productRestriction.setRestrictionType(productRestrictionResponse.getType());
            productRestriction.setMandatory(productRestrictionResponse.isMandatory());
            productRestriction.setRestrictionDisplayText(productRestrictionResponse.getDisplayText());
            productRestriction.setRestrictionTitle(productRestrictionResponse.getTitle());
            productRestriction.setRestrictionDescription(productRestrictionResponse.getDescription());
            productRestriction.setRestrictionQuestion(productRestrictionResponse.getQuestion());
            productRestriction.setRestrictionAnswers(transformProductRestrictionAnswer(productRestrictionResponse.getAnswers()));
            productRestriction.setRestrictionMedia(transform(productRestrictionResponse.getMedia()));
            items.add(productRestriction);
        }

        return items;
    }

    private List<ProductRestrictionAnswer> transformProductRestrictionAnswer(String[] answers) {

        ArrayList<ProductRestrictionAnswer> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(answers)) {
            return items;
        }
        for (String answer : answers) {
            ProductRestrictionAnswer productRestrictionAnswer = new ProductRestrictionAnswer();
            productRestrictionAnswer.setRestrictionAnswerDisplayText(answer);
            items.add(productRestrictionAnswer);
        }

        return items;
    }

    private List<ProductPreference> transformProductPreference(PreferenceEntity entity) {

        ArrayList<ProductPreference> items = new ArrayList<>();
        if (entity == null) {
            return items;
        }
        ProductPreference productPreference = new ProductPreference();
        productPreference.setMandatoryPreferenceFlag(entity.isMandatory());
        productPreference.setPreferenceId(entity.getPreferenceId());
        productPreference.setPreferenceName(entity.getName());
        productPreference.setPreferenceType(entity.getType());
        productPreference.setPreferenceValue(transformPreferencesValues(entity.getValues()));
        items.add(productPreference);

        return items;
    }

    private List<ProductPreferenceValue> transformPreferencesValues(List<PreferenceValueEntity> entities) {
        final List<ProductPreferenceValue> items = new ArrayList<>();
        if (CollectionUtils.isEmpty(entities)) {
            return items;
        }
        for (PreferenceValueEntity entity : entities) {
            if (entity == null) {
                continue;
            }

            ProductPreferenceValue productPreferenceValue = new ProductPreferenceValue();
            productPreferenceValue.setPreferenceValueCode(entity.getCode());
            productPreferenceValue.setPreferenceValueId(entity.getPreferenceValueId());
            productPreferenceValue.setPreferenceValueName(entity.getName());
            items.add(productPreferenceValue);
        }
        return items;
    }

    private List<ProductAdvisement> transformProductAdvisement(List<AdvisementEntity> entities) {

        ArrayList<ProductAdvisement> items = new ArrayList<ProductAdvisement>();

        if (CollectionUtils.isEmpty(entities)) {
            return items;
        }
        for (AdvisementEntity entity : entities) {

            if (entity == null) {
                continue;
            }
            ProductAdvisement productAdvisement = new ProductAdvisement();
            productAdvisement.setAdvisementDescription(entity.getDescription());
            productAdvisement.setAdvisementId(entity.getAdvisementId());
            productAdvisement.setAdvisementName(entity.getName());
            productAdvisement.setAdvisementTitle(entity.getTitle());
            productAdvisement.setAdvisementMedia(transform(entity.getMedia()));
            productAdvisement.setAdvisementType(entity.getType());
            productAdvisement.setAdvisementDescription(entity.getDescription());
            items.add(productAdvisement);
        }

        return items;
    }

    private SellingPrice transform(StartingFromPriceEntity startingFromPriceEntity) {
        SellingPrice sellingPrice = null;
        if (startingFromPriceEntity != null) {
            sellingPrice = new SellingPrice();
            sellingPrice.setAdultPrice(startingFromPriceEntity.getAdultPrice());
            sellingPrice.setChildPrice(startingFromPriceEntity.getChildPrice());
            sellingPrice.setCurrency(startingFromPriceEntity.getCurrency());
            sellingPrice.setInfantPrice(startingFromPriceEntity.getInfantPrice());
        }
        return sellingPrice;
    }

    private ProductCostType transform(CostTypeEntity entity) {
        ProductCostType productCostType = null;
        if (entity != null) {
            productCostType = new ProductCostType();
            productCostType.setCostTypeCode(entity.getCode());
            productCostType.setCostTypeDescription(entity.getDescription());
            productCostType.setCostTypeMedia(transform(entity.getMedia()));
            productCostType.setCostTypeTitle(entity.getTitle());
        }
        return productCostType;
    }

    private ProductDuration transform(DurationEntity entity) {
        ProductDuration productDuration = null;
        if (entity != null) {
            productDuration = new ProductDuration();
            productDuration.setAtYourLeisure(entity.isAtYourLeisure());
            productDuration.setDurationInMinutes(entity.getDurationInMinutes());
            productDuration.setLagTimeInMinutes(entity.getLagTimeInMinutes());
            productDuration.setLeadTimeInMinutes(entity.getLeadTimeInMinutes());
        }
        return productDuration;
    }

    private ProductLocation transform(LocationEntity locationEntity) {
        ProductLocation productLocation = null;
        if (locationEntity != null) {
            productLocation = new ProductLocation();
            productLocation.setLocationCode(locationEntity.getCode());
            productLocation.setLocationType(locationEntity.getType());
            productLocation.setOperatingHoursEnd(locationEntity.getHoursEnd());
            productLocation.setOperatingHoursStart(locationEntity.getHoursStart());
        }
        return productLocation;
    }

    private ProductActivityLevel transform(ActivityLevelEntity entity) {
        ProductActivityLevel productActivityLevel = null;
        if (entity != null) {
            productActivityLevel = new ProductActivityLevel();
            productActivityLevel.setActivityLevelDescription(entity.getDescription());
            productActivityLevel.setActivityLevelId(entity.getActivityLevelId());
            productActivityLevel.setActivityLevelMedia(transform(entity.getMedia()));
        }
        return productActivityLevel;
    }

    private Media transform(MediaEntity mediaEntity) {
        Media media = null;
        if (mediaEntity != null) {
            media = new Media();
            media.setMediaItem(transformMediaItem(mediaEntity.getValues()));
        }
        return media;
    }

    private List<MediaItem> transformMediaItem(List<MediaValueEntity> mediaValueEntities) {
        ArrayList<MediaItem> items = new ArrayList<>();

        if (CollectionUtils.isEmpty(mediaValueEntities)) {
            return items;
        }
        for (MediaValueEntity mediaValueEntity : mediaValueEntities) {

            if (mediaValueEntity == null) {
                continue;
            }
            MediaItem mediaItem = new MediaItem();
            mediaItem.setMediaRefLink(mediaValueEntity.getLink());
            mediaItem.setMediaType(mediaValueEntity.getType());
            items.add(mediaItem);

        }

        return items;
    }

    private ProductType transform(TypeEntity entity) {
        ProductType productType = null;
        if (entity != null) {
            productType = new ProductType();
            productType.setProductTypeId(entity.getTypeId());
            productType.setProductTypeName(entity.getName());
            productType.setProductType(entity.getType());
        }
        return productType;
    }

    private List<ProductCategory> transform(CategoryEntity categoryEntity) {

        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        if (categoryEntity == null) {
            return productCategories;
        }

        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryDescription(categoryEntity.getDescription());
        productCategory.setCategoryId(categoryEntity.getCategoryId());
        productCategory.setProductTags(transformProductTags(categoryEntity.getTags()));
        productCategories.add(productCategory);
        return productCategories;
    }

    private List<ProductTags> transformProductTags(String[] tags) {

        ArrayList<ProductTags> items = new ArrayList<>();

        if (tags == null || tags.length == 0) {
            return items;
        }

        for (String tag : tags) {

            if (tag == null) {
                continue;
            }
            ProductTags productTags = new ProductTags();
            productTags.setDescription(tag);
            items.add(productTags);
        }
        return items;
    }

}