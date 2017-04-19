package com.rcl.excalibur.mvp.presenter;

import android.os.Handler;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerHeader;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.mvp.view.PlannerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import static com.rcl.excalibur.model.PlannerProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_MORNING;

public class PlannerPresenter {

    private static final String HEADER_FORMAT = "H%s";
    private static final String ITEM_FORMAT = "I%s";
    private static final int HEADER_LIST_SIZE = 4;

    private static final long DELAY = 5000;

    private GetOfferingsDbUseCase useCase;
    private PlannerProductModelMapper mapper;
    private PlannerView view;

    private SparseArrayCompat<PlannerHeader> headerList;

    private int lastHeaderId = 0;
    private int lastItemId = 0;

    public PlannerPresenter(PlannerView view, GetOfferingsDbUseCase useCase, PlannerProductModelMapper modelMapper) {
        this.view = view;
        this.useCase = useCase;
        this.mapper = modelMapper;
    }

    public void init() {
        view.init();
        view.initAnimation();
        view.initBottomSheetBehavior();

        createHeaderList();

        //FIXME this is just mock data that is going to be replaced when we get the actual ship day.
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        new Handler().postDelayed(() -> {
            SparseArrayCompat<List<PlannerProductModel>> plannerProducts = mapper.transform(useCase.getAllForDay(calendar.getTime()));
            List<AbstractFlexibleItem> items = addPlannerItems(plannerProducts.get(PlannerProductModelMapper.ALL_DAY_PRODUCT_LIST));
            if (!items.isEmpty()) {
                view.showAllDayLayout();
            }
            items.addAll(addPlannerItems(plannerProducts.get(PlannerProductModelMapper.TIMED_PRODUCT_LIST)));
            view.addPlannerItems(items);
        }, DELAY);
    }

    private void createHeaderList() {
        headerList = new SparseArrayCompat<>(HEADER_LIST_SIZE);
        headerList.append(STATE_MORNING, createPlannerHeader(R.string.title_morning));
        headerList.append(STATE_AFTERNOON, createPlannerHeader(R.string.title_afternoon));
        headerList.append(STATE_EVENING, createPlannerHeader(R.string.title_evening));
        headerList.append(STATE_LATE_NIGHT, createPlannerHeader(R.string.title_late_night));
    }

    private List<AbstractFlexibleItem> addPlannerItems(final List<PlannerProductModel> plannerProductModels) {
        List<AbstractFlexibleItem> plannerItems = new ArrayList<>();
        for (PlannerProductModel plannerProductModel : plannerProductModels) {
            plannerItems.add(createPlannerItem(plannerProductModel, headerList.get(plannerProductModel.getState())));
        }
        return plannerItems;
    }

    private PlannerHeader createPlannerHeader(int textRes) {
        BaseActivity activity = view.getActivity();
        if (activity == null) {
            return null;
        }
        PlannerHeader plannerHeader = new PlannerHeader(String.format(HEADER_FORMAT, ++lastHeaderId));
        plannerHeader.setTitle(activity.getString(textRes));
        return plannerHeader;
    }

    private PlannerProductItem createPlannerItem(PlannerProductModel plannerProductModel, PlannerHeader plannerHeader) {
        PlannerProductItem plannerProductItem = new PlannerProductItem(String.format(ITEM_FORMAT, ++lastItemId), plannerHeader);
        plannerProductItem.setPlannerProductModel(plannerProductModel);
        return plannerProductItem;
    }

    public void onItemClick(int position) {
        view.onItemClick(position);
    }
}
