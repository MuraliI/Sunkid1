package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
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
import com.rcl.excalibur.fragments.PlannerFragment;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.mapper.SailingInformationModelDataMapper;
import com.rcl.excalibur.model.EventModel;
import com.rcl.excalibur.model.ItineraryModel;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.model.PortModel;
import com.rcl.excalibur.model.SailingInfoModel;
import com.rcl.excalibur.mvp.model.PlannerModel;
import com.rcl.excalibur.mvp.view.PlannerView;
import com.rcl.excalibur.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

import static com.rcl.excalibur.model.PlannerProductModel.ALL_DAY_HEADER;
import static com.rcl.excalibur.model.PlannerProductModel.GENERAL_HEADER;

public class PlannerPresenter {

    static final String DAY_DEFAULT_VALUE = "1";
    private GetOfferingsDbUseCase useCase;
    private static final String HEADER_FORMAT = "H%s";
    private static final String ITEM_FORMAT = "I%s";

    private static final int HEADER_LIST_SIZE = 4;

    private static final int FIRST_DAY = 1;
    private static final String ARRIVING_DEPARTING_SEPARATOR = "; ";
    private static final String PORT_TYPE_CRUISING = "CRUISING";

    private PlannerProductModelMapper mapper;

    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;

    private PlannerView view;
    private PlannerModel model;

    private SparseArrayCompat<PlannerHeader> headerList;

    private int lastHeaderId = 0;
    private int lastItemId = 0;
    private String dayPreferences;

    public PlannerPresenter(PlannerView view,
                            GetOfferingsDbUseCase useCase,
                            PlannerProductModelMapper modelMapper,
                            GetSailingPreferenceUseCase getSailingPreferenceUseCase,
                            GetSaildDateDbUseCase getSaildDateDbUseCase,
                            SailingInformationModelDataMapper sailingInformationModelDataMapper,
                            PlannerModel model) {
        this.view = view;
        this.model = model;
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
        view.setViewObserver(new OnScrolledObserver(this));
        createHeaderList();
    }

    private void createHeaderList() {
        headerList = new SparseArrayCompat<>(HEADER_LIST_SIZE);
        headerList.append(GENERAL_HEADER, createPlannerHeader(R.string.empty_string));
        headerList.append(ALL_DAY_HEADER, createPlannerHeader(R.string.planner_title_all_day));
    }

    private List<AbstractFlexibleItem> addPlannerItems(final List<PlannerProductModel> plannerProductModels) {
        List<AbstractFlexibleItem> plannerItems = new ArrayList<>();
        /*for (PlannerProductModel plannerProductModel : plannerProductModels) {
            plannerItems.add(createPlannerItem(plannerProductModel, headerList.get(plannerProductModel.getHeaderItBelongs())));
        }*/

        for (int i = 0; i < plannerProductModels.size(); i++) {
            PlannerProductModel plannerProductModel = plannerProductModels.get(i);

            if (!plannerProductModel.isAllDayProduct()) {
                PlannerProductModel previousPlannerProductModel = i > 0 ? plannerProductModels.get(i - 1) : null;
                if (previousPlannerProductModel == null
                        || plannerProductModel.isStartHourDifferent(previousPlannerProductModel)) {
                    String startHourText = null;
                    final PlannerFragment fragment = view.getFragment();
                    if (fragment != null) {
                        startHourText = DateUtils.getDateHour(
                                plannerProductModel.getStartDate().getTime(), fragment.getResources());
                    }
                    plannerProductModel.setStartHourText(startHourText);
                }
            }

            plannerItems.add(createPlannerItem(plannerProductModel, headerList.get(plannerProductModel.getHeaderItBelongs())));
        }
        return plannerItems;
    }

    private Pair<List<Integer>, List<AbstractFlexibleItem>> getHiddenItems(final List<AbstractFlexibleItem> flexibleItems) {
        List<Integer> hiddenIndex = new ArrayList<>();

        List<AbstractFlexibleItem> tmpVisibleItems = new ArrayList<>(flexibleItems);
        List<AbstractFlexibleItem> hiddenItems = new ArrayList<>();
        for (int i = 0; i < tmpVisibleItems.size(); i++) {
            if (tmpVisibleItems.get(i) instanceof PlannerProductItem) {
                PlannerProductItem plannerProductItem = (PlannerProductItem) tmpVisibleItems.get(i);
                PlannerProductModel plannerProductModel = plannerProductItem.getPlannerProductModel();

                // TODO: Delete this if, only for testing
                if (i <= 10 && i % 2 == 0) {
                    plannerProductModel.setFeatured(true);
                }

                if (!plannerProductModel.isFeatured()) {
                    hiddenIndex.add(i);
                    plannerProductItem.setIndexToBeAdded(i);
                    hiddenItems.add(plannerProductItem);
                    flexibleItems.remove(plannerProductItem);
                }
            }
        }
        return new Pair<>(hiddenIndex, hiddenItems);
    }

