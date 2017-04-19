package com.rcl.excalibur.mapper;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.utils.PresentationDateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        model.setOperatinghours(calculateOperatingHours(allDayStartDate, allDayEndDate));
        model.setStartDate(allDayStartDate);
        model.setEndDate(allDayEndDate);
        model.setAllDayProduct(true);

        return model;
    }

    private PlannerProductModel createNormalPlannerProductModel(Product product, Offering offering) {
        PlannerProductModel model = (PlannerProductModel) productInformationMapper.transform(product);

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(offering.getDate());

        model.setStartDate(startDate);
        model.setEndDate(calculateEndDate(offering.getDate(), product.getProductDuration()));
        model.setOperatinghours(calculateOperatingHours(model.getStartDate(), model.getEndDate()));

        return model;
    }

    private String calculateOperatingHours(Calendar startDate, Calendar endDate) {
        return resources.getString(R.string.planner_operating_hours
                , PresentationDateUtils.getDateTime(startDate.getTime(), resources)
                , PresentationDateUtils.getDateTime(endDate.getTime(), resources));
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
