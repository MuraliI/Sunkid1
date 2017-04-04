package com.rcl.excalibur.mvp.presenter;


import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.mvp.view.PasswordView;
import com.rcl.excalibur.mvp.view.base.ActivityView;
import com.rcl.excalibur.utils.ActivityUtils;

public class PasswordPresenter implements ActivityPresenter {
    private PasswordView view;

    public PasswordPresenter(PasswordView view) {
        this.view = view;
        init();
    }

    public void onHeaderBackOnClick() {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }
        ActivityUtils.onBackActivity(activity);
    }


    private void init() {
        view.init();
    }

    @Override
    public ActivityView getView() {
        return view;
    }
}
