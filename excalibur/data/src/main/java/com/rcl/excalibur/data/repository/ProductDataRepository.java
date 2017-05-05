package com.rcl.excalibur.data.repository;


import android.support.annotation.NonNull;

import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.rcl.excalibur.data.entity.AdvisementEntity;
import com.rcl.excalibur.data.entity.CategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryEntity;
import com.rcl.excalibur.data.entity.ChildCategoryProductEntity;
import com.rcl.excalibur.data.entity.CostTypeEntity;
import com.rcl.excalibur.data.entity.DeckInfoEntity;
import com.rcl.excalibur.data.entity.DurationEntity;
import com.rcl.excalibur.data.entity.LocationEntity;
import com.rcl.excalibur.data.entity.MediaEntity;
import com.rcl.excalibur.data.entity.MediaValueEntity;
import com.rcl.excalibur.data.entity.OfferingEntity;
import com.rcl.excalibur.data.entity.OperationHourEntity;
import com.rcl.excalibur.data.entity.PreferenceValueEntity;
import com.rcl.excalibur.data.entity.PriceEntity;
import com.rcl.excalibur.data.entity.ProductEntity;
import com.rcl.excalibur.data.entity.RestrictionEntity;
import com.rcl.excalibur.data.entity.StartingFromPriceEntity;
import com.rcl.excalibur.data.entity.TypeEntity;
import com.rcl.excalibur.data.mapper.ProductEntityDataMapper;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.ChildCategory;
import com.rcl.excalibur.domain.LocationDeckInfo;
import com.rcl.excalibur.domain.LocationOperationHour;
import com.rcl.excalibur.domain.Media;
import com.rcl.excalibur.domain.MediaItem;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductCategory;
import com.rcl.excalibur.domain.ProductCostType;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.domain.ProductRestrictionAnswer;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.domain.SellingPrice;
import com.rcl.excalibur.domain.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.data.utils.DBUtil.eq;
import static com.rcl.excalibur.data.utils.DBUtil.eqId;

