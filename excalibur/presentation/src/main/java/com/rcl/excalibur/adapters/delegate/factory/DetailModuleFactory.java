package com.rcl.excalibur.adapters.delegate.factory;


import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;

import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.base.DelegateAdapter;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.ExpandableDescriptionViewType;
import com.rcl.excalibur.adapters.viewtype.PricesFromViewType;
import com.rcl.excalibur.adapters.viewtype.StandardTimesViewType;
import com.rcl.excalibur.adapters.viewtype.TitleAndDescriptionViewType;
import com.rcl.excalibur.data.utils.CollectionUtils;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductActivityLevel;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.ProductLocation;
import com.rcl.excalibur.domain.ProductRestriction;
import com.rcl.excalibur.domain.SellingPrice;
import com.rcl.excalibur.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class DetailModuleFactory {
    public Product product;

    protected static boolean isHoursEmpty(String value) {
        return TextUtils.isEmpty(value) || "0".equals(value);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public abstract SparseArrayCompat<DelegateAdapter> getDelegateAdapterArray();

    public abstract List<RecyclerViewType> getListOfDetailViewTypes(Resources resources);

    void addTitleAndDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList, final String title, final String description) {
        recyclerViewTypeList.add(new TitleAndDescriptionViewType(title, description));
    }

    void addPriceFromTypes(final List<RecyclerViewType> recyclerViewTypeList) {
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

    void addProductDurationTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources res, @StringRes int title) {
        final ProductDuration productDuration = product.getProductDuration();
        if (productDuration == null) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, res.getString(title),
                res.getString(R.string.mins, productDuration.getDurationInMinutes()));
    }

    void addLongDescriptionTypes(final List<RecyclerViewType> recyclerViewTypeList) {
        final String description = product.getProductLongDescription();
        if (TextUtils.isEmpty(description)) {
            return;
        }
        recyclerViewTypeList.add(new ExpandableDescriptionViewType(description));
    }

    void addProductLocationTypes(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources) {
        final ProductLocation productLocation = product.getProductLocation();
        if (productLocation == null || isHoursEmpty(productLocation.getOperatingHoursEnd())) {
            return;
        }
        ArrayList<String[]> arrayListTimes = new ArrayList<>();
        arrayListTimes.add(new String[]{resources.getString(R.string.day_1), product.getTimeFrame()});
        recyclerViewTypeList.add(new StandardTimesViewType(resources.getString(R.string.operating_hours), arrayListTimes));
    }

    void addRestrictionsType(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources, @StringRes int title) {
        final List<ProductRestriction> restrictions = product.getRestrictions();
        if (CollectionUtils.isEmpty(restrictions)) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(title),
                restrictions.get(0).getRestrictionDisplayText());
    }

    void addProductLevel(final List<RecyclerViewType> recyclerViewTypeList, @NonNull Resources resources) {
        final ProductActivityLevel productActivityLevel = product.getActivityLevel();
        if (productActivityLevel == null) {
            return;
        }
        addTitleAndDescriptionTypes(recyclerViewTypeList, resources.getString(R.string.activity_level),
                productActivityLevel.getActivityLevelTitle());
    }

}
