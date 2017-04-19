package com.rcl.excalibur.mapper;

import android.content.res.Resources;
import android.support.annotation.DrawableRes;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.utils.PresentationDateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rcl.excalibur.utils.CategoryUtils.ACTIVITIES;
import static com.rcl.excalibur.utils.CategoryUtils.DINING;
import static com.rcl.excalibur.utils.CategoryUtils.ENTERTAINMENT;
import static com.rcl.excalibur.utils.CategoryUtils.SHOREX;
import static com.rcl.excalibur.utils.CategoryUtils.SPA;


public class PlannerProductModelMapper {

    public static final int ALL_DAY_PRODUCT_LIST = 0;
    public static final int TIMED_PRODUCT_LIST = 1;

    private static final int ALL_DAY_OFFERING_LIMIT = 10;
    private static final int DEFAULT_DURATION = 0;

    private BaseProductInformationMapper productInformationMapper;
    private Resources resources;

    public PlannerProductModelMapper(Resources resources) {
        this.resources = resources;
        productInformationMapper = new PlannerProductSubMapper();
    }

    public SparseArrayCompat<List<PlannerProductModel>> transform(List<Offering> input) {
        List<PlannerProductModel> timedProductList = new ArrayList<>();
        List<PlannerProductModel> allDayProducts = new ArrayList<>();

        SparseArrayCompat<List<PlannerProductModel>> productList = new SparseArrayCompat<>(2);
        productList.append(ALL_DAY_PRODUCT_LIST, allDayProducts);
        productList.append(TIMED_PRODUCT_LIST, timedProductList);

        Map<Product, List<Offering>> productOfferingMap = createOfferingProductMap(input);

        for (Map.Entry<Product, List<Offering>> entry : productOfferingMap.entrySet()) {
            Product product = entry.getKey();
            List<Offering> productOfferingList = entry.getValue();

            if (productOfferingList.size() > ALL_DAY_OFFERING_LIMIT) {
                allDayProducts.add(createAllDayProductModel(product, productOfferingList));
            } else {
                for (Offering offering : productOfferingList) {
                    timedProductList.add(createNormalPlannerProductModel(product, offering));
                }
            }
        }

        Collections.sort(allDayProducts);
        Collections.sort(timedProductList);

        return productList;
    }

    private Map<Product, List<Offering>> createOfferingProductMap(List<Offering> input) {
        Map<Product, List<Offering>> productOfferingMap = new HashMap<>();

        for (Offering offering : input) {
            List<Offering> offeringList = productOfferingMap.get(offering.getProduct());

            if (offeringList == null) {
                offeringList = new ArrayList<>();
                productOfferingMap.put(offering.getProduct(), offeringList);
            }

            offeringList.add(offering);
        }

        return productOfferingMap;
    }

    private PlannerProductModel createAllDayProductModel(Product product, List<Offering> offeringList) {
        Collections.sort(offeringList);

        PlannerProductModel model = (PlannerProductModel) productInformationMapper.transform(product);

        Calendar allDayStartDate = Calendar.getInstance();
        allDayStartDate.setTime(offeringList.get(0).getDate());

        Calendar allDayEndDate = Calendar.getInstance();
        allDayEndDate.setTime(offeringList.get(offeringList.size() - 1).getDate());

        model.setOperatingHours(calculateOperatingHours(allDayStartDate, allDayEndDate));
        model.setStartDate(allDayStartDate);
        model.setEndDate(allDayEndDate);
        model.setAllDayProduct(true);
        model.setResourceIdCategoryIcon(getCategoryIcon(model.getProductType()));
        model.setVenue(resources.getString(R.string.deck_label) + ConstantsUtil.WHITE_SPACE + model.getVenue());
        return model;
    }

    private PlannerProductModel createNormalPlannerProductModel(Product product, Offering offering) {
        PlannerProductModel model = (PlannerProductModel) productInformationMapper.transform(product);

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(offering.getDate());

        model.setStartDate(startDate);
        model.setEndDate(calculateEndDate(offering.getDate(), product.getProductDuration()));
        model.setOperatingHours(calculateOperatingHours(model.getStartDate(), model.getEndDate()));
        model.setResourceIdCategoryIcon(getCategoryIcon(model.getProductType()));
        model.setLocation(resources.getString(R.string.deck_label) + ConstantsUtil.WHITE_SPACE + model.getLocation());
        return model;
    }

    @DrawableRes
    private int getCategoryIcon(String productType) {
        switch (productType) {
            case ACTIVITIES:
                return R.drawable.icon_services_color;
            case DINING:
                return R.drawable.icon_dining_color;
            case ENTERTAINMENT:
                return R.drawable.icon_entertainment_color;
            case SHOREX:
                return R.drawable.icon_shops_color;
            case SPA:
                return R.drawable.icon_spa_color;
            default:
                return R.drawable.icon_excrusions_color;
        }
    }

    private String calculateOperatingHours(Calendar startDate, Calendar endDate) {
        return resources.getString(R.string.planner_operating_hours,
                PresentationDateUtils.getDateTime(startDate.getTime(), resources),
                PresentationDateUtils.getDateTime(endDate.getTime(), resources));
    }

    private Calendar calculateEndDate(Date starTime, ProductDuration duration) {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(starTime);
        endDate.add(Calendar.MINUTE, duration != null ? (int) duration.getDurationInMinutes() : DEFAULT_DURATION);
        return endDate;
    }

    private class PlannerProductSubMapper extends BaseProductInformationMapper<PlannerProductModel> {

        @Override
        protected PlannerProductModel getProductInformationModel() {
            return new PlannerProductModel();
        }
    }
}
