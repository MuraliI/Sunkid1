package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.DescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableAccesibilityViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableLinkViewType;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductActivityLevel;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.mapper.ProductInformationMapper;
import com.rcl.excalibur.mapper.ProductModelDataMapper;
import com.rcl.excalibur.model.ProductAccessibilityModel;
import com.rcl.excalibur.model.ProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.rcl.excalibur.domain.ProductAdvisement.ATTIRE;
import static com.rcl.excalibur.domain.ProductAdvisement.KNOW_BEFORE_YOU_GO;
import static com.rcl.excalibur.domain.ProductAdvisement.LEGAL;
import static com.rcl.excalibur.utils.StringUtils.getPriceFormatted;

public final class DetailViewTypeFactory {

    private static final int NO_DURATION = 0;
    private static final String NEXT_LINE = "\n";
    private static final String TO_REPLACE = ", ";

    private DetailViewTypeFactory() {
    }

    public static List<RecyclerViewType> getAdaptersAndViewTypesForModel(Product product, Resources resources) {
        LinkedList<RecyclerViewType> viewTypes = new LinkedList<>();
        ProductModel model = new ProductModelDataMapper().transform(product);

        addHeroSectionHeader(product, viewTypes);
        addMakeReservation(viewTypes, resources, model);
        addPricesModule(viewTypes, resources, model);
        addCuisineModule(viewTypes, resources, model);
        addDurationModule(viewTypes, resources, model);
        addExperienceModule(viewTypes, resources, model);
        addAttireModule(viewTypes, resources, model);
        addAgeModule(viewTypes, resources, model);
        addHeightModule(viewTypes, resources, model);
        addKnowBeforeYouGoModule(viewTypes, resources, model);
        addDescriptionTypes(viewTypes, model.getDescription());
        addAccessibilityModule(viewTypes, resources, model);
        addLegalModule(viewTypes, resources, model);

        return viewTypes;
    }

