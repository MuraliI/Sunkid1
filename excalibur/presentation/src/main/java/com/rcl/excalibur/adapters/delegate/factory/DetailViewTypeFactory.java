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
import com.rcl.excalibur.domain.SellingPrice;
import com.rcl.excalibur.mapper.ProductInformationMapper;
import com.rcl.excalibur.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.rcl.excalibur.utils.DateUtils.MINUTES_IN_HOUR;

public final class DetailViewTypeFactory {

    private static final int NO_DURATION = 0;
    private static final String NEXT_LINE = "\n";
    private static final String TO_REPLACE = ", ";

    private DetailViewTypeFactory() {
    }

    public static List<RecyclerViewType> getAdaptersAndViewTypesForModel(Product product, Resources resources) {
        LinkedList<RecyclerViewType> viewTypes = new LinkedList<>();

        addHeroSectionHeader(product, viewTypes);
        addMakeReservation(viewTypes, resources, product);
        addCuisineModule(viewTypes, resources, product);
        addDurationModule(viewTypes, resources, product);
        addExperienceModule(viewTypes, resources, product);
        addAttireModule(viewTypes, resources, product);
        addRestrictionModules(viewTypes, resources, product);
        addKnowBeforeYouGoModule(viewTypes, resources, product);
        addDescriptionTypes(viewTypes, product);
        addAccessibilityModule(viewTypes, resources, product);
        addLegalModule(viewTypes, resources, product);

        return viewTypes;
    }

    private static void addCuisineModule(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, Product product) {
        List<ProductAdvisement> advisementsCuisine = product.getProductAdvisementsById(ProductAdvisement.CUISINE);
        if (CollectionUtils.isEmpty(advisementsCuisine)) {
            return;
        }
        ProductAdvisement advisement = advisementsCuisine.get(0);
        if (advisement != null && !TextUtils.isEmpty(advisement.getAdvisementDescription())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList,
                    resources.getString(R.string.detail_module_cuisine),
                    advisement.getAdvisementDescription());
        }
    }

    private static void addHeroSectionHeader(Product product, LinkedList<RecyclerViewType> viewTypes) {
        viewTypes.add(new ProductInformationMapper().transform(product));
    }

    private static void addRestrictionModules(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, Product product) {
        if (CollectionUtils.isEmpty(product.getRestrictions())) {
            return;
        }
        for (ProductRestriction restriction : product.getRestrictions()) {
            String title = restriction.getRestrictionTitle();
            String description = restriction.getRestrictionDisplayText();
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                addTitleAndDescriptionTypes(recyclerViewTypeList, title, description);
            }
        }
    }

    private static void addAccessibilityModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources,
                                               Product product) {

        List<ProductAdvisement> accessibilities = product.getProductAdvisementsById(ProductAdvisement.ACCESSIBILITY);
        if (CollectionUtils.isEmpty(accessibilities)) {
            return;
        }
        addExpandableAccessibilityTypes(recyclerViewTypeList, resources, accessibilities);
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
                                          Product product) {
        if (product.getProductDuration() == null) {
            return;
        }
        long duration = product.getProductDuration().getDurationInMinutes();
        if (duration > NO_DURATION) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.duration), getDurationFormatted(res, (int) duration));
        }
    }

    public static String getDurationFormatted(Resources resources, int duration) {
        String durationStr = "";
        int hours = duration / MINUTES_IN_HOUR;
        int remainingMinutes = duration % MINUTES_IN_HOUR;
        if (hours > 0) {
            durationStr += resources.getQuantityString(R.plurals.product_hr, hours, hours);
        }
        if (remainingMinutes > 0) {
            durationStr += resources.getQuantityString(R.plurals.product_min, remainingMinutes, remainingMinutes);
        }
        return durationStr;
    }

    private static void addAttireModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                        Product product) {
        List<ProductAdvisement> advisementsAttire = product.getProductAdvisementsById(ProductAdvisement.ATTIRE);
        if (CollectionUtils.isEmpty(advisementsAttire)) {
            return;
        }
        ProductAdvisement advisement = advisementsAttire.get(0);
        if (advisement != null && !TextUtils.isEmpty(advisement.getAdvisementDescription())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList,
                    res.getString(R.string.discover_item_detail_attire),
                    advisement.getAdvisementDescription());
        }
    }

    private static void addKnowBeforeYouGoModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                                 Product product) {
        List<ProductAdvisement> advisementsKnow = product.getProductAdvisementsById(ProductAdvisement.KNOW_BEFORE_YOU_GO);
        if (CollectionUtils.isEmpty(advisementsKnow)) {
            return;
        }
        ProductAdvisement advisement = advisementsKnow.get(0);
        String description = advisement.getAdvisementDescription();
        if (advisement != null && !TextUtils.isEmpty(description)) {
            //FIXME hardcoded to match UI Design
            description = description.replace(TO_REPLACE, NEXT_LINE);
            addTitleAndDescriptionTypes(recyclerViewTypeList,
                    res.getString(R.string.discover_item_detail_know),
                    description);
        }
    }

    private static void addLegalModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                       Product product) {
        List<ProductAdvisement> advisementsLegal = product.getProductAdvisementsById(ProductAdvisement.LEGAL);
        if (CollectionUtils.isEmpty(advisementsLegal)) {
            return;
        }
        ProductAdvisement advisement = advisementsLegal.get(0);
        if (advisement != null && !TextUtils.isEmpty(advisement.getAdvisementDescription())) {
            addExpandableAndDescriptionTypes(recyclerViewTypeList,
                    res.getString(R.string.detail_module_legal),
                    advisement.getAdvisementDescription());
        }
    }

    private static void addDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, Product product) {
        String description = product.getProductShortDescription();
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
                                           @NonNull Resources resources, Product product) {
        if (!TextUtils.isEmpty(product.getProductReservationInformation())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.discover_item_detail_make_a_reservation),
                    product.getProductReservationInformation());
        }
    }

    private static void addExperienceModule(final List<RecyclerViewType> recyclerViewTypeList,
                                            @NonNull Resources resources, Product product) {
        if (!TextUtils.isEmpty(product.getExperience())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.discover_item_detail_experience),
                    product.getExperience());
        }
    }

    private static void addExpandableAccessibilityTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                                        final List<ProductAdvisement> accessibilities) {

        recyclerViewTypeList.add(new ExpandableAccesibilityViewType(res.getString(R.string.accessibility), accessibilities));
    }

    private void addPriceFromTypes(final List<RecyclerViewType> recyclerViewTypeList, Product product) {
        final SellingPrice sellingPrice = product.getStartingFromPrice();
        if (sellingPrice == null) {
            return;
        }
        final float adultPrice = sellingPrice.getAdultPrice();
        final float childPrice = sellingPrice.getChildPrice();
        if (adultPrice != 0 || childPrice != 0) {
            recyclerViewTypeList.add(new PricesFromViewType(StringUtils.getPriceFormated(adultPrice),
                    StringUtils.getPriceFormated(childPrice)));
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
