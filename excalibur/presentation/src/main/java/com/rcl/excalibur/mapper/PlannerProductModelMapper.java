package com.rcl.excalibur.mapper;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class PlannerProductModelMapper {

    public static final int ALL_DAY_PRODUCT_LIST = 0;
    public static final int TIMED_PRODUCT_LIST = 1;

    private static final int ALL_DAY_OFFERING_LIMIT = 10;
    private static final int DEFAULT_DURATION = 0;

    private BaseProductInformationMapper informationMapper;
    private Resources resources;

    public PlannerProductModelMapper(Resources resources) {
        informationMapper = new PlannerProductMapper();
        this.resources = resources;
    }

    public SparseArrayCompat<List<PlannerProductModel>> transform(List<Product> input) {
        //TODO implement the planner product model logic
        List<PlannerProductModel> timedProductList = new ArrayList<>();
        List<PlannerProductModel> allDayProducts = new ArrayList<>();

        SparseArrayCompat<List<PlannerProductModel>> productList = new SparseArrayCompat<>(2);
        productList.append(ALL_DAY_PRODUCT_LIST, allDayProducts);
        productList.append(TIMED_PRODUCT_LIST, timedProductList);

        for (Product product : input) {
            if (product.getOfferings().size() > ALL_DAY_OFFERING_LIMIT) {
                PlannerProductModel model = (PlannerProductModel) informationMapper.transform(product);
                model.setOperatinghours(resources.getString(R.string.planner_all_day_item));
                model.setAllDayProduct(true);
                allDayProducts.add(model);
            } else {
                for (Offering offering : product.getOfferings()) {
                    PlannerProductModel model = (PlannerProductModel) informationMapper.transform(product);

                    Calendar startDate = Calendar.getInstance();
                    startDate.setTime(offering.getDate());

                    model.setStartDate(startDate);
                    model.setEndDate(calculateEndDate(offering.getDate(), product.getProductDuration()));
                    model.setOperatinghours(calculateOperatingHours(model.getStartDate(), model.getEndDate()));

                    timedProductList.add(model);
                }
            }
        }

        Collections.sort(allDayProducts, (model1, model2) -> model1.getProductName().compareTo(model2.getProductName()));
        Collections.sort(timedProductList);

        return productList;
    }

    private String calculateOperatingHours(Calendar startDate, Calendar endDate) {
        return resources.getString(R.string.planner_operating_hours
                , DateUtils.getDateTime(startDate.getTime(), resources)
                , DateUtils.getDateTime(endDate.getTime(), resources));
    }

    private Calendar calculateEndDate(Date starTime, ProductDuration duration) {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(starTime);
        endDate.add(Calendar.MINUTE, duration != null ? (int) duration.getDurationInMinutes() : DEFAULT_DURATION);
        return endDate;
    }
}
