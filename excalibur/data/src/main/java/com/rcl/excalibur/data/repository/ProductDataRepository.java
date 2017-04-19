package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.ActivityLevelEntity;
import com.rcl.excalibur.data.entity.AdvisementEntity;
import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryProductEntity;
import com.rcl.excalibur.data.entity.CostTypeEntity;
import com.rcl.excalibur.data.entity.DurationEntity;
import com.rcl.excalibur.data.entity.LocationEntity;
import com.rcl.excalibur.data.entity.MediaEntity;
import com.rcl.excalibur.data.entity.MediaValueEntity;
import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.PreferenceEntity;
import com.rcl.excalibur.data.entity.PreferenceValueEntity;
import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.entity.RestrictionEntity;
import com.rcl.excalibur.data.entity.StartingFromPriceEntity;
import com.rcl.excalibur.data.entity.TypeEntity;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.ChildCategory;
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
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;

public class ProductDataRepository extends BaseDataRepository<Product, ProductEntity, Void, ProductEntityDataMapper>
        implements ProductRepository {

    public ProductDataRepository() {
        super(new ProductEntityDataMapper(), ProductEntity.class);
    }

    @Override
    public void create(@NonNull Product product) {
        final ProductEntity entity = new ProductEntity();
        entity.setProductId(product.getProductId());
        entity.setProductClass(product.getProductClass());
        entity.setTitle(product.getProductTitle());
        entity.setCode(product.getProductCode());
        entity.setLongDescription(product.getProductLongDescription());
        entity.setShortDescription(product.getProductShortDescription());
        entity.setRank(product.getProductRank());
        entity.setSchedulable(product.isScheduable());
        entity.setReservationRequired(product.isReservationRequired());
        entity.setUpcharge(product.getProductUpcharge());
        entity.setExperience(product.getExperience());
        entity.setProductReservationInformation(product.getProductReservationInformation());

        //ProductType
        create(entity, product.getProductType());

        //Location
        create(entity, product.getProductLocation());
        //Duration
        create(entity, product.getProductDuration());
        //CostTypeEntity
        create(entity, product.getCostType());
        //preference
        createPreferences(entity, product.getPreferences());
        //productMedia
        create(entity, product.getProductMedia());
        //Activity Level
        create(entity, product.getActivityLevel());
        //StartingFromPrice
        create(entity, product.getStartingFromPrice());
        //Category
        createCategory(entity, product.getProductCategory());

        entity.save();

        //Advisements
        createAdvisements(entity, product.getAdvisements());

        //Restriction
        createRestriction(entity, product.getRestrictions());
    }

    @Override
    public void deleteAll() {
        new Delete().from(RestrictionEntity.class).execute();
        new Delete().from(AdvisementEntity.class).execute();
        new Delete().from(OfferingEntity.class).execute();
        new Delete().from(PriceEntity.class).execute();
        new Delete().from(ChildCategoryProductEntity.class).execute();
        new Delete().from(ProductEntity.class).execute();
        new Delete().from(CategoryEntity.class).execute();
        new Delete().from(StartingFromPriceEntity.class).execute();
        new Delete().from(ActivityLevelEntity.class).execute();
        new Delete().from(PreferenceValueEntity.class).execute();
        new Delete().from(PreferenceEntity.class).execute();
        new Delete().from(CostTypeEntity.class).execute();
        new Delete().from(DurationEntity.class).execute();
        new Delete().from(LocationEntity.class).execute();
        new Delete().from(TypeEntity.class).execute();
        new Delete().from(MediaValueEntity.class).execute();
        new Delete().from(MediaEntity.class).execute();
    }

    private void create(final ProductEntity entity, final SellingPrice startingFromPrice) {
        if (startingFromPrice == null) {
            return;
        }
        final StartingFromPriceEntity startingFromPriceEntity = new StartingFromPriceEntity();
        startingFromPriceEntity.setAdultPrice(startingFromPrice.getAdultPrice());
        startingFromPriceEntity.setChildPrice(startingFromPrice.getChildPrice());
        startingFromPriceEntity.setCurrency(startingFromPrice.getCurrency());
        startingFromPriceEntity.setInfantPrice(startingFromPrice.getInfantPrice());
        startingFromPriceEntity.save();
        entity.setStartingFromPrice(startingFromPriceEntity);
    }

    private void create(final ProductEntity entity, final ProductActivityLevel activityLevel) {
        if (activityLevel == null) {
            return;
        }
        final ActivityLevelEntity activityLevelEntity = new ActivityLevelEntity();
        activityLevelEntity.setTitle(activityLevel.getActivityLevelTitle());
        activityLevelEntity.setDescription(activityLevel.getActivityLevelDescription());
        activityLevelEntity.setActivityLevelId(activityLevel.getActivityLevelId());
        final Media media = activityLevel.getActivityLevelMedia();
        if (media != null && !CollectionUtils.isEmpty(media.getMediaItem())) {
            final MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.save();
            for (MediaItem mediaItem : media.getMediaItem()) {
                final MediaValueEntity mediaValueEntity = new MediaValueEntity();
                mediaValueEntity.setMedia(mediaEntity);
                mediaValueEntity.setLink(mediaItem.getMediaRefLink());
                mediaValueEntity.setType(mediaItem.getMediaType());
                mediaValueEntity.save();
            }
            activityLevelEntity.setMedia(mediaEntity);
        }
        activityLevelEntity.save();
        entity.setActivityLevel(activityLevelEntity);
    }

    private void createRestriction(final ProductEntity entity, final List<ProductRestriction> productRestrictions) {
        if (CollectionUtils.isEmpty(productRestrictions)) {
            return;
        }
        for (ProductRestriction productRestriction : productRestrictions) {
            final RestrictionEntity restrictionEntity = new RestrictionEntity();
            restrictionEntity.setType(productRestriction.getRestrictionType());
            restrictionEntity.setDisplayText(productRestriction.getRestrictionDisplayText());
            restrictionEntity.setMandatory(productRestriction.isMandatory());
            restrictionEntity.setQuestion(productRestriction.getRestrictionQuestion());
            restrictionEntity.setRestrictionId(productRestriction.getRestrictionId());
            restrictionEntity.setTitle(productRestriction.getRestrictionTitle());
            restrictionEntity.setProduct(entity);
            final List<ProductRestrictionAnswer> answers = productRestriction.getRestrictionAnswers();
            if (!CollectionUtils.isEmpty(answers)) {
                final List<String> result = new ArrayList<>();
                for (ProductRestrictionAnswer answer : answers) {
                    result.add(answer.getRestrictionAnswerDisplayText());
                }
                restrictionEntity.setAnswers(result);
            }
            restrictionEntity.save();
        }
    }

    private void createAdvisements(final ProductEntity entity, final List<ProductAdvisement> productAdvisements) {
        if (CollectionUtils.isEmpty(productAdvisements)) {
            return;
        }
        for (ProductAdvisement productAdvisement : productAdvisements) {
            final AdvisementEntity advisementEntity = new AdvisementEntity();
            advisementEntity.setName(productAdvisement.getAdvisementName());
            advisementEntity.setTitle(productAdvisement.getAdvisementTitle());
            advisementEntity.setDescription(productAdvisement.getAdvisementDescription());
            advisementEntity.setType(productAdvisement.getAdvisementType());
            advisementEntity.setAdvisementId(productAdvisement.getAdvisementId());
            advisementEntity.setProduct(entity);
            final Media media = productAdvisement.getAdvisementMedia();
            if (media != null && !CollectionUtils.isEmpty(media.getMediaItem())) {
                final MediaEntity mediaEntity = new MediaEntity();
                mediaEntity.save();
                for (MediaItem mediaItem : media.getMediaItem()) {
                    final MediaValueEntity mediaValueEntity = new MediaValueEntity();
                    mediaValueEntity.setMedia(mediaEntity);
                    mediaValueEntity.setLink(mediaItem.getMediaRefLink());
                    mediaValueEntity.setType(mediaItem.getMediaType());
                    mediaValueEntity.save();
                }
                advisementEntity.setMedia(mediaEntity);
            }
            advisementEntity.save();
        }
    }

    private void create(final ProductEntity entity, final Media productMedia) {
        if (productMedia == null || CollectionUtils.isEmpty(productMedia.getMediaItem())) {
            return;
        }
        final MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.save();
        for (MediaItem mediaItem : productMedia.getMediaItem()) {
            final MediaValueEntity mediaValueEntity = new MediaValueEntity();
            mediaValueEntity.setType(mediaItem.getMediaType());
            mediaValueEntity.setLink(mediaItem.getMediaRefLink());
            mediaValueEntity.setMedia(mediaEntity);
            mediaValueEntity.save();
        }
        entity.setProductMedia(mediaEntity);
    }

    private void createPreferences(final ProductEntity entity, final List<ProductPreference> preferences) {
        if (CollectionUtils.isEmpty(preferences)) {
            return;
        }
        for (ProductPreference productPreference : preferences) {
            final PreferenceEntity preferenceEntity = new PreferenceEntity();
            preferenceEntity.setName(productPreference.getPreferenceName());
            preferenceEntity.setType(productPreference.getPreferenceType());
            preferenceEntity.setMandatory(productPreference.isMandatoryPreferenceFlag());
            preferenceEntity.setPreferenceId(productPreference.getPreferenceId());
            preferenceEntity.save();
            //Preference Values
            final List<ProductPreferenceValue> productPreferenceValues = productPreference.getPreferenceValue();
            if (!CollectionUtils.isEmpty(productPreferenceValues)) {
                for (ProductPreferenceValue productPreferenceValue : productPreferenceValues) {
                    final PreferenceValueEntity preferenceValueEntity = new PreferenceValueEntity();
                    preferenceValueEntity.setName(productPreferenceValue.getPreferenceValueName());
                    preferenceValueEntity.setCode(productPreferenceValue.getPreferenceValueCode());
                    preferenceValueEntity.setPreference(preferenceEntity);
                    preferenceValueEntity.setPreferenceValueId(productPreferenceValue.getPreferenceValueId());
                }
            }
            entity.setPreference(preferenceEntity);
        }
    }

    private void create(final ProductEntity entity, final ProductCostType productCostType) {
        if (productCostType == null) {
            return;
        }
        final CostTypeEntity costTypeEntity = new CostTypeEntity();
        costTypeEntity.setTitle(productCostType.getCostTypeTitle());
        costTypeEntity.setDescription(productCostType.getCostTypeDescription());
        costTypeEntity.setCode(productCostType.getCostTypeCode());
        final Media media = productCostType.getCostTypeMedia();
        if (media != null && !CollectionUtils.isEmpty(media.getMediaItem())) {
            final MediaEntity mediaEntity = new MediaEntity();
            mediaEntity.save();
            for (MediaItem mediaItem : media.getMediaItem()) {
                final MediaValueEntity mediaValueEntity = new MediaValueEntity();
                mediaValueEntity.setMedia(mediaEntity);
                mediaValueEntity.setLink(mediaItem.getMediaRefLink());
                mediaValueEntity.setType(mediaItem.getMediaType());
                mediaValueEntity.save();
            }
            costTypeEntity.setMedia(mediaEntity);
        }
        entity.setCostTypeEntity(costTypeEntity);
    }

    private void create(final ProductEntity entity, final ProductDuration productDuration) {
        if (productDuration == null) {
            return;
        }
        final DurationEntity durationEntity = new DurationEntity();
        durationEntity.setAtYourLeisure(productDuration.isAtYourLeisure());
        durationEntity.setDurationInMinutes(productDuration.getDurationInMinutes());
        durationEntity.setLagTimeInMinutes(productDuration.getLagTimeInMinutes());
        durationEntity.setLeadTimeInMinutes(productDuration.getLeadTimeInMinutes());
        durationEntity.save();
        entity.setDuration(durationEntity);

    }

    private void create(final ProductEntity entity, final ProductLocation productLocation) {
        if (productLocation == null) {
            return;
        }
        final LocationEntity locationEntity = new LocationEntity();
        locationEntity.setType(productLocation.getLocationType());
        locationEntity.setCode(productLocation.getLocationCode());
        locationEntity.setHoursStart(productLocation.getOperatingHoursStart());
        locationEntity.setHoursEnd(productLocation.getOperatingHoursEnd());
        locationEntity.setPort(productLocation.getLocationPort());
        locationEntity.setDeckNumber(productLocation.getLocationDeckNumber());
        locationEntity.setDirection(productLocation.getLocationDirection());
        locationEntity.setVenue(productLocation.getLocationVenue());
        locationEntity.save();
        entity.setLocation(locationEntity);

    }

    private void create(final ProductEntity entity, final ProductType productType) {
        if (productType == null) {
            return;
        }
        TypeEntity typeEntity = new Select()
                .from(TypeEntity.class)
                .where(eq(TypeEntity.COLUMN_TYPE, productType.getProductType()))
                .executeSingle();
        if (typeEntity == null) {
            typeEntity = new TypeEntity();
            typeEntity.setName(productType.getProductTypeName());
            typeEntity.setType(productType.getProductType());
            typeEntity.setTypeId(productType.getProductTypeId());
            typeEntity.save();
        }
        entity.setType(typeEntity);

    }

    private void createCategory(final ProductEntity entity, final ProductCategory category) {

        if (category == null) {
            return;
        }

        final CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(category.getCategoryId());
        categoryEntity.setDescription(category.getCategoryDescription());
        categoryEntity.setName(category.getCategoryName());
        categoryEntity.save();
        entity.setCategory(categoryEntity);

        createChildCategories(categoryEntity, category.getChildCategory());

    }

    private void createChildCategories(final CategoryEntity entity, final List<ChildCategory> categories) {

        if (CollectionUtils.isEmpty(categories)) {
            return;
        }

        for (ChildCategory childCategory : categories) {

            final ChildCategoryProductEntity childCategoryProductEntity = new ChildCategoryProductEntity();
            childCategoryProductEntity.setCategoryId(childCategory.getItems().getCategoryId());
            childCategoryProductEntity.setDescription(childCategory.getItems().getCategoryDescription());
            childCategoryProductEntity.setName(childCategory.getItems().getCategoryName());
            childCategoryProductEntity.setCategory(entity);
            childCategoryProductEntity.save();
        }
    }

    @Override
    public List<Product> getAll(@NonNull final String type) {
        final TypeEntity typeEntity = new Select()
                .from(TypeEntity.class)
                .where(eq(TypeEntity.COLUMN_TYPE, type))
                .executeSingle();
        if (typeEntity == null) {
            return new ArrayList<>();
        }
        final List<ProductEntity> entities = new Select()
                .from(ProductEntity.class)
                .where(eq(ProductEntity.COLUMN_TYPE, typeEntity.getId()))
                .execute();
        return getMapper().transform(entities, null);
    }

    @Override
    public Product get(String id) {
        return get(ProductEntity.COLUMN_PRODUCT_ID, id);
    }


}
