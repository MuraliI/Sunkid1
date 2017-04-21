package com.rcl.excalibur.mvp.presenter;

import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.activity.TriptychHomeActivity;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerHeader;
import com.rcl.excalibur.adapters.planner.abstractitem.PlannerProductItem;
import com.rcl.excalibur.domain.SailDateInfo;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSaildDateDbUseCase;
import com.rcl.excalibur.domain.interactor.GetSailingPreferenceUseCase;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.model.SailingInfoModel;
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

    static final String DAY_DEFAULT_VALUE = "1";
    private GetOfferingsDbUseCase useCase;
    private static final String HEADER_FORMAT = "H%s";
    private static final String ITEM_FORMAT = "I%s";

    private static final int HEADER_LIST_SIZE = 4;
    private static final long DELAY = 5000;

    private PlannerProductModelMapper mapper;

    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;

    private PlannerView view;

    private SparseArrayCompat<PlannerHeader> headerList;

    private int lastHeaderId = 0;
    private int lastItemId = 0;
    private String dayPreferences;

    public PlannerPresenter(PlannerView view,
                            GetOfferingsDbUseCase useCase,
                            PlannerProductModelMapper modelMapper,
                            GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                            GetSaildDateDbUseCase getSaildDateDbUseCase,
                            SailingInformationModelDataMapper sailingInformationModelDataMapper) {
        this.view = view;
        this.useCase = useCase;
        this.mapper = modelMapper;
        this.getSailingPreferenceUseCase = getSailingPreferenceUseCase;
        this.getSaildDateDbUseCase = getSaildDateDbUseCase;
        this.sailingInformationModelDataMapper = sailingInformationModelDataMapper;
    }

    public void init() {
        view.init();
        view.showProgressBar(true);
        view.initAnimation();
        view.initBottomSheetBehavior();

        createHeaderList();
    }

    public void getArrivingDebarkingInfo() {
        dayPreferences = getSailingPreferenceUseCase.getDay();
        int selectedDay = Integer.valueOf(dayPreferences == null ? DAY_DEFAULT_VALUE : dayPreferences);

        SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
        SailingInfoModel sailingInfoModel = sailingInformationModelDataMapper.transform(sailDateInfo);
        ItineraryModel itinerary = sailingInfoModel.getItinerary();
        if (itinerary == null) {
            view.addArrivingDebanrkingValues(null, selectedDay);
        } else {
            List<EventModel> events = itinerary.getEvents();
            view.addArrivingDebanrkingValues(events, selectedDay);
        }
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

    public void onServiceCallCompleted() {
        view.showProgressBar(false);
        //FIXME this is just mock data that is going to be replaced when we get the actual ship day.
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        SparseArrayCompat<List<PlannerProductModel>> plannerProducts = mapper.transform(useCase.getAllForDay(calendar.getTime()));
        List<AbstractFlexibleItem> items = addPlannerItems(plannerProducts.get(PlannerProductModelMapper.ALL_DAY_PRODUCT_LIST));
        if (!items.isEmpty()) {
            view.showAllDayLayout();
        }
        items.addAll(addPlannerItems(plannerProducts.get(PlannerProductModelMapper.TIMED_PRODUCT_LIST)));
        view.addPlannerItems(items);
        getArrivingDebarkingInfo();
        BaseActivity activity = view.getActivity();
        if (activity != null) {
            ((TriptychHomeActivity) activity).getShipLocationInfo();
        }
    }
}
