package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.R;
import com.rcl.excalibur.adapters.delegate.factory.DetailModuleFactory;
import com.rcl.excalibur.adapters.delegate.factory.DinningDetailModuleFactory;
import com.rcl.excalibur.model.PlanModel;
import com.rcl.excalibur.mvp.view.PlanDetailView;

public class PlanDetailPresenter implements BasePresenter {

    private PlanDetailView view;
    private DetailModuleFactory moduleFactory;

    public PlanDetailPresenter(PlanDetailView view, PlanModel planModel) {
        this.view = view;
        initModuleFactory(planModel);
        initView();
    }

    //TODO check what kind of plan was passed by the activity
    private void initModuleFactory(PlanModel planModel) {
        moduleFactory = new DinningDetailModuleFactory();
    }

    private void initView() {
        if (view.getActivity() != null) {
            view.setDetailTitle(view.getActivity().getString(R.string.hardcoded_activity_title)); //FIXME get the plan title
        }
        view.setAdapterObserver(new DetailAdapterObserver(this));
        view.render(moduleFactory.getDelegateAdapterArray(), moduleFactory.getListOfDetailModules());
    }



    public class DetailAdapterObserver extends DefaultPresentObserver<PlanModel, PlanDetailPresenter> {

        public DetailAdapterObserver(PlanDetailPresenter presenter) {
            super(presenter);
        }

        @Override
        public void onNext(PlanModel value) {
            //TODO do something when a detail item is clicked.
        }
    }
}