    private static void addCuisineModule(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, ProductModel product) {
        if (product.getAdvisementsAndReestrictions().containsKey(ProductAdvisement.CUISINE)) {
            String description = product.getAdvisementsAndReestrictions().get(ProductAdvisement.CUISINE);
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.detail_module_cuisine), description);
        }
    }

    private static void addHeroSectionHeader(Product product, LinkedList<RecyclerViewType> viewTypes) {
        viewTypes.add(new ProductInformationMapper().transform(product));
    }

    private static void addAgeModule(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, ProductModel product) {
        if (product.getAdvisementsAndReestrictions().containsKey(ProductRestriction.AGE)) {
            String description = product.getAdvisementsAndReestrictions().get(ProductRestriction.AGE);
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.detail_module_age), description);
        }
    }

    private static void addHeightModule(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, ProductModel product) {
        if (product.getAdvisementsAndReestrictions().containsKey(ProductRestriction.HEIGHT)) {
            String description = product.getAdvisementsAndReestrictions().get(ProductRestriction.HEIGHT);
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.detail_module_height), description);
        }
    }

    private static void addAccessibilityModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources,
                                               ProductModel product) {
        if (CollectionUtils.isEmpty(product.getAccessibilities())) {
            return;
        }
        addExpandableAccessibilityTypes(recyclerViewTypeList, resources, product.getAccessibilities());
    }

    private static boolean isHoursEmpty(String value) {
        return TextUtils.isEmpty(value) || "0".equals(value);
    }

    private static void addExpandableAndDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, final String title,
                                                         final String description) {
        String[] descriptionArr = {description};
        recyclerViewTypeList.add(new ExpandableLinkViewType(title, descriptionArr));
    }

    private static void addDurationModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                          ProductModel product) {
        if (product.getDuration() > NO_DURATION) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.duration), product.getDurationFormatted(res));
        }
    }

    private static void addAttireModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                        ProductModel product) {
        if (product.getAdvisementsAndReestrictions().containsKey(ATTIRE)) {
            String description = product.getAdvisementsAndReestrictions().get(ATTIRE);
            addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.discover_item_detail_attire), description);
        }
    }

    private static void addKnowBeforeYouGoModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                                 ProductModel product) {
        if (product.getAdvisementsAndReestrictions().containsKey(KNOW_BEFORE_YOU_GO)) {
            String description = product.getAdvisementsAndReestrictions().get(KNOW_BEFORE_YOU_GO);
            //FIXME hardcoded to match UI Design
            description = description.replace(TO_REPLACE, NEXT_LINE);
            addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.discover_item_detail_know), description);
        }
    }

    private static void addLegalModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                       ProductModel product) {
        if (product.getAdvisementsAndReestrictions().containsKey(LEGAL)) {
            String description = product.getAdvisementsAndReestrictions().get(LEGAL);
            addExpandableAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.detail_module_legal), description);
        }
    }

    private static void addDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, final String description) {
        if (TextUtils.isEmpty(description)) {
            return;
        }
        recyclerViewTypeList.add(new DescriptionViewType(description));
    }

    private static void addTitleAndDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, final String title,
                                                    final String description) {
        recyclerViewTypeList.add(new TitleAndDescriptionViewType(title, description));
    }

    private static void addMakeReservation(final List<RecyclerViewType> recyclerViewTypeList,
                                           @NonNull Resources resources, ProductModel product) {
        if (!TextUtils.isEmpty(product.getReservationInformation())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.discover_item_detail_make_a_reservation),
                    product.getReservationInformation());
        }
    }

    private static void addExperienceModule(final List<RecyclerViewType> recyclerViewTypeList,
                                            @NonNull Resources resources, ProductModel product) {
        if (!TextUtils.isEmpty(product.getExperience())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.discover_item_detail_experience),
                    product.getExperience());
        }
    }

    private static void addExpandableAccessibilityTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                                        final List<ProductAccessibilityModel> accessibilities) {

        recyclerViewTypeList.add(new ExpandableAccesibilityViewType(res.getString(R.string.accessibility), accessibilities));
    }

    private static void addPricesModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res, ProductModel product) {
        final float adultPrice = 20;
        final float childPrice = 10;
        if (adultPrice != 0 || childPrice != 0) {
            HashMap<String, String> map = new HashMap<>();

            if (adultPrice > 0) {
                map.put(res.getString(R.string.adult), res.getString(R.string.item_price, getPriceFormatted(adultPrice)));
            }
            if (childPrice > 0) {
                map.put(res.getString(R.string.child), res.getString(R.string.item_price, getPriceFormatted(childPrice)));
            }

            PricesFromViewType pricesFromViewType = new PricesFromViewType(res.getString(R.string.prices),
                    res.getString(R.string.starting_from), map);
            recyclerViewTypeList.add(pricesFromViewType);
        }
    }

    private void addLongDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, Product product) {
        final String description = product.getProductLongDescription();
        if (TextUtils.isEmpty(description)) {
            return;
        }
        recyclerViewTypeList.add(new ExpandableDescriptionViewType(description));
    }

    private void addProductLocationTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources, Product product) {
        final ProductLocation productLocation = product.getProductLocation();
        if (productLocation == null || isHoursEmpty(productLocation.getOperatingHoursEnd())) {
            return;
        }
        ArrayList<String[]> arrayListTimes = new ArrayList<>();
        arrayListTimes.add(new String[]{resources.getString(R.string.day_1), product.getTimeFrame()});
        recyclerViewTypeList.add(new StandardTimesViewType(resources.getString(R.string.operating_hours), arrayListTimes));
    }

    private void addProductLevel(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources, Product product) {
        final ProductActivityLevel productActivityLevel = product.getActivityLevel();
        if (productActivityLevel == null) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.activity_level),
                productActivityLevel.getActivityLevelTitle());
    }

}
