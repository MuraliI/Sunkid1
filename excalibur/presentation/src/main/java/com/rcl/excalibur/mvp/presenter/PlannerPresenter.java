package com.rcl.excalibur.mvp.presenter;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.util.Pair;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;

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
import com.rcl.excalibur.mvp.presenter.rx.DefaultPresentObserver;
import com.rcl.excalibur.mvp.view.PlannerView;
import com.rcl.excalibur.utils.DateUtils;
import com.rcl.excalibur.utils.RoundedImageView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flexibleadapter.items.ISectionable;

import static com.rcl.excalibur.model.PlannerProductModel.ALL_DAY_HEADER;
import static com.rcl.excalibur.model.PlannerProductModel.GENERAL_HEADER;

public class PlannerPresenter {

    static final String DAY_DEFAULT_VALUE = "1";
    private static final String HEADER_FORMAT = "H%s";
    private static final String ITEM_FORMAT = "I%s";
    private static final String ARRIVING_DEPARTING_SEPARATOR = "; ";
    private static final String PORT_TYPE_CRUISING = "CRUISING";

    private static final float OFFSET_OVER_95_PERCENT = 0.95f;
    private static final float MAX_SLIDE_OFFSET = 1.0f;

    private static final int HEADER_LIST_SIZE = 4;
    private static final int FIRST_DAY = 1;
    private static final int NO_PEEK_HEIGHT = 0;
    private static final int DELAY_MILLIS_COLLAPSE = 200;
    private static final int DELAY_MILLIS_SCROLL = 100;
    private static final int HOUR_SIX = 6;
    private static final int HOUR_TWELVE = 12;
    private static final int HOUR_SEVENTEEN = 17;
    private static final int HOUR_TWENTY_THREE = 23;

    private PlannerProductModelMapper mapper;

    private GetSailingPreferenceUseCase getSailingPreferenceUseCase;
    private GetSaildDateDbUseCase getSaildDateDbUseCase;
    private GetOfferingsDbUseCase useCase;
    private SailingInformationModelDataMapper sailingInformationModelDataMapper;

    private PlannerView view;
    private PlannerModel model;

    private SparseArrayCompat<PlannerHeader> headerList;
    private List<IFlexible> hiddenGeneralItems;
    private List<IFlexible> hiddenAllDayItems;

    private Handler handler;

    private boolean bottomSheetIsSliding;
    private boolean isExpanded;

    @StringRes private int currentPartOfDayResource;
    private int lastHeaderId = 0;
    private int lastItemId = 0;

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

    public void init(boolean serviceAlreadyCompleted) {
        view.init();
        view.showProgressBar(true);
        view.initAnimation();
        view.initBottomSheetBehavior();
        view.setViewObserver(new OnScrolledObserver(this));
        view.setExpandCollapseObserver(new OnExpandCollapseObserver(this));
        view.setAttachedObserver(new OnChildViewAttachedObserver(this));
        view.setSlideObserver(new OnBottomSheetSlideObserver(this));
        view.setStateChangeObserver(new OnBottomSheetStateChange(this));

        handler = new Handler();
        hiddenGeneralItems = new ArrayList<>();
        hiddenAllDayItems = new ArrayList<>();

        createHeaderList();
        if (serviceAlreadyCompleted) {
            onServiceCallCompleted();
        }
    }

    private void createHeaderList() {
        headerList = new SparseArrayCompat<>(HEADER_LIST_SIZE);
        headerList.append(GENERAL_HEADER, createPlannerHeader(R.string.title_morning, false));
        headerList.append(ALL_DAY_HEADER, createPlannerHeader(R.string.planner_title_all_day, true));
    }

