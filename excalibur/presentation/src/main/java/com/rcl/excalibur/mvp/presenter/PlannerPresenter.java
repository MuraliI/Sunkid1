package com.rcl.excalibur.mvp.presenter;

import android.os.Handler;
import android.support.v4.util.SparseArrayCompat;

import com.rcl.excalibur.R;
import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.adapters.base.RecyclerViewType;
import com.rcl.excalibur.adapters.viewtype.planner.PlannerHeaderViewType;
import com.rcl.excalibur.adapters.viewtype.planner.SeparatorViewType;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.DefaultObserver;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mapper.PlannerProductModelMapper;
import com.rcl.excalibur.model.PlannerProductModel;
import com.rcl.excalibur.mvp.view.PlannerView;

import java.util.ArrayList;
import java.util.List;

import static com.rcl.excalibur.model.PlannerProductModel.STATE_AFTERNOON;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_EVENING;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_LATE_NIGHT;
import static com.rcl.excalibur.model.PlannerProductModel.STATE_MORNING;

public class PlannerPresenter {
    private GetProductDbUseCase productUseCase;
    private ProductUseCaseObserver serviceObserver;

    private PlannerProductModelMapper mapper;
    private PlannerView view;

    private RecyclerViewType scrollToElement = null;

    private boolean morningAdded = false;
    private boolean afternoonAdded = false;
    private boolean eveningAdded = false;
    private boolean lateNightAdded = false;

    public PlannerPresenter(PlannerView view, GetProductDbUseCase productUseCase, PlannerProductModelMapper modelMapper) {
        this.view = view;
        this.productUseCase = productUseCase;
        this.mapper = modelMapper;
        this.serviceObserver = new ProductUseCaseObserver();
    }

    public void init() {
        view.init();
        new Handler().postDelayed(() -> productUseCase.getAll(serviceObserver), 3000);
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

    private final class ProductUseCaseObserver extends DefaultObserver<List<Product>> {
        @Override
        public void onNext(List<Product> value) {
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
