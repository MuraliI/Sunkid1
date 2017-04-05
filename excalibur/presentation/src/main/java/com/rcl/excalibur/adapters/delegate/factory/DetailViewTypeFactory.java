package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductActivityLevel;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.domain.SellingPrice;
import com.rcl.excalibur.mapper.ProductModelDataMapper;
import com.rcl.excalibur.model.ProductModel;
import com.rcl.excalibur.mapper.ProductInformationMapper;
import com.rcl.excalibur.utils.ProductModelProvider;
import com.rcl.excalibur.utils.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class DetailViewTypeFactory {

    private static final int NO_DURATION = 0;

    private DetailViewTypeFactory() {
    }

    public static List<RecyclerViewType> getAdaptersAndViewTypesForModel(Product product, Resources resources) {
        LinkedList<RecyclerViewType> viewTypes = new LinkedList<>();
        addHeroSectionHeader(product, viewTypes);

        //FIXME refactor this code to transform product model in each method and create a better model
        ProductModel model = new ProductModelDataMapper().transform(product);
        addMakeReservation(viewTypes, resources, model);
        addAdvisements(viewTypes, resources, model);
        if (model.getDuration() > NO_DURATION) {
            addProductDurationTypes(viewTypes, resources, model);
            addProductDurationTypes(viewTypes, resources, model);
            addProductDurationTypes(viewTypes, resources, model);
            addProductDurationTypes(viewTypes, resources, model);
            addProductDurationTypes(viewTypes, resources, model);
            addProductDurationTypes(viewTypes, resources, model);
        }

        return viewTypes;
    }

    private static void addHeroSectionHeader(Product product, LinkedList<RecyclerViewType> viewTypes) {
        viewTypes.add(new ProductInformationMapper().transform(product));
    }

    private static boolean isHoursEmpty(String value) {
        return TextUtils.isEmpty(value) || "0".equals(value);
    }

    private static void addTitleAndDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, final String title,
                                                    final String description) {
        recyclerViewTypeList.add(new TitleAndDescriptionViewType(title, description));
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

    private static void addProductDurationTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res,
                                                ProductModel product) {
        if (product == null) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(R.string.duration), product.getDurationFormatted(res));
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

    private void addRestrictionsType(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources, @StringRes int title,
                                     Product product) {
        final List<ProductRestriction> restrictions = product.getRestrictions();
        if (CollectionUtils.isEmpty(restrictions)) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(title),
                restrictions.get(0).getRestrictionDisplayText());
    }

    private void addProductLevel(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources, Product product) {
        final ProductActivityLevel productActivityLevel = product.getActivityLevel();
        if (productActivityLevel == null) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.activity_level),
                productActivityLevel.getActivityLevelTitle());
    }

    private static void addAdvisements(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources,
                                       ProductModel product) {
        // FIXME: Obtain products from database
        product = ProductModelProvider.productModelMap.get("1");
        LinkedHashMap<String, String> advisements = product.getAdvisements();

        for (Map.Entry<String, String> entry : advisements.entrySet()) {
            String advisementTitle = entry.getKey();
            String advisementDescription = entry.getValue();
            addTitleAndDescriptionTypes(recyclerViewTypeList, advisementTitle,
                    advisementDescription);
        }
    }

    private static void addMakeReservation(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources, ProductModel product) {
        // FIXME: Obtain products from database
        if (!TextUtils.isEmpty(product.getReservationInformation())) {
            addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.discover_item_detail_make_a_reservation),
                    product.getReservationInformation());
        }
    }

}