    private List<IFlexible> addPlannerItems(final List<PlannerProductModel> plannerProductModels) {
        List<IFlexible> plannerItems = new ArrayList<>();
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

            // TODO: Delete this when services provides the flag
            if (i <= 6 && i % 2 == 0) {
                plannerProductModel.setFeatured(true);
            } else {
                plannerProductModel.setFeatured(false);
            }

            PlannerProductItem plannerProductItem = createPlannerItem(plannerProductModel,
                    headerList.get(plannerProductModel.getHeaderItBelongs()));

            if (!plannerProductModel.isFeatured()) {
                if (plannerProductModel.isAllDayProduct()) {
                    hiddenAllDayItems.add(plannerProductItem);
                } else {
                    hiddenGeneralItems.add(plannerProductItem);
                }
                continue;
            }

            plannerItems.add(plannerProductItem);
        }
        return plannerItems;
    }

    private PlannerHeader createPlannerHeader(@StringRes int textRes, boolean isAllDayHeader) {
        BaseActivity activity = view.getActivity();
        if (activity == null) {
            return null;
        }
        PlannerHeader plannerHeader = new PlannerHeader(String.format(HEADER_FORMAT, ++lastHeaderId));
        plannerHeader.setTitle(textRes);
        plannerHeader.setAllDayHeader(isAllDayHeader);
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
        calendar.set(Calendar.DAY_OF_MONTH, 7);
        calendar.set(Calendar.MONTH, Calendar.JULY);

        SparseArrayCompat<List<PlannerProductModel>> plannerProducts = mapper.transform(useCase.getAllForDay(calendar.getTime()));
        List<IFlexible> visibleItems = addPlannerItems(plannerProducts.get(PlannerProductModelMapper.TIMED_PRODUCT_LIST));
        visibleItems.addAll(addPlannerItems(plannerProducts.get(PlannerProductModelMapper.ALL_DAY_PRODUCT_LIST)));
        view.addPlannerItems(visibleItems);

        getArrivingDisembarkingInfo();
        BaseActivity activity = view.getActivity();
        if (activity != null) {
            ((TriptychHomeActivity) activity).getShipLocationInfo();
        }
    }

    public void getArrivingDisembarkingInfo() {
        String dayPreferences = getSailingPreferenceUseCase.getDay();
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
            if (stringIntegerPair != null) {
                view.setTextCompoundDrawableDayInfo(stringIntegerPair.first, stringIntegerPair.second);
            }
        }
    }

    private Pair<String, Integer> getArrivalDisembarkingDescription(List<EventModel> events, int day) {
        Activity activity = view.getActivity();
        if (activity == null) {
            return null;
        }
        Resources resources = activity.getResources();
        PortModel sailPort = PortModel.getSailPortByDay(events, day);
        int drawable = R.drawable.ic_excursions;

        if (day == FIRST_DAY) {
            return new Pair<>(resources.getString(R.string.departing_at) + model.getTimeFormat(sailPort.getDepartureTime()), drawable);
        } else if (day == events.size()) {
            return new Pair<>(resources.getString(R.string.arriving_at) + model.getTimeFormat(sailPort.getArrivalTime()), drawable);
        } else if (PORT_TYPE_CRUISING.equals(sailPort.getPortType())) {
            sailPort = getPortTypeNextDay(events, day, sailPort);
            return new Pair<>(resources.getString(R.string.next_port) + sailPort.getPortName(), drawable);
        } else {
            return new Pair<>(resources.getString(R.string.arriving_at) + model.getTimeFormat(sailPort.getArrivalTime())
                    + ARRIVING_DEPARTING_SEPARATOR + resources.getString(R.string.departing_at)
                    + model.getTimeFormat(sailPort.getDepartureTime()), drawable);
        }
    }

    private void recyclerOnScroll(int headerVerticalPosition) {
        int topItem = view.getFirstItemPosition();
        if (topItem == 0) {
            topItem++;
        }
        PlannerProductItem productItem = view.getNextItem(topItem);
        if (productItem == null) {
            return;
        }
        PlannerProductModel productModel = productItem.getPlannerProductModel();
        if (productModel == null || productModel.isAllDayProduct()
                || currentPartOfDayResource == getPartOfDayResource(productModel)) {
            return;
        }
        View itemView = view.getViewItemAt(topItem);
        if (itemView == null) {
            return;
        }
        if (currentPartOfDayResource == 0) {
            updateHeader(productItem);
        }
        int verticalPosition = view.getVerticalLocationOnScreen(itemView);
        if (verticalPosition < headerVerticalPosition) {
            updateHeader(productItem);
        }
    }

    private void updateHeader(PlannerProductItem productItem) {
        PlannerHeader header = productItem.getHeader();
        updateHeader(header, productItem);
        view.updateHeader(header);
    }

    private void updateHeader(PlannerHeader header, PlannerProductItem productItem) {
        int newPartOfDay = getPartOfDayResource(productItem.getPlannerProductModel());
        header.setTitle(newPartOfDay);
        currentPartOfDayResource = newPartOfDay;
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

    @StringRes
    private int getPartOfDayResource(@NonNull PlannerProductModel productModel) {
        Calendar startDate = productModel.getStartDate();
        if (startDate != null) {
            int hourOfDay = startDate.get(Calendar.HOUR_OF_DAY);
            if (hourOfDay >= HOUR_SIX && hourOfDay < HOUR_TWELVE) {
                return R.string.title_morning;
            } else if (hourOfDay >= HOUR_TWELVE && hourOfDay < HOUR_SEVENTEEN) {
                return R.string.title_afternoon;
            } else if (hourOfDay >= HOUR_SEVENTEEN && hourOfDay < HOUR_TWENTY_THREE) {
                return R.string.title_evening;
            } else {
                return R.string.title_late_night;
            }
        }
        return R.string.empty_string;
    }

    private void onChildAttachedToWindow(View itemView) {
        if (!bottomSheetIsSliding) {
            this.view.setInitialViewState(itemView);
        }
    }

    private void expandSection(PlannerHeader header) {
        List<IFlexible> itemsToAdd;
        if (header.isAllDayHeader()) {
            itemsToAdd = hiddenAllDayItems;
        } else {
            updateGeneralHeader(header);
            itemsToAdd = hiddenGeneralItems;
        }

        for (IFlexible item : itemsToAdd) {
            view.addItemToSection((ISectionable) item, header);
        }

        header.setSectionExpanded(true);
        view.scrollToHeader(header);
        view.updateHeader(header);
    }

    private void collapseSection(PlannerHeader header) {
        view.removeItemsFromSection(header);
        header.setSectionExpanded(false);
        view.scrollToHeader(header);
        if (!header.isAllDayHeader()) {
            updateGeneralHeader(header);
        }
        view.updateHeader(header);
    }

    private void updateGeneralHeader(PlannerHeader header) {
        PlannerProductItem productItem = view.getNextItem(view.getFirstItemPosition());
        if (productItem != null) {
            updateHeader(header, productItem);
        }
    }

    private void onStateChange(Integer newState) {
        switch (newState) {
            case BottomSheetBehavior.STATE_EXPANDED:
                if (!isExpanded) {
                    setBottomSheetExpandedState();
                }
                break;
            case BottomSheetBehavior.STATE_COLLAPSED:
                if (isExpanded) {
                    handler.postDelayed(() -> view.scrollToTopOfList(), DELAY_MILLIS_SCROLL);
                    handler.postDelayed(this::setBottomSheetCollapsingState, DELAY_MILLIS_COLLAPSE);
                }
                break;
            default:
                break;
        }
    }

    private void onSlide(Float slideOffset) {
        bottomSheetIsSliding = true;
        view.setShipArrivingDebarkingAlpha(MAX_SLIDE_OFFSET - slideOffset);
        if (isExpanded) {
            return;
        }
        calculateItemMargins(slideOffset);
    }

    private void calculateItemMargins(float slideOffset) {
        int visibleChildren = view.findLastVisibleItemPosition();
        int imageMargin = Math.round(slideOffset * this.view.getInitImageMargin());
        int verticalMargin = getMargin(slideOffset, this.view.getInitVerticalMargin());
        int horizontalMargin = getMargin(slideOffset, this.view.getInitHorizontalMargin());

        for (int i = 0; i <= visibleChildren; i++) {
            if (view.isIndexAHeader(i)) {
                continue;
            }

            View itemView = this.view.getViewItemAt(i);
            if (itemView == null) {
                return;
            }

            this.view.resizeItemView(itemView, verticalMargin, horizontalMargin);
            this.view.resizeImage(itemView, imageMargin);
            this.view.requestLayoutView(itemView);

            RoundedImageView imageView = ButterKnife.findById(itemView, R.id.image_itinerary_product_picture);
            if (imageView != null) {
                imageView.setRadius(R.dimen.default_radius);
            }

            if (slideOffset >= OFFSET_OVER_95_PERCENT) {
                this.view.changeSeparatorVisibility(itemView, View.VISIBLE);
                this.view.setItemViewBackground(itemView, R.drawable.background_cue_card);
            } else {
                this.view.setItemViewBackground(itemView, R.drawable.background_rounded_cue_card);
            }
        }
    }

    private int getMargin(float slideOffset, int marginValue) {
        return Math.round((MAX_SLIDE_OFFSET - slideOffset) * marginValue);
    }

    private void setBottomSheetExpandedState() {
        isExpanded = true;

        view.setShipArrivingDebarkingVisibility(View.GONE);
        view.setBottomSheetPeekHeight(NO_PEEK_HEIGHT);

        calculateItemMargins(MAX_SLIDE_OFFSET);

        view.showHeadersView();
        view.setRecyclerViewBackground(R.drawable.background_rounded_top_planner_white);
    }

    private void setBottomSheetCollapsingState() {
        isExpanded = false;

        view.setShipArrivingDebarkingVisibility(View.VISIBLE);
        view.resetItemsToInitialState();

        view.setBottomSheetPeekHeight(view.getPeekHeight());
        view.animateContainerLayout();

        view.setRecyclerViewBackground(R.drawable.background_rounded_top_planner_transparent);
    }

    private static class OnScrolledObserver extends DefaultPresentObserver<Integer, PlannerPresenter> {

        OnScrolledObserver(PlannerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Integer value) {
            getPresenter().recyclerOnScroll(value);
        }
    }

    private static class OnExpandCollapseObserver extends DefaultPresentObserver<PlannerHeader, PlannerPresenter> {

        OnExpandCollapseObserver(PlannerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(PlannerHeader header) {
            PlannerPresenter presenter = getPresenter();
            if (header.isSectionExpanded()) {
                presenter.collapseSection(header);
            } else {
                presenter.expandSection(header);
            }
        }
    }

    private static class OnChildViewAttachedObserver extends DefaultPresentObserver<View, PlannerPresenter> {

        OnChildViewAttachedObserver(PlannerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(View itemView) {
            getPresenter().onChildAttachedToWindow(itemView);
        }
    }

    private static class OnBottomSheetSlideObserver extends DefaultPresentObserver<Float, PlannerPresenter> {

        OnBottomSheetSlideObserver(PlannerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Float slideOffset) {
            getPresenter().onSlide(slideOffset);
        }
    }

    private static class OnBottomSheetStateChange extends DefaultPresentObserver<Integer, PlannerPresenter> {

        OnBottomSheetStateChange(PlannerPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(Integer newState) {
            getPresenter().onStateChange(newState);
        }
    }

}