public class ProductDataRepository extends BaseDataRepository<Product, ProductEntity, Void, ProductEntityDataMapper>
        implements ProductRepository {


    public ProductDataRepository() {
        super(new ProductEntityDataMapper(), ProductEntity.class);
    }

    @Override
    public void create(@NonNull Product product) {
        ProductEntity entity = getEntity(ProductEntity.COLUMN_PRODUCT_ID, product.getProductId());
        if (entity != null) {
            delete(entity);
        }

        entity = new ProductEntity();
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
        //productMedia
        create(entity, product.getProductMedia());
        //StartingFromPrice
        create(entity, product.getStartingFromPrice());
        //Category
        createCategory(entity, product.getProductCategory());

        entity.save();

        //Advisements
        createAdvisements(entity, product.getAdvisements());

        //Restriction
        createRestriction(entity, product.getRestrictions());

        //Offerings
        createOfferings(entity, product.getOfferings());
    }

    private void createOfferings(ProductEntity entity, List<Offering> offerings) {
        if (CollectionUtils.isEmpty(offerings)) {
            return;
        }
        for (Offering offering : offerings) {
            if (existsOffering(offering.getId())) {
                continue;
            }
            OfferingEntity offeringEntity = new OfferingEntity();
            offeringEntity.setOfferingId(offering.getId());
            offeringEntity.setProductEntity(entity);
            offeringEntity.setDate(offering.getDateString());
            offeringEntity.setTime(offering.getTimeString());
            offeringEntity.setPrice(create(offering.getPrice()));

            offeringEntity.save();
        }
    }

    private boolean existsOffering(String id) {
        return new Select()
                .from(OfferingEntity.class)
                .where(eq(OfferingEntity.COLUMN_OFFERING_ID, id))
                .exists();
    }

    private PriceEntity create(SellingPrice sellingPrice) {
        if (sellingPrice == null) {
            return null;
        }
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setCurrency(sellingPrice.getCurrency());
        priceEntity.setAdultPrice(sellingPrice.getAdultPrice());
        priceEntity.setChildPrice(sellingPrice.getChildPrice());
        priceEntity.setInfantPrice(sellingPrice.getInfantPrice());
        priceEntity.save();
        return priceEntity;

    }

    private void delete(Model model) {
        if (model != null) {
            model.delete();
        }
    }

    private void delete(@NonNull ProductEntity entity) {
        List<RestrictionEntity> restrictionEntities = entity.getRestrictions();
        for (RestrictionEntity restrictionEntity : restrictionEntities) {
            delete(restrictionEntity.getMedia());
            restrictionEntity.delete();
        }

        List<AdvisementEntity> advisementEntities = entity.getAdvisements();
        for (AdvisementEntity advisementEntity : advisementEntities) {
            delete(advisementEntity.getMedia());
            advisementEntity.delete();
        }

        List<OfferingEntity> offeringEntities = entity.getOfferings();
        for (OfferingEntity offeringEntity : offeringEntities) {
            delete(offeringEntity.getPrice());
            offeringEntity.delete();
        }

        delete(entity.getStartingFromPrice());
        delete(entity.getCostType());
        delete(entity.getDuration());
        delete(entity.getLocation());
        delete(entity.getProductMedia());

        new Delete().from(ProductEntity.class).where(eqId(entity.getId())).execute();

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
        new Delete().from(PreferenceValueEntity.class).execute();
        new Delete().from(CostTypeEntity.class).execute();
        new Delete().from(DurationEntity.class).execute();
        new Delete().from(DeckInfoEntity.class).execute();
        new Delete().from(OperationHourEntity.class).execute();
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
        costTypeEntity.save();
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
        locationEntity.setCode(productLocation.getLocationCode());
        locationEntity.setTitle(productLocation.getLocationTitle());
        locationEntity.setType(productLocation.getLocationType());
        locationEntity.setLatitude(productLocation.getLatitude());
        locationEntity.setLongitude(productLocation.getLongitude());
        locationEntity.save();

        // DeckInfo
        createDeckInfo(locationEntity, productLocation.getLocationDeckInfo());

        // Operation Hours
        createOperationHours(locationEntity, productLocation.getLocationOperationHours());

        entity.setLocation(locationEntity);
    }

    private void createDeckInfo(final LocationEntity entity, final List<LocationDeckInfo> deckInfoList) {
        if (CollectionUtils.isEmpty(deckInfoList)) {
            return;
        }
        for (LocationDeckInfo locationDeckInfo : deckInfoList) {
            final DeckInfoEntity deckInfoEntity = new DeckInfoEntity();
            deckInfoEntity.setDeckNumber(locationDeckInfo.getDeckNumber());
            deckInfoEntity.setDirection(locationDeckInfo.getDirection());
            deckInfoEntity.setLocationEntity(entity);
            deckInfoEntity.save();
        }
    }

    private void createOperationHours(final LocationEntity entity, final List<LocationOperationHour> operationHourList) {
        if (CollectionUtils.isEmpty(operationHourList)) {
            return;
        }
        for (LocationOperationHour locationOperationHour : operationHourList) {
            final OperationHourEntity operationHourEntity = new OperationHourEntity();
            operationHourEntity.setDayNumber(locationOperationHour.getDayNumber());
            operationHourEntity.setTimeOfDay(locationOperationHour.getTimeOfDay());
            operationHourEntity.setStartTime(locationOperationHour.getStartTime());
            operationHourEntity.setEndTime(locationOperationHour.getEndTime());
            operationHourEntity.setLocationEntity(entity);
            operationHourEntity.save();
        }
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
        CategoryEntity categoryEntity = (CategoryEntity) getEntity(CategoryEntity.class, CategoryEntity.COLUMN_CATEGORY_ID, category.getCategoryId());
        if (categoryEntity == null) {
            categoryEntity = new CategoryEntity();
            categoryEntity.setName(category.getCategoryName());
            categoryEntity.setCategoryId(category.getCategoryId());
            categoryEntity.setDescription(category.getCategoryDescription());
            categoryEntity.save();
        }
//        createChildCategories(categoryEntity, category.getChildCategory());
        entity.setCategory(categoryEntity);
    }

    private void createChildCategories(final CategoryEntity entity, final List<ChildCategory> categories) {

        if (CollectionUtils.isEmpty(categories)) {
            return;
        }

        for (ChildCategory childCategory : categories) {

            final ChildCategoryEntity childCategoryEntity = new ChildCategoryEntity();
            childCategoryEntity.setChildCategoryId(childCategory.getItems().getCategoryId());
            childCategoryEntity.setDescription(childCategory.getItems().getCategoryDescription());
            childCategoryEntity.setName(childCategory.getItems().getCategoryName());
            childCategoryEntity.setCategory(entity);
            childCategoryEntity.save();
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
    public List<Product> getByCategory(@NonNull final String category, int maxCount, int offset) {
        final CategoryEntity categoryEntity = (CategoryEntity) getEntity(CategoryEntity.class, CategoryEntity.COLUMN_CATEGORY_ID, category);
        if (categoryEntity == null) {
            return new ArrayList<>();
        }
        String condition = eq(ProductEntity.COLUMN_CATEGORY, categoryEntity.getId());
        return this.getBatch(condition, maxCount, offset);
    }

    @Override
    public Product get(String id) {
        return get(ProductEntity.COLUMN_PRODUCT_ID, id);
    }

}