    private PlannerHeader createPlannerHeader(@StringRes int textRes) {
        BaseActivity activity = view.getActivity();
        if (activity == null) {
            return null;
        }
        PlannerHeader plannerHeader = new PlannerHeader(String.format(HEADER_FORMAT, ++lastHeaderId));
        plannerHeader.setTitle(textRes);
        return plannerHeader;
    }

    private PlannerProductItem createPlannerItem(PlannerProductModel plannerProductModel, PlannerHeader plannerHeader) {
        // TODO: mock
        String timeLabel = new Random().nextBoolean() ? null : "10 AM";
        PlannerProductItem plannerProductItem = new PlannerProductItem(String.format(ITEM_FORMAT, ++lastItemId), timeLabel, plannerHeader);
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
        calendar.set(Calendar.DAY_OF_MONTH, 5);
        calendar.set(Calendar.MONTH, Calendar.MAY);

        SparseArrayCompat<List<PlannerProductModel>> plannerProducts = mapper.transform(useCase.getAllForDay(calendar.getTime()));
        List<AbstractFlexibleItem> visibleItems = addPlannerItems(plannerProducts.get(PlannerProductModelMapper.TIMED_PRODUCT_LIST));
        visibleItems.addAll(addPlannerItems(plannerProducts.get(PlannerProductModelMapper.ALL_DAY_PRODUCT_LIST)));
        //Pair<List<Integer>, List<AbstractFlexibleItem>> hiddenItems = getHiddenItems(visibleItems);
        view.addPlannerItems(visibleItems);
        //view.addPlannerItems(visibleItems, hiddenItems);

        getArrivingDisembarkingInfo();
        BaseActivity activity = view.getActivity();
        if (activity != null) {
            ((TriptychHomeActivity) activity).getShipLocationInfo();
        }
    }

    public void getArrivingDisembarkingInfo() {
        dayPreferences = getSailingPreferenceUseCase.getDay();
        int selectedDay = Integer.valueOf(dayPreferences == null ? DAY_DEFAULT_VALUE : dayPreferences);

        SailDateInfo sailDateInfo = getSaildDateDbUseCase.get();
        SailingInfoModel sailingInfoModel = sailingInformationModelDataMapper.transform(sailDateInfo);
        ItineraryModel itinerary = sailingInfoModel.getItinerary();
        if (itinerary != null) {
            List<EventModel> events = itinerary.getEvents();
            addArrivingDisembarkingValues(events, selectedDay);
        }
    }

    private void addArrivingDisembarkingValues(List<EventModel> events, int day) {
        Activity activity = view.getActivity();
        if (activity == null) {
            return;
        }

        if (events != null) {
            Pair<String, Integer> stringIntegerPair = getArrivalDisembarkingDescription(events, day);
            view.setTextCompoundDrawableDayInfo(stringIntegerPair.first, stringIntegerPair.second);
        }
    }

    private Pair<String, Integer> getArrivalDisembarkingDescription(List<EventModel> events, int day) {
        Resources resources = view.getActivity().getResources();
        int drawable;
        PortModel sailPort = PortModel.getSailPortByDay(events, day);
        drawable = R.drawable.ic_excursions;

        if (day == FIRST_DAY) {
            return new Pair<>(resources.getString(R.string.departing_at) + model.getTimeFormat(
                    Integer.valueOf(sailPort.getDepartureTime())), drawable);
        } else if (day == events.size()) {
            return new Pair<>(resources.getString(R.string.arriving_at) + model.getTimeFormat(Integer.valueOf(sailPort.getArrivalTime())),
                    drawable);
        } else if (PORT_TYPE_CRUISING.equals(sailPort.getPortType())) {
            sailPort = getPortTypeNextDay(events, day, sailPort);
            return new Pair<>(resources.getString(R.string.next_port) + sailPort.getPortName(), drawable);
        } else {
            return new Pair<>(resources.getString(R.string.arriving_at) + model.getTimeFormat(Integer.valueOf(sailPort.getArrivalTime()))
                    + ARRIVING_DEPARTING_SEPARATOR + resources.getString(R.string.departing_at)
                    + model.getTimeFormat(Integer.valueOf(sailPort.getDepartureTime())), drawable);
        }
    }

    private PortModel getPortTypeNextDay(List<EventModel> events, int day, PortModel sailPort) {
        int nextDay = day + 1;
        if (nextDay <= events.size()) {
            sailPort = PortModel.getSailPortByDay(events, nextDay);
            if (PORT_TYPE_CRUISING.equals(sailPort.getPortType())) {
                sailPort = getPortTypeNextDay(events, nextDay, sailPort);
            }
        }
        return sailPort;
    }

    public static class OnScrolledObserver extends DefaultPresentObserver<PlannerProductItem, PlannerPresenter> {

        public OnScrolledObserver(PlannerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(PlannerProductItem value) {

        }
    }


}
