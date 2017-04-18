package com.rcl.excalibur.mvp.presenter;

import android.os.Handler;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.planner.PlannerHeaderViewType;
import com.rcl.excalibur.adapters.viewtype.planner.SeparatorViewType;
import com.rcl.excalibur.domain.Offering;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetOfferingsDbUseCase;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.mvp.view.PlannerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.rcl.excalibur.model.PlannerProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_MORNING;

public class PlannerPresenter {

    private GetOfferingsDbUseCase useCase;
    private OfferingUseCaseObserver serviceObserver;

    private PlannerProductModelMapper mapper;
    private PlannerView view;

    private RecyclerViewType scrollToElement = null;

    private boolean morningAdded = false;
    private boolean afternoonAdded = false;
    private boolean eveningAdded = false;
    private boolean lateNightAdded = false;

    public PlannerPresenter(PlannerView view, GetOfferingsDbUseCase useCase, PlannerProductModelMapper modelMapper) {
        this.view = view;
        this.useCase = useCase;
        this.mapper = modelMapper;
        this.serviceObserver = new OfferingUseCaseObserver();
    }

    public void init() {
        view.init();
        //FIXME this is just mock data that is going to be replaced when we get the actual ship day.
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        new Handler().postDelayed(() -> useCase.getAllForDay(calendar.getTime(), serviceObserver), 5000);
    }

    private void refreshPositioning() {
        if (scrollToElement != null) {
            view.scrollToPosition(scrollToElement);
        }
    }

    private List<RecyclerViewType> groupEventByDate(List<PlannerProductModel> products) {
        List<RecyclerViewType> viewTypeList = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            PlannerProductModel productModel = products.get(i);
            switch (productModel.getState()) {
                case STATE_MORNING:
                    if (!morningAdded) {
                        viewTypeList.add(createHeader(R.string.title_morning));
                        morningAdded = true;
                    }
                    break;
                case STATE_AFTERNOON:
                    if (!afternoonAdded) {
                        viewTypeList.add(createHeader(R.string.title_afternoon));
                        afternoonAdded = true;
                    }
                    break;
                case STATE_EVENING:
                    if (!eveningAdded) {
                        viewTypeList.add(createHeader(R.string.title_evening));
                        eveningAdded = true;
                    }
                    break;
                case STATE_LATE_NIGHT:
                    if (!lateNightAdded) {
                        viewTypeList.add(createHeader(R.string.title_late_night));
                        lateNightAdded = true;
                    }
                    break;
                default:
                    break;
            }
            viewTypeList.add(productModel);
            viewTypeList.add(new SeparatorViewType());
        }

        return viewTypeList;
    }

    private PlannerHeaderViewType createHeader(int textRes) {
        BaseActivity activity = view.getActivity();
        if (activity == null) {
            return null;
        }
        PlannerHeaderViewType plannerHeaderViewType = new PlannerHeaderViewType();
        plannerHeaderViewType.setLabel(view.getActivity().getString(textRes));
        return plannerHeaderViewType;
    }

    private List<RecyclerViewType> addAllDayItemsAndHeader(final List<PlannerProductModel> plannerProductModels) {
        List<RecyclerViewType> allDayPlusHeader = new ArrayList<>();
        allDayPlusHeader.add(createHeader(R.string.planner_all_day_item));
        allDayPlusHeader.addAll(plannerProductModels);
        return allDayPlusHeader;
    }

    private final class OfferingUseCaseObserver extends DefaultObserver<List<Offering>> {
        @Override
        public void onNext(List<Offering> value) {
            if (view.getActivity() != null) {
                SparseArrayCompat<List<PlannerProductModel>> plannerProducts = mapper.transform(value);
                List<RecyclerViewType> viewTypeList = addAllDayItemsAndHeader(
                        plannerProducts.get(PlannerProductModelMapper.ALL_DAY_PRODUCT_LIST));
                viewTypeList.addAll(groupEventByDate(plannerProducts.get(PlannerProductModelMapper.TIMED_PRODUCT_LIST)));
                view.addPlans(viewTypeList);
                refreshPositioning();
            }
        }

    }
}
