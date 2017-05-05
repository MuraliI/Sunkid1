package com.rcl.excalibur.mapper;

import android.content.res.Resources;
import android.support.v4.util.SparseArrayCompat;

import com.google.gson.internal.LinkedHashTreeMap;
import com.rcl.excalibur.R;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.ProductDuration;
import com.rcl.excalibur.domain.utils.ConstantsUtil;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.utils.CategoryUtils;
import com.rcl.excalibur.utils.PresentationDateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class PlannerProductModelMapper {

    public static final int ALL_DAY_PRODUCT_LIST = 0;
    public static final int TIMED_PRODUCT_LIST = 1;

    private static final int ALL_DAY_DURATION_IN_MINUTES = 300;
    private static final int DEFAULT_DURATION = 0;
    private static final int FIRST_OFFERING = 0;
    private static final int INITIAL_CAPACITY = 2;

    private BaseProductInformationMapper productInformationMapper;
    private Resources resources;

    public PlannerProductModelMapper(Resources resources) {
        this.resources = resources;
        productInformationMapper = new PlannerProductSubMapper();
    }

    public SparseArrayCompat<List<PlannerProductModel>> transform(List<Offering> input) {
        List<PlannerProductModel> allDayProducts = new ArrayList<>();
        List<PlannerProductModel> timedProduct = new ArrayList<>();

        SparseArrayCompat<List<PlannerProductModel>> productList = new SparseArrayCompat<>(INITIAL_CAPACITY);
        productList.append(ALL_DAY_PRODUCT_LIST, allDayProducts);
        productList.append(TIMED_PRODUCT_LIST, timedProduct);

        Map<String, List<Offering>> productOfferingMap = createOfferingProductMap(input);
        for (Map.Entry<String, List<Offering>> entry : productOfferingMap.entrySet()) {
            List<Offering> productOfferingList = entry.getValue();

            long productDuration = productOfferingList.get(FIRST_OFFERING).getProduct()
                    .getProductDuration().getDurationInMinutes();
            if (productDuration >= ALL_DAY_DURATION_IN_MINUTES) {
                allDayProducts.addAll(createPlannerProductModelList(productOfferingList, true));
            } else {
                timedProduct.addAll(createPlannerProductModelList(productOfferingList, false));
            }
        }

        Collections.sort(allDayProducts);
        Collections.sort(timedProduct);

        return productList;
    }

    private Map<String, List<Offering>> createOfferingProductMap(List<Offering> input) {
        Map<String, List<Offering>> productOfferingMap = new LinkedHashTreeMap<>();

        for (Offering offering : input) {
            Product product = offering.getProduct();
            if (false) { // TODO: Replace with !product.isHighlighted() when service provides the flags
                continue;
            }

            String productId = product.getProductId();
            List<Offering> offeringList = productOfferingMap.get(productId);
            if (offeringList == null) {
                offeringList = new ArrayList<>();
            }
            offeringList.add(offering);
            productOfferingMap.put(productId, offeringList);
        }

        return productOfferingMap;
    }

    @SuppressWarnings("Convert2streamapi")
    private List<PlannerProductModel> createPlannerProductModelList(List<Offering> productOfferingList, boolean isAllDayProduct) {
        List<PlannerProductModel> plannerProductModelList = new ArrayList<>();
        for (Offering offering : productOfferingList) {
            plannerProductModelList.add(createPlannerProductModel(offering, isAllDayProduct));
        }
        return plannerProductModelList;
    }

    private PlannerProductModel createPlannerProductModel(Offering offering, boolean isAllDayProduct) {
        Product product = offering.getProduct();

        PlannerProductModel model = (PlannerProductModel) productInformationMapper.transform(product);

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(offering.getDate());

        model.setStartDate(startDate);
        model.setEndDate(calculateEndDate(offering.getDate(), product.getProductDuration()));
        model.setOperatingHours(calculateOperatingHours(model.getStartDate(), model.getEndDate()));
        model.setResourceIdCategoryIcon(CategoryUtils.getCategoryIcon(model.getProductType()));

        String location = model.getLocation();
        if (location != null && !location.isEmpty()) {
            model.setLocation(resources.getString(R.string.deck_label, model.getLocation()));
        } else {
            model.setLocation(ConstantsUtil.EMPTY);
        }

        model.setFeatured(product.isFeatured());
        model.setHighlighted(product.isHighlighted());
        model.setAllDayProduct(isAllDayProduct);

        return model;
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
