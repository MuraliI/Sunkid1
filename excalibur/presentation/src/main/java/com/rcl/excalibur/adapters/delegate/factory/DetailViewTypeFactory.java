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
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductActivityLevel;
import com.rcl.excalibur.domain.ProductAdvisement;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.domain.ProductType;
import com.rcl.excalibur.mapper.ProductInformationMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.rcl.excalibur.utils.PresentationDateUtils.MINUTES_IN_HOUR;
import static com.rcl.excalibur.data.utils.StringUtils.getPriceFormatted;

public final class DetailViewTypeFactory {

    private static final int NO_DURATION = 0;
    private static final String NEXT_LINE = "\n";
    private static final String TO_REPLACE = ", ";
    // TODO: To be removed once the service provides this details
    private static final String TIME_HARDCODE = "Times may vary";

    private DetailViewTypeFactory() {
    }

    public static List<RecyclerViewType> getAdaptersAndViewTypesForModel(Product product, List<Offering> offerings, Resources resources) {
        LinkedList<RecyclerViewType> viewTypes = new LinkedList<>();

        addHeroSectionHeader(product, viewTypes);
        addTimeModule(viewTypes, resources, product);
        addPricesModule(viewTypes, offerings, resources, product);
        addCuisineModule(viewTypes, resources, product);
        addDurationModule(viewTypes, resources, product);
        addAttireModule(viewTypes, resources, product);
        addRestrictionModules(viewTypes, resources, product);
        addKnowBeforeYouGoModule(viewTypes, resources, product);
        addDescriptionTypes(viewTypes, product);
        addAccessibilityModule(viewTypes, resources, product);
        addLegalModule(viewTypes, resources, product);

        return viewTypes;
    }

    private static void addTimeModule(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, Product product) {
        addTitleAndDescriptionTypes(recyclerViewTypeList,
                resources.getString(R.string.detail_module_times),
                // TODO: To be removed once the service provides this details
                TIME_HARDCODE);
    }

    private static void addCuisineModule(LinkedList<RecyclerViewType> recyclerViewTypeList, Resources resources, Product product) {
        List<ProductAdvisement> advisementsCuisine = product.getProductAdvisementsByType(ProductAdvisement.CUISINE);
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

        List<ProductAdvisement> accessibilities = product.getProductAdvisementsByType(ProductAdvisement.ACCESSIBILITY);
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
        if (product.getProductType() == null || product.getProductType().getProductType() == null || product.getProductDuration() == null) {
            return;
        }
        if (product.getProductType().getProductType().equals(ProductType.DINING_TYPE)) {
            return;
        }
        if ((!product.isReservationRequired() && !product.isScheduable())) {
            return;
        }
        long duration = product.getProductDuration().getDurationInMinutes();
        if (duration > NO_DURATION) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.duration), getDurationFormatted(res, (int) duration));
        }
    }

    public static String getDurationFormatted(Resources resources, int duration) {
        String durationStr = "";
        if (duration >= MINUTES_IN_HOUR) {
            int hours = duration / MINUTES_IN_HOUR;
            int remainingMinutes = duration % MINUTES_IN_HOUR;
            if (hours > 0) {
                durationStr += resources.getQuantityString(R.plurals.product_hr, hours, hours);
            }
            if (remainingMinutes > 0) {
                durationStr += resources.getQuantityString(R.plurals.product_min, remainingMinutes, remainingMinutes);
            }
        } else {
            durationStr += resources.getQuantityString(R.plurals.product_min, duration, duration);
        }

        return durationStr;

    }

    private static void addAttireModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                        Product product) {
        List<ProductAdvisement> advisementsAttire = product.getProductAdvisementsByType(ProductAdvisement.ATTIRE);
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
        List<ProductAdvisement> advisementsKnow = product.getProductAdvisementsByType(ProductAdvisement.KNOW_BEFORE_YOU_GO);
        if (CollectionUtils.isEmpty(advisementsKnow)) {
            return;
        }
        ProductAdvisement advisement = advisementsKnow.get(0);
        if (advisement != null) {
            String title = advisement.getAdvisementTitle();
            if (!TextUtils.isEmpty(title)) {
                //FIXME hardcoded to match UI Design
                title = title.replace(TO_REPLACE, NEXT_LINE);
                addTitleAndDescriptionTypes(recyclerViewTypeList,
                        res.getString(R.string.discover_item_detail_know),
                        title);
            }
        }
    }

    private static void addLegalModule(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                       Product product) {
        List<ProductAdvisement> advisementsLegal = product.getProductAdvisementsByType(ProductAdvisement.LEGAL);
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

    public static void addTitleAndDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, final String title,
                                                   final String description) {
        recyclerViewTypeList.add(new TitleAndDescriptionViewType(title, description));
    }

    private static void addExpandableAccessibilityTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                                        final List<ProductAdvisement> accessibilities) {

        recyclerViewTypeList.add(new ExpandableAccesibilityViewType(res.getString(R.string.accessibility), accessibilities));
    }

    private static void addPricesModule(final List<RecyclerViewType> recyclerViewTypeList, List<Offering> offerings
            , @NonNull Resources res, Product product) {

        if (!product.isShopping() && !product.isDining()) {

            HashMap<String, String> map = new HashMap<>();
            float adultPrice = -1;
            float childPrice = -1;

            if (product.isSpa()) {
                adultPrice = product.getStartingFromPrice().getAdultPrice();
                childPrice = product.getStartingFromPrice().getChildPrice();
            } else {
                if (!CollectionUtils.isEmpty(offerings)) {
                    Collections.sort(offerings, (o1, o2) -> o1.compareByPrice(o2));
                    Offering offeringFirst = offerings.get(0);
                    adultPrice = offeringFirst.getPrice().getAdultPrice();
                    childPrice = offeringFirst.getPrice().getChildPrice();
                }
            }

            //Default behavior for SPA, SHOREX, ACTIVITIES, ENTERTAINMENT, GUEST_SERVICES
            if (adultPrice > 0) {
                map.put(res.getString(R.string.adult), res.getString(R.string.item_price, getPriceFormatted(adultPrice)));
            }

            if (product.isShorex()
                    || product.isGuestServices()) {
                if (childPrice > 0) {
                    map.put(res.getString(R.string.child), res.getString(R.string.item_price, getPriceFormatted(childPrice)));
                }
            }

            if (product.isActivities()
                    || product.isEntertainment()) {
                if (childPrice > 0) {
                    map.put(res.getString(R.string.child), res.getString(R.string.item_price, getPriceFormatted(childPrice)));
                } else if (childPrice == 0 && adultPrice > 0) {
                    map.put(res.getString(R.string.child), res.getString(R.string.price_free));
                }
            }

            if (!map.isEmpty()) {
                PricesFromViewType pricesFromViewType = new PricesFromViewType(res.getString(R.string.prices),
                        res.getString(R.string.prices_from), map, product);
                recyclerViewTypeList.add(pricesFromViewType);
            }
        }
    }

    private void addLongDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, Product product) {
        final String description = product.getProductLongDescription();
        if (TextUtils.isEmpty(description)) {
            return;
        }
        recyclerViewTypeList.add(new ExpandableDescriptionViewType(description));
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
